// 리소스 서버가 게이트웨이에 응답할 때의 형식
package com.cardcompany.dto;

public record ResourceResponse(
        String SEQ,     // 고객번호
        PaymentInformation currentquarter,  // 요청한 시점의 데이터
        PaymentInformation pastquarter      // 비교할 시점의 데이터
) {

}