package com.cardcompany.dto;

// 게이트웨이가 분석 서버에 요청할 때 DTO
public record GwtoAnaRequestDTO(
        String SEQ,     // 고객번호
        PaymentInformation currentquarter,  // 요청한 시점의 데이터
        PaymentInformation pastquarter      // 비교할 시점의 데이터
) {
}