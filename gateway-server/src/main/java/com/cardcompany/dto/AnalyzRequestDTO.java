// 게이트웨이가 분석서버에 요청할 때의 형식
package com.cardcompany.dto;

public record AnalyzRequestDTO(
        String SEQ,     // 고객번호
        PaymentInformation currentquarter,  // 요청한 시점의 데이터
        PaymentInformation pastquarter      // 비교할 시점의 데이터
) {
}
