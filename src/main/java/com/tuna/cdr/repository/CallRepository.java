package com.tuna.cdr.repository;

import com.tuna.cdr.domain.Call; // Call Entity'nizin doğru paket yolu
import io.quarkus.hibernate.orm.panache.PanacheRepository; // Panache'nin temel repository arayüzü
import jakarta.enterprise.context.ApplicationScoped; // Bu sınıfın Quarkus tarafından yönetilen bir bileşen olduğunu belirtir

@ApplicationScoped // Bu annotation, Quarkus'un bu sınıfın bir bileşen olduğunu anlamasını sağlar.
public class CallRepository implements PanacheRepository<Call> {
    // PanacheRepository arayüzünü uyguladığımızda, Panache otomatik olarak bize
    // Call nesneleri için temel veritabanı işlemleri sağlar:
    // - persist(call): Çağrıyı veritabanına kaydeder
    // - findById(id): Belirli bir ID'ye sahip çağrıyı bulur
    // - listAll(): Tüm çağrıları listeler
    // - delete(call): Çağrıyı siler
    // ... ve daha fazlası

    // Eğer belirli bir field'a göre arama yapmak isterseniz, burada özel metodlar
    // tanımlayabilirsiniz.
    // Örneğin: A numarasına göre çağrı bulma
    public Call findByANumber(String aNumber) {
        return find("aNumber", aNumber).firstResult();
    }
}