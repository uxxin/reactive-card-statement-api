package com.cardcompany.dto;

// 게이트웨이가 유저에 응답할 때의 형식
public record GwtoUserResponseDTO(
        String SEQ,     // 고객번호
        PaymentInformation currentquarter,
        AnalyzedPaymentInformation analysed
) {

}
