package com.cardcompany.controller;

import com.cardcompany.dto.AnalysisRequest;
import com.cardcompany.dto.AnalyzedPaymentInformation;
import com.cardcompany.service.AnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
@AllArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @PostMapping("/compare")
    public ResponseEntity<AnalyzedPaymentInformation> compare(@RequestBody AnalysisRequest request) {
        return ResponseEntity.ok(analysisService.compare(request));
    }

}
