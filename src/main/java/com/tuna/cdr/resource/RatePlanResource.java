package com.tuna.cdr.resource;

import com.tuna.cdr.domain.SMSCallRatePlan;
import com.tuna.cdr.domain.VoiceCallRatePlan;
import com.tuna.cdr.service.RatePlanService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/rate-plans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RatePlanResource {

    @Inject
    RatePlanService ratePlanService;

    /**
     * Yeni bir sesli arama tarife planı oluşturur.
     * POST /rate-plans/voice
     * 
     * @param plan Oluşturulacak VoiceCallRatePlan nesnesi
     * @return Oluşturulan VoiceCallRatePlan nesnesi ve HTTP 201 Created durumu
     */
    @POST
    @Path("/voice")
    public Response createVoiceCallRatePlan(VoiceCallRatePlan plan) {
        VoiceCallRatePlan createdPlan = ratePlanService.createVoiceCallRatePlan(plan);
        return Response.created(URI.create("/rate-plans/voice/" + createdPlan.getId())).entity(createdPlan).build();
    }

    /**
     * Yeni bir SMS tarife planı oluşturur.
     * POST /rate-plans/sms
     * 
     * @param plan Oluşturulacak SMSCallRatePlan nesnesi
     * @return Oluşturulan SMSCallRatePlan nesnesi ve HTTP 201 Created durumu
     */
    @POST
    @Path("/sms")
    public Response createSMSCallRatePlan(SMSCallRatePlan plan) {
        SMSCallRatePlan createdPlan = ratePlanService.createSMSCallRatePlan(plan);
        return Response.created(URI.create("/rate-plans/sms/" + createdPlan.getId())).entity(createdPlan).build();
    }

    /**
     * Belirli bir ID'ye sahip sesli arama tarife planını getirir.
     * GET /rate-plans/voice/{id}
     * 
     * @param id Tarife planı ID'si
     * @return VoiceCallRatePlan nesnesi veya HTTP 404 Not Found
     */
    @GET
    @Path("/voice/{id}")
    public Response getVoiceCallRatePlanById(@PathParam("id") Long id) {
        VoiceCallRatePlan plan = ratePlanService.getVoiceCallRatePlanById(id);
        if (plan != null) {
            return Response.ok(plan).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Belirli bir ID'ye sahip SMS tarife planını getirir.
     * GET /rate-plans/sms/{id}
     * 
     * @param id Tarife planı ID'si
     * @return SMSCallRatePlan nesnesi veya HTTP 404 Not Found
     */
    @GET
    @Path("/sms/{id}")
    public Response getSMSCallRatePlanById(@PathParam("id") Long id) {
        SMSCallRatePlan plan = ratePlanService.getSMSCallRatePlanById(id);
        if (plan != null) {
            return Response.ok(plan).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Tüm sesli arama tarife planlarını listeler.
     * GET /rate-plans/voice
     * 
     * @return Tüm sesli arama tarife planlarının listesi
     */
    @GET
    @Path("/voice")
    public List<VoiceCallRatePlan> getAllVoiceCallRatePlans() {
        return ratePlanService.getAllVoiceCallRatePlans();
    }

    /**
     * Tüm SMS tarife planlarını listeler.
     * GET /rate-plans/sms
     * 
     * @return Tüm SMS tarife planlarının listesi
     */
    @GET
    @Path("/sms")
    public List<SMSCallRatePlan> getAllSMSCallRatePlans() {
        return ratePlanService.getAllSMSCallRatePlans();
    }
}