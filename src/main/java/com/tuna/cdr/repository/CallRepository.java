package com.tuna.cdr.repository;

import com.tuna.cdr.domain.Call;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CallRepository implements PanacheRepository<Call> {
    // Call nesneleri için temel veritabanı işlemleri sağlar:
    // - persist(call): Çağrıyı veritabanına kaydeder
    // - findById(id): Belirli bir ID'ye sahip çağrıyı bulur
    // - listAll(): Tüm çağrıları listeler
    // - delete(call): Çağrıyı siler

    // Örneğin: A numarasına göre çağrı bulma
    public Call findByANumber(String aNumber) {
        return find("aNumber", aNumber).firstResult();
    }
}