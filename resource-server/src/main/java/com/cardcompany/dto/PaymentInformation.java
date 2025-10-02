package com.cardcompany.dto;

// DB 조회 결과 응답 형식
public record PaymentInformation(
        String BAS_YH,
        String SEQ,
        int TOT_USE_AM
) {
}