package com.tuna.cdr.repository;

import com.tuna.cdr.domain.VoiceCallRatePlan; // VoiceCallRatePlan Entity'nizin doğru paket yolu
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VoiceCallRatePlanRepository implements PanacheRepository<VoiceCallRatePlan> {
    // Tarife planı adına göre arama yapmak için özel bir metot (isteğe bağlı)
    public VoiceCallRatePlan findByPlanName(String planName) {
        return find("planName", planName).firstResult();
    }
}