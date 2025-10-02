// 분석 결과
package com.cardcompany.dto;

public record AnalyzedPaymentInformation(
        String SEQ,     // 고객번호
        int consumeGap // 전년대비 소비금액 차 비교
) {
}