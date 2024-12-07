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

@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<String> createPlan(@RequestBody @Valid PlanRequest planRequest) {
        return ResponseEntity.ok(planService.createPlan(planRequest));
    }

    @GetMapping("/allplans")
    public ResponseEntity<List<PlanResponse>> findAll()
    {
        return ResponseEntity.ok(planService.planList());
    }


    @GetMapping("/planName/{planName}")
    public ResponseEntity<PlanResponse> findByName(
            @PathVariable("planName") String planName)
    {
        return ResponseEntity.ok(planService.getPlanByName(planName));
    }


    @GetMapping("/exists/{plan-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("plan-id") String planId)
    {
        return ResponseEntity.ok(planService.existsById(planId));
    }



    //---------------------------Subscriptions--------------------------------//
    
    @PostMapping("/createSubscription")
    public ResponseEntity<String> createSubscription(@RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscriptionRequest));
    }


    @GetMapping("/userSub/{idUser}")
    public ResponseEntity<SubscriptionResponse> findUserSubscriptionB(
            @PathVariable("idUser") String idUser)
    {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByIdUser(idUser));
    }


    @PutMapping
    public ResponseEntity<String> updateUserSubscriptionB(
            @RequestBody @Valid SubscriptionRequest subscriptionRequest)
    {
        return ResponseEntity.ok(subscriptionService.updateUserSubscription(subscriptionRequest));
    }

    @GetMapping("/allSubscriptions")
    public ResponseEntity<List<SubscriptionResponse>> findAllSubscriptions()
    {
        return ResponseEntity.ok(subscriptionService.SubscriptionList());
    }

    @DeleteMapping("/subscription/{user-id}")
    public ResponseEntity<Void> deleteByIdUser(
            @PathVariable("user-id") String userId)
    {
        subscriptionService.deleteUserSubscription(userId);
        return ResponseEntity.accepted().build();
    }

}
