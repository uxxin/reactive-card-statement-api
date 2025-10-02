package com.cardcompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 게이트웨이가 분석서버에 요청할 때의 형식 DTO
public class AnalysisRequest {

    @JsonProperty("SEQ")
    private String seq;                 // 루트 SEQ도 카멜 필드 + 명시 매핑

    @JsonProperty("currentQuarter")
    private PaymentInformation currentQuarter;

    @JsonProperty("pastQuarter")
    private PaymentInformation pastQuarter;

}
