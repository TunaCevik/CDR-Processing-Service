package com.tuna.cdr.service;

import com.tuna.cdr.domain.SMSCallRatePlan;
import com.tuna.cdr.domain.VoiceCallRatePlan;
import com.tuna.cdr.repository.SMSCallRatePlanRepository;
import com.tuna.cdr.repository.VoiceCallRatePlanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class RatePlanService {

    @Inject
    VoiceCallRatePlanRepository voiceCallRatePlanRepository;

    @Inject
    SMSCallRatePlanRepository smsCallRatePlanRepository;

    @Transactional
    public VoiceCallRatePlan createVoiceCallRatePlan(VoiceCallRatePlan plan) {
        voiceCallRatePlanRepository.persist(plan);
        return plan;
    }

    @Transactional
    public SMSCallRatePlan createSMSCallRatePlan(SMSCallRatePlan plan) {
        smsCallRatePlanRepository.persist(plan);
        return plan;
    }

    public VoiceCallRatePlan getVoiceCallRatePlanById(Long id) {
        return voiceCallRatePlanRepository.findById(id);
    }

    public SMSCallRatePlan getSMSCallRatePlanById(Long id) {
        return smsCallRatePlanRepository.findById(id);
    }

    public List<VoiceCallRatePlan> getAllVoiceCallRatePlans() {
        return voiceCallRatePlanRepository.listAll();
    }

    public List<SMSCallRatePlan> getAllSMSCallRatePlans() {
        return smsCallRatePlanRepository.listAll();
    }
}