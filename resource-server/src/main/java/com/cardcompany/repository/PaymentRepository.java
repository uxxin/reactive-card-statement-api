package com.cardcompany.repository;

import com.cardcompany.entity.PaymentEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PaymentRepository extends ReactiveCrudRepository<PaymentEntity, Long> {

    @Query("SELECT * FROM EDU_DATA_F WHERE SEQ = :customerId AND BAS_YH = :baseQuarter")
        // Mono<PaymentEntity>: 이 쿼리의 결과가 0개 또는 1개의 PaymentEntity 객체임을 의미 (비동기)
    Mono<PaymentEntity> findByCustomerIdAndBaseQuarter(String customerId, String baseQuarter);
}