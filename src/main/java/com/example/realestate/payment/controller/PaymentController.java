package com.example.realestate.payment.controller;


import com.example.realestate.payment.request.PlanRequest;
import com.example.realestate.payment.request.SubscriptionRequest;
import com.example.realestate.payment.response.PlanResponse;
import com.example.realestate.payment.response.SubscriptionResponse;
import com.example.realestate.payment.service.PlanService;
import com.example.realestate.payment.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/planSubscription")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private PlanService planService;

    @Autowired
    private SubscriptionService subscriptionService;


    //---------------------------Plans--------------------------------//

    @PostMapping("/createPlan")
    public String createPlan(@RequestBody @Valid PlanRequest planRequest) {
        return planService.createPlan(planRequest);
    }

    @GetMapping("/allplans")
    public List<PlanResponse> findAllPlans()
    {
        return planService.planList();
    }


    @GetMapping("/planName/{planName}")
    public PlanResponse findByName(
            @PathVariable("planName") String planName)
    {
        return planService.getPlanByName(planName);
    }


    @GetMapping("/exists/{plan-id}")
    public Boolean existsById(
            @PathVariable("plan-id") String planId)
    {
        return planService.existsById(planId);
    }



    //---------------------------Subscriptions--------------------------------//
    
    @PostMapping("/createSubscription")
    public String createSubscription(@RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return subscriptionService.createSubscription(subscriptionRequest);
    }


    @GetMapping("/userSub/{idUser}")
    public SubscriptionResponse findUserSubscriptionB(
            @PathVariable("idUser") String idUser)
    {
        return subscriptionService.getSubscriptionByIdUser(idUser);
    }


    @PutMapping("/updateSubscription")
    public String updateUserSubscriptionB(
            @RequestBody @Valid SubscriptionRequest subscriptionRequest)
    {
        return subscriptionService.updateUserSubscription(subscriptionRequest);
    }


    @GetMapping("/allSubscriptions")
    public List<SubscriptionResponse> findAllSubscriptions()
    {
        return subscriptionService.SubscriptionList();
    }

    @DeleteMapping("/subscription/{user-id}")
    public void deleteByIdUser(
            @PathVariable("user-id") String userId)
    {
        subscriptionService.deleteUserSubscription(userId);
    }

}
