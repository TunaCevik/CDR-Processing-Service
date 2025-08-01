package com.tuna.cdr.resource;

import com.tuna.cdr.domain.Call;
import com.tuna.cdr.service.CallService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/calls") // Bu sınıfın /calls yoluyla erişilebilir olduğunu belirtir
@Produces(MediaType.APPLICATION_JSON) // Geri dönen yanıtların JSON formatında olacağını belirtir
@Consumes(MediaType.APPLICATION_JSON) // Gelen isteklerin JSON formatında olacağını belirtir
public class CallResource {

    @Inject // CallService'i bu kaynağa enjekte et
    CallService callService;

    /**
     * Yeni bir çağrı kaydı alır, işler ve kaydeder.
     * POST /calls
     * 
     * @param call Gelen Call nesnesi (JSON'dan dönüştürülecek)
     * @return Oluşturulan Call nesnesi ve HTTP 201 Created durumu
     */
    @POST
    public Response createCall(Call call) {
        // Service katmanındaki işleme ve kaydetme metodunu çağır
        Call processedCall = callService.processAndSaveCall(call);
        // HTTP 201 Created durum kodu ve yeni kaynağın URI'si ile yanıt dön
        return Response.created(URI.create("/calls/" + processedCall.getId())).entity(processedCall).build();
    }

    /**
     * Belirli bir ID'ye sahip çağrı kaydını getirir.
     * GET /calls/{id}
     * 
     * @param id Çağrı ID'si
     * @return Call nesnesi ve HTTP 200 OK durumu veya HTTP 404 Not Found
     */
    @GET
    @Path("/{id}")
    public Response getCallById(@PathParam("id") Long id) {
        Call call = callService.getCallById(id);
        if (call != null) {
            return Response.ok(call).build(); // Bulunduysa 200 OK
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Bulunamazsa 404 Not Found
        }
    }

    /**
     * Tüm çağrı kayıtlarını listeler.
     * GET /calls
     * 
     * @return Tüm çağrıların listesi ve HTTP 200 OK durumu
     */
    @GET
    public List<Call> getAllCalls() {
        return callService.getAllCalls();
    }

    // İsterseniz burada güncelleme (PUT) ve silme (DELETE) metotlarını da
    // ekleyebilirsiniz.
    // Örneğin:
    // @PUT
    // @Path("/{id}")
    // public Response updateCall(@PathParam("id") Long id, Call updatedCall) {
    // Call result = callService.updateCall(id, updatedCall); // Service'e uygun
    // metot eklenmeli
    // if (result != null) {
    // return Response.ok(result).build();
    // } else {
    // return Response.status(Response.Status.NOT_FOUND).build();
    // }
    // }
}