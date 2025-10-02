package com.cardcompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
// 반환할 분석 결과 DTO
public class AnalyzedPaymentInformation {

    @JsonProperty("seq")  // "SEQ"로 내리고 싶으면 "SEQ"로 변경
    private String seq;

    @JsonProperty("consumeGap")
    private int consumeGap;

    @JsonProperty("consumeRate")
    private double consumeRate;
}
