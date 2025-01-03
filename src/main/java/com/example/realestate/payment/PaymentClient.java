package com.example.realestate.payment;


import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "payment-service",
        url = "http://localhost:8070/api/v1/planSubscription"
)
public interface PaymentClient {

    @PostMapping("/createPlan")
    public String createPlan(@RequestBody @Valid PlanRequest planRequest);

    @GetMapping("/allplans")
    public List<PlanResponse> findAllPlans();

    @GetMapping("/planName/{planName}")
    public PlanResponse findByName(
            @PathVariable("planName") String planName);


    @GetMapping("/exists/{plan-id}")
    public Boolean existsById(
            @PathVariable("plan-id") String planId);


    @PostMapping("/createSubscription")
    public String createSubscription(@RequestBody @Valid SubscriptionRequest subscriptionRequest);


    @GetMapping("/userSub/{idUser}")
    public SubscriptionResponse findUserSubscriptionB(
            @PathVariable("idUser") String idUser);


    @PutMapping("/updateSubscription")
    public String updateUserSubscriptionB(
            @RequestBody @Valid SubscriptionRequest subscriptionRequest);


    @GetMapping("/allSubscriptions")
    public List<SubscriptionResponse> findAllSubscriptions();


    @DeleteMapping("/subscription/{user-id}")
    public void deleteByIdUser(
            @PathVariable("user-id") String userId);

}
