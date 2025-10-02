package com.cardcompany.controller;


import com.cardcompany.dto.ResourceResponse;
import com.cardcompany.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeoutException;


@RestController
public class Controller {

    private final WebClient webClient;

    public Controller(WebClient webClient) {
        this.webClient = webClient;
    }

//    @GetMapping("/getInfo")
//    public Mono<ResponseDTO> getInfo(@RequestParam("SEQ") String SEQ) throws InterruptedException {
//        return webClient.get()
//                .uri("{domain}/{endpoint}?seq={seq}", SEQ)
//                .retrieve()
//                .bodyToMono(ResponseDTO.class)
//                .timeout(Duration.ofSeconds(10))
//                .onErrorResume(TimeoutException.class, e ->
//                        Mono.just(new ResponseDTO("TIMEOUT", null, null))
//                );
//    }

    @GetMapping("/getInfo")
    public Mono<ResponseEntity<ResponseDTO>> getInfo(@RequestParam("SEQ") String SEQ) {
        return webClient.get()
                .uri("{domain}/{endpoint}?seq={seq}", SEQ)
                .retrieve()
                .bodyToMono(ResourceResponse.class)
                .timeout(Duration.ofSeconds(10))
                .flatMap(resource ->
                        webClient.post()
                                .uri("{domain}/{endpoint}")
                                .bodyValue(resource)
                                .retrieve()
                                .bodyToMono(ResponseDTO.class)
                                // 정상 응답 : 200 OK
                                .map(dto -> ResponseEntity.ok(dto))
                                .timeout(Duration.ofSeconds(10))
                                // 두 번째 API 타임아웃 : 20001
                                .onErrorResume(TimeoutException.class,
                                        e -> Mono.just(ResponseEntity
                                                .status(200)
                                                .body(new ResponseDTO("TIMEOUT", resource.currentquarter(), null))))
                )
                // 첫 번째 API 타임아웃 : 504 Gateway Timeout
                .onErrorResume(TimeoutException.class,
                        e -> Mono.just(ResponseEntity
                                .status(504)
                                .body(new ResponseDTO(SEQ, null, null))));
    }
}
