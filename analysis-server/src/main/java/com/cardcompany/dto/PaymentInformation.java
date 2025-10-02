package com.cardcompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInformation {

    @JsonProperty("BAS_YH")
    private String basYh;      // 기준 시점

    @JsonProperty("SEQ")
    private String seq;        // 고객번호

    @JsonProperty("TOT_USE_AM")
    private Integer totUseAm;  // 총 이용금액 (null 구분용으로 Integer)

}
