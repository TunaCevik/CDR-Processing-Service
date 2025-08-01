package com.tuna.cdr.repository;

import com.tuna.cdr.domain.SMSCallRatePlan; // SMSCallRatePlan Entity'nizin doğru paket yolu
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SMSCallRatePlanRepository implements PanacheRepository<SMSCallRatePlan> {
    // Tarife planı adına göre arama yapmak için özel bir metot (isteğe bağlı)
    public SMSCallRatePlan findByPlanName(String planName) {
        return find("planName", planName).firstResult();
    }
}