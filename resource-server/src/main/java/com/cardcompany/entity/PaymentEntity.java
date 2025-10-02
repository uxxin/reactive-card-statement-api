package com.cardcompany.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("EDU_DATA_F")
public class PaymentEntity {

    @Id
    private Long id;

    @Column("SEQ") // DB의 'SEQ' 컬럼과 매핑
    private String SEQ;

    @Column("BAS_YH") // DB의 'BAS_YH' 컬럼과 매핑
    private String BAS_YH;

    @Column("TOT_USE_AM") // DB의 'TOT_USE_AM' 컬럼과 매핑
    private int TOT_USE_AM;
}