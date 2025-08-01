package com.tuna.cdr.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voice_call_rate_plans")
public class VoiceCallRatePlan extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "plan_name", nullable = false, unique = true)
    public String planName;

    @Column(name = "rate_per_second", nullable = false)
    public Double ratePerSecond;

    @Column(name = "is_active", nullable = false)
    public boolean isActive;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    // Constructors
    public VoiceCallRatePlan() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
    }

    // Getter Metotları
    public Long getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }

    public Double getRatePerSecond() {
        return ratePerSecond;
    }

    public boolean isActive() {
        return isActive;
    } // Boolean için isXxx() formatı yaygındır

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setter Metotları
    public void setId(Long id) {
        this.id = id;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setRatePerSecond(Double ratePerSecond) {
        this.ratePerSecond = ratePerSecond;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}