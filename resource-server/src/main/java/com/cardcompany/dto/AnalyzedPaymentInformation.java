package com.cardcompany.dto;

// 분석 결과 형식
public record AnalyzedPaymentInformation(
        String SEQ,     // 고객번호
        int consumeGap // 전년대비 소비금액 차 비교
) {
}