// 유저가 게이트웨이에 요청할 때의 형식
package com.cardcompany.dto;

public record RequestDTO(
        String SEQ,     // 고객번호
        String BAS_YH  // 기준 시점(2023년 4분기 기준)
) {
}