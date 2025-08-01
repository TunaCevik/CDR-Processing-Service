Sure, here's the `README.md` content for your code base repository (`cdr-processing-service` repo), provided in a single text block as requested:

````markdown
# CDR İşleme Servisi (Quarkus)

Bu depo, Telekomünikasyon Çağrı Detay Kayıtlarını (CDR) işleyen ve maliyetlendiren bir Quarkus backend servisinin kod tabanını içermektedir.

## 🚀 Kullandığım Teknolojiler

- **Java 21**
- **Quarkus 3.20.2**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Gradle / Maven**
- **Hibernate ORM & Panache**

## 🛠️ Kurulum ve Çalıştırma

### Önkoşullar

- Quarkus CLI
- Docker & Docker Compose

### Adım 1: Projeyi Klonlama

```bash
git clone [https://github.com/KULLANICI_ADINIZ/cdr-processing-service.git](https://github.com/KULLANICI_ADINIZ/cdr-processing-service.git)
cd cdr-processing-service
```
````

### Adım 2: PostgreSQL Veritabanını Başlatma (Docker Compose ile)

Proje kök dizininde `docker-compose.yml` dosyasını kullanarak PostgreSQL veritabanını başlatın:

```bash
docker-compose up -d
```

Bu komut, Docker konteyneri içinde çalışan bir PostgreSQL veritabanını ayağa kaldıracaktır.

### Adım 3: Uygulama Ayarları

Uygulamanın şemasını otomatik olarak veritabanında oluşturması ve ilk kurulumda gerekli `id` kısıtlamalarını sağlaması için `src/main/resources/application.properties` dosyasında aşağıdaki ayarların yapılı olduğundan emin olun:

```properties
# application.properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=cdr_user
quarkus.datasource.password=mysecretpassword
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/cdr_db
quarkus.hibernate-orm.database.generation=drop-and-create # Geliştirme ortamında şema değişiklikleri için kullanılır.
quarkus.hibernate-orm.show-sql=true
```

**Önemli Not:** Geliştirme ortamında `quarkus.hibernate-orm.database.generation=drop-and-create` kullanmak hızlı şema güncellemeleri sağlar ancak her başlangıçta veritabanındaki tüm veriyi SİLER. Üretim ortamında veya veriyi korumak istediğinizde bu değeri `update` veya `none` olarak değiştirmelisiniz.

### Adım 4: Quarkus Uygulamasını Başlatma

Veritabanı konteyneri çalışır durumdayken, Quarkus uygulamasını geliştirme modunda başlatın:

```bash
./gradlew quarkusDev
```

Uygulama başarıyla başladığında konsolda `Listening on: http://localhost:8080` mesajını görmelisiniz.

**Sorun Giderme (Port Çakışması):**
Eğer `Port 8080 seems to be in use` hatası alırsanız, önceki Quarkus uygulamasını durdurmak için `Ctrl+C` kullanın ve gerekirse portu kullanan işlemi tespit edip sonlandırmak için aşağıdaki komutları kullanın:

```bash
sudo ss -tunap | grep 8080
sudo kill -9 <PID_NUMARASI>
```

Daha sonra `quarkusDev` komutunu tekrar çalıştırın.

## ⚡ API Endpoint'leri ile Etkileşim (Thunder Client / cURL)

Uygulama `http://localhost:8080` adresinde çalışmaktadır. API'leri test etmek için Thunder Client (VS Code eklentisi) veya cURL kullanabilirsiniz.

### 1\. Tarife Planları

Tarife planları, çağrı maliyetlerinin hesaplanması için gereklidir.

#### Sesli Arama Tarife Planı Oluşturma

- **Endpoint:** `POST /rate-plans/voice`
- **Headers:** `Content-Type: application/json`
- **Body (JSON):**
  ```json
  {
    "planName": "StandartSesTarifesi",
    "ratePerSecond": 0.05,
    "isActive": true
  }
  ```
- **Beklenen Yanıt:** `201 Created` ve oluşturulan tarife planının JSON objesi (`id` otomatik atanır).

#### SMS Tarife Planı Oluşturma

- **Endpoint:** `POST /rate-plans/sms`
- **Headers:** `Content-Type: application/json`
- **Body (JSON):**
  ```json
  {
    "planName": "SMSPaketA",
    "ratePerSegment": 0.02,
    "isActive": true
  }
  ```
- **Beklenen Yanıt:** `201 Created` ve oluşturulan tarife planının JSON objesi (`id` otomatik atanır).

### 2\. Çağrı Kaydı Oluşturma ve Maliyet Hesaplama

#### Sesli Çağrı Kaydı Oluşturma

- **Endpoint:** `POST /calls`
- **Headers:** `Content-Type: application/json`
- **Body (JSON):** (Önceden oluşturulan `voiceRatePlanId`'yi kullanın)
  ```json
  {
      "callType": "VOICE_CALL",
      "startTime": "2025-08-01T10:00:00",
      "endTime": "2025-08-01T10:01:30",
      "conversationDuration": 90,
      "aNumber": "905551234567",
      "bNumber": "905327654321",
      "imsi": "123456789012345",
      "direction": "MO",
      "result": "SUCCESS",
      "voiceRatePlanId": 1,  # Oluşturduğunuz sesli planın ID'si
      "imei": "987654321098765",
      "cellId": 101,
      "lacId": 201,
      "setupDuration": 5
  }
  ```
- **Beklenen Yanıt:** `201 Created` ve maliyeti hesaplanmış çağrı kaydının JSON objesi.

#### SMS Çağrı Kaydı Oluşturma

- **Endpoint:** `POST /calls`
- **Headers:** `Content-Type: application/json`
- **Body (JSON):** (Önceden oluşturulan `smsRatePlanId`'yi kullanın)
  ```json
  {
      "callType": "SMS_CALL",
      "startTime": "2025-08-01T11:00:00",
      "messageLength": 3,
      "aNumber": "905557654321",
      "bNumber": "905321234567",
      "imsi": "543210987654321",
      "direction": "MO",
      "result": "SUCCESS",
      "smsRatePlanId": 2, # Oluşturduğunuz SMS planının ID'si
      "imei": "123456789012345",
      "cellId": 102,
      "lacId": 202
  }
  ```
- **Beklenen Yanıt:** `201 Created` ve maliyeti hesaplanmış SMS kaydının JSON objesi.

### 3\. Kayıtları Görüntüleme

- **GET /calls**: Tüm çağrı kayıtlarını listeler.
- **GET /calls/{id}**: Belirli bir çağrı kaydını ID'sine göre getirir.
- **GET /rate-plans/voice**: Tüm sesli arama tarife planlarını listeler.
- **GET /rate-plans/sms**: Tüm SMS tarife planlarını listeler.
