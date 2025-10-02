// DB 조회 결과 응답 형식 
package com.cardcompany.dto;

public record PaymentInformation(
        String BAS_YH,  // 기준 시점(2023년 4분기 기준)
        String SEQ,     // 고객번호
        int TOT_USE_AM // 총이용금액
) {
}