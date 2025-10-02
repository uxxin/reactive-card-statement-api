package com.cardcompany.controller;

import com.cardcompany.dto.RsctoGwResponseDTO;
import com.cardcompany.service.PaymentResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/resource")
public class PaymentResourceController {

    private final PaymentResourceService paymentResourceService;

    public PaymentResourceController(PaymentResourceService paymentResourceService) {
        this.paymentResourceService = paymentResourceService;
    }

    @GetMapping("/payment-info")
    public Mono<RsctoGwResponseDTO> getPaymentInformation(
            // URL의 쿼리 파라미터 'seq' 값을 String customerId 변수에 할당 (ex: ?seq=C0001)
            @RequestParam("seq") String customerId,
            // URL의 쿼리 파라미터 'bas_yh' 값을 String baseQuarter 변수에 할당 (ex: &bas_yh=2023Q4)
            @RequestParam("bas_yh") String baseQuarter) {

        System.out.println("리소스 서버: 게이트웨이로부터 요청 받음 -> SEQ=" + customerId + ", BAS_YH=" + baseQuarter);
        return paymentResourceService.getPaymentInfo(customerId, baseQuarter);
    }
}