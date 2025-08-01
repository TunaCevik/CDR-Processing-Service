package com.tuna.cdr.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calls")
public class Call extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "call_type", nullable = false)
    public String callType; // 'VOICE_CALL' veya 'SMS_CALL'

    @Column(name = "start_time", nullable = false)
    public LocalDateTime startTime;

    @Column(name = "imsi", nullable = false)
    public String imsi; // Uluslararası Mobil Abone Kimliği

    @Column(name = "imei")
    public String imei; // Uluslararası Mobil Ekipman Kimliği (nullable)

    @Column(name = "cell_id")
    public Long cellId; // Hücre kulesu ID'si (nullable)

    @Column(name = "lac_id")
    public Long lacId; // Konum alanı kodu ID'si (nullable)

    @Column(name = "a_number", nullable = false)
    public String aNumber; // Arayan numara

    @Column(name = "b_number", nullable = false)
    public String bNumber; // Aranan numara

    @Column(name = "direction", nullable = false)
    public String direction; // 'MO' (Mobil Giden) veya 'MT' (Mobil Gelen)

    @Column(name = "result", nullable = false)
    public String result; // 'SUCCESS' veya 'FAIL'

    @Column(name = "cost", nullable = false)
    public Double cost;

    @Column(name = "end_time") // Telefon çağrısı bitiş zamanı
    public LocalDateTime endTime;

    @Column(name = "setup_duration") // Kurulum süresi (milisaniye)
    public Long setupDuration;

    @Column(name = "conversation_duration") // Konuşma süresi (milisaniye)
    public Long conversationDuration;

    @Column(name = "message_length") // SMS mesaj uzunluğu
    public Integer messageLength;

    @Column(name = "voice_rate_plan_id")
    public Long voiceRatePlanId;

    @Column(name = "sms_rate_plan_id")
    public Long smsRatePlanId;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    // Constructors
    public Call() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.cost = 0.0;
    }

    public Call(String callType, LocalDateTime startTime, String imsi, String imei, Long cellId, Long lacId,
            String aNumber, String bNumber, String direction, String result, Double cost,
            LocalDateTime endTime, Long setupDuration, Long conversationDuration, Integer messageLength,
            Long voiceRatePlanId, Long smsRatePlanId) {
        this(); // Call default constructor for createdAt/updatedAt/cost defaults
        this.callType = callType;
        this.startTime = startTime;
        this.imsi = imsi;
        this.imei = imei;
        this.cellId = cellId;
        this.lacId = lacId;
        this.aNumber = aNumber;
        this.bNumber = bNumber;
        this.direction = direction;
        this.result = result;
        this.cost = cost;
        this.endTime = endTime;
        this.setupDuration = setupDuration;
        this.conversationDuration = conversationDuration;
        this.messageLength = messageLength;
        this.voiceRatePlanId = voiceRatePlanId;
        this.smsRatePlanId = smsRatePlanId;
    }

    // Lifecycle Callback
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getter Metotları
    public Long getId() {
        return id;
    }

    public String getCallType() {
        return callType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getImsi() {
        return imsi;
    }

    public String getImei() {
        return imei;
    }

    public Long getCellId() {
        return cellId;
    }

    public Long getLacId() {
        return lacId;
    }

    public String getANumber() {
        return aNumber;
    }

    public String getBNumber() {
        return bNumber;
    }

    public String getDirection() {
        return direction;
    }

    public String getResult() {
        return result;
    }

    public Double getCost() {
        return cost;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Long getSetupDuration() {
        return setupDuration;
    }

    public Long getConversationDuration() {
        return conversationDuration;
    }

    public Integer getMessageLength() {
        return messageLength;
    }

    public Long getVoiceRatePlanId() {
        return voiceRatePlanId;
    }

    public Long getSmsRatePlanId() {
        return smsRatePlanId;
    }

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

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setCellId(Long cellId) {
        this.cellId = cellId;
    }

    public void setLacId(Long lacId) {
        this.lacId = lacId;
    }

    public void setANumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public void setBNumber(String bNumber) {
        this.bNumber = bNumber;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setSetupDuration(Long setupDuration) {
        this.setupDuration = setupDuration;
    }

    public void setConversationDuration(Long conversationDuration) {
        this.conversationDuration = conversationDuration;
    }

    public void setMessageLength(Integer messageLength) {
        this.messageLength = messageLength;
    }

    public void setVoiceRatePlanId(Long voiceRatePlanId) {
        this.voiceRatePlanId = voiceRatePlanId;
    }

    public void setSmsRatePlanId(Long smsRatePlanId) {
        this.smsRatePlanId = smsRatePlanId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // calculateCost Metodu (Service katmanının ihtiyaç duyduğu iş mantığı)
    public void calculateCost(double rate) {
        if ("VOICE_CALL".equalsIgnoreCase(this.callType) && this.conversationDuration != null) {
            this.cost = this.conversationDuration * rate;
        } else if ("SMS_CALL".equalsIgnoreCase(this.callType) && this.messageLength != null) {
            this.cost = (double) this.messageLength * rate;
        } else {
            this.cost = 0.0;
        }
    }
}