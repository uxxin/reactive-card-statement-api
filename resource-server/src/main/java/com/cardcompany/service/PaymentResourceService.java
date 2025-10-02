package com.cardcompany.service;

import com.cardcompany.dto.PaymentInformation;
import com.cardcompany.dto.RsctoGwResponseDTO;
import com.cardcompany.entity.PaymentEntity;
import com.cardcompany.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentResourceService {

    private final PaymentRepository paymentRepository;

    public PaymentResourceService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Mono<RsctoGwResponseDTO> getPaymentInfo(String customerId, String baseQuarter) {
        
        // 입력받은 분기 기준으로 전년도 분기 계산
        String pastQuarter = calculatePastQuarter(baseQuarter);

        // 현재 분기 데이터 비동기 조회
        Mono<PaymentInformation> currentInfoMono = paymentRepository
                .findByCustomerIdAndBaseQuarter(customerId, baseQuarter)
                .defaultIfEmpty(new PaymentEntity()) // 데이터가 없을 경우 NPE 방지를 위해 빈 객체 반환
                .map(entity -> new PaymentInformation(entity.getBAS_YH(), entity.getSEQ(), entity.getTOT_USE_AM()));

        // 과거 분기 데이터 비동기 조회
        Mono<PaymentInformation> pastInfoMono = paymentRepository
                .findByCustomerIdAndBaseQuarter(customerId, pastQuarter)
                .defaultIfEmpty(new PaymentEntity()) // 데이터가 없을 경우 NPE 방지
                .map(entity -> new PaymentInformation(entity.getBAS_YH(), entity.getSEQ(), entity.getTOT_USE_AM()));

        // Mono.zip 을 사용해 두 개의 비동기 DB 조회 작업을 병렬로 실행
        // 두 비동기 조회 결과를 조합하여 최종 DTO 생성
        return Mono.zip(currentInfoMono, pastInfoMono)
                .map(tuple -> new RsctoGwResponseDTO(customerId, tuple.getT1(), tuple.getT2()));
    }

    /**
     * 입력된 분기 문자열의 전년도 동분기 문자열을 계산하는 내부 메소드
     */
    private String calculatePastQuarter(String quarter) {
        if (quarter == null || quarter.length() != 6 || quarter.charAt(4) != 'Q') {
            return "UNKNOWN";
        }
        try {
            String yearString = quarter.substring(0, 4);
            String quarterPart = quarter.substring(4);
            int previousYear = Integer.parseInt(yearString) - 1;
            return previousYear + quarterPart;
        } catch (NumberFormatException e) {
            return "UNKNOWN";
        }
    }
}