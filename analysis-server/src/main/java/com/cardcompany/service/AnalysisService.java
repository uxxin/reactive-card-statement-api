package com.cardcompany.service;

import com.cardcompany.dto.AnalysisRequest;
import com.cardcompany.dto.AnalyzedPaymentInformation;
import com.cardcompany.dto.PaymentInformation;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {

    public AnalyzedPaymentInformation compare(AnalysisRequest request) {
        // 기본 오류 검증
        if (request.getCurrentQuarter() == null || request.getPastQuarter() == null) {
            throw new IllegalArgumentException("currentQuarter or pastQuarter is required");
        }
        if (request.getSeq() == null || request.getSeq().isBlank()) {
            throw new IllegalArgumentException("SEQ is required");
        }

        // 조회 시점 설정
        PaymentInformation cur = request.getCurrentQuarter();
        PaymentInformation past = request.getPastQuarter();

        // 시점에 따른 총 소비 금액
        int curAmt = cur.getTotUseAm();
        int pastAmt = past.getTotUseAm();

        int gap = curAmt - pastAmt;

        double rate = (pastAmt == 0)
                ? (curAmt == 0 ? 0.0 : null)
                : (double) gap / Math.abs(pastAmt);

        return new AnalyzedPaymentInformation(request.getSeq(), gap, rate);
    }
}
