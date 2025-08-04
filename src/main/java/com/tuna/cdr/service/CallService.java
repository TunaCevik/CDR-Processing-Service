package com.tuna.cdr.service;

import com.tuna.cdr.domain.Call;
import com.tuna.cdr.domain.SMSCallRatePlan;
import com.tuna.cdr.domain.VoiceCallRatePlan;
import com.tuna.cdr.repository.CallRepository;
import com.tuna.cdr.repository.SMSCallRatePlanRepository;
import com.tuna.cdr.repository.VoiceCallRatePlanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class CallService {

    @Inject
    CallRepository callRepository;

    @Inject
    VoiceCallRatePlanRepository voiceCallRatePlanRepository;

    @Inject
    SMSCallRatePlanRepository smsCallRatePlanRepository;

    /**
     * Yeni bir çağrı kaydını işler, maliyetini hesaplar ve veritabanına kaydeder.
     * Diyagramlardaki "Call" objesi üzerinden maliyet hesaplama akışını takip
     * eder.
     * 
     * @param call İşlenecek Call nesnesi
     * @return İşlenmiş ve kaydedilmiş Call nesnesi
     */
    @Transactional
    public Call processAndSaveCall(Call call) {
        if (call.getCallType() == null || call.getCallType().isEmpty()) {
            throw new IllegalArgumentException("Çağrı tipi boş olamaz.");
        }

        switch (call.getCallType().toUpperCase()) {
            case "VOICE_CALL":
                if (call.getVoiceRatePlanId() == null) {
                    throw new IllegalArgumentException("Sesli çağrı için tarife planı ID'si gerekli.");
                }
                VoiceCallRatePlan voiceRatePlan = voiceCallRatePlanRepository.findById(call.getVoiceRatePlanId());
                if (voiceRatePlan != null && voiceRatePlan.isActive()) {
                    call.calculateCost(voiceRatePlan.getRatePerSecond());
                } else {
                    call.setCost(0.0);
                    System.err.println(
                            "Sesli arama tarife planı bulunamadı veya aktif değil: ID " + call.getVoiceRatePlanId());
                }
                break;
            case "SMS_CALL":
                if (call.getSmsRatePlanId() == null) {
                    throw new IllegalArgumentException("SMS çağrı için tarife planı ID'si gerekli.");
                }
                SMSCallRatePlan smsRatePlan = smsCallRatePlanRepository.findById(call.getSmsRatePlanId());
                if (smsRatePlan != null && smsRatePlan.isActive()) {
                    call.calculateCost(smsRatePlan.getRatePerSegment());
                } else {
                    call.setCost(0.0);
                    System.err.println(
                            "SMS arama tarife planı bulunamadı veya aktif değil: ID " + call.getSmsRatePlanId());
                }
                break;
            default:
                call.setCost(0.0);
                System.err.println("Bilinmeyen Çağrı Tipi: " + call.getCallType());
                break;
        }

        if (call.getCreatedAt() == null) {
            call.setCreatedAt(LocalDateTime.now());
        }
        call.setUpdatedAt(LocalDateTime.now());

        callRepository.persist(call);
        return call;
    }

    public Call getCallById(Long id) {
        return callRepository.findById(id);
    }

    public java.util.List<Call> getAllCalls() {
        return callRepository.listAll();
    }
}