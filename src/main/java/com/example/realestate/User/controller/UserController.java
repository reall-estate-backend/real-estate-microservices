package com.example.realestate.User.controller;


import com.example.realestate.User.config.UserAuthenticationProvider;
import com.example.realestate.User.dtos.CredentialsDto;
import com.example.realestate.User.dtos.SignUpDto;
import com.example.realestate.User.dtos.UserDto;
import com.example.realestate.User.request.UserRequest;
import com.example.realestate.User.response.UserResponse;
import com.example.realestate.User.service.UserService;
import com.example.realestate.offre.OffreClient;
import com.example.realestate.offre.OffreRequest;
import com.example.realestate.offre.OffreResponse;
import com.example.realestate.payment.*;
import com.example.realestate.prediction.PredictionClient;
import com.example.realestate.prediction.PredictionRequest;
import com.example.realestate.prediction.PredictionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private PredictionClient predictionClient;

    @Autowired
    private OffreClient offreClient;

    @Autowired
    private PaymentClient paymentClient;



    //---------------------------------User------------------------------------//
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getUsername()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(createdUser);
    }


    @PutMapping
    public ResponseEntity<Void> updateUser(
            @RequestBody @Valid UserRequest userRequest)
    {
        userService.updateUser(userRequest);
        return ResponseEntity.accepted().build();
    }


    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll()
    {
        return ResponseEntity.ok(userService.findAllUsers());
    }


    @GetMapping("/exists/{user-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("user-id") String userId)
    {
        return ResponseEntity.ok(userService.existsById(userId));
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("userId") String userId)
    {
        return ResponseEntity.ok(userService.findById(userId));
    }


    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("user-id") String userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }



    //---------------------------------Prediction------------------------------------//


    @PostMapping("/predict")
    public ResponseEntity<PredictionResponse> predictHousePrice(@RequestBody PredictionRequest request){
        return ResponseEntity.ok(offreClient.predictHousePrice(request));
    }


    //---------------------------------Payment------------------------------------//

    @PostMapping("/createPlan")
    public ResponseEntity<String> createPlan(@RequestBody @Valid PlanRequest planRequest) {
        return ResponseEntity.ok(paymentClient.createPlan(planRequest));
    }


    @GetMapping("/allplans")
    public ResponseEntity<List<PlanResponse>> findAllOffres()
    {
        return ResponseEntity.ok(paymentClient.findAllPlans());
    }


    @GetMapping("/planName/{planName}")
    public ResponseEntity<PlanResponse> findByNameOffre(
            @PathVariable("planName") String planName)
    {
        return ResponseEntity.ok(paymentClient.findByName(planName));
    }



    @GetMapping("/exists/{plan-id}")
    public ResponseEntity<Boolean> existsByIdPlan(
            @PathVariable("plan-id") String planId)
    {
        return ResponseEntity.ok(paymentClient.existsById(planId));
    }


    @PostMapping("/createSubscription")
    public ResponseEntity<String> createSubscription(@RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return ResponseEntity.ok(paymentClient.createSubscription(subscriptionRequest));
    }


    @GetMapping("/userSub/{idUser}")
    public ResponseEntity<SubscriptionResponse> findUserSubscriptionB(
            @PathVariable("idUser") String idUser)
    {
        return ResponseEntity.ok(paymentClient.findUserSubscriptionB(idUser));
    }



    @PutMapping("/updateSubscription")
    public ResponseEntity<String> updateUserSubscriptionB(
            @RequestBody @Valid SubscriptionRequest subscriptionRequest)
    {
        return ResponseEntity.ok(paymentClient.updateUserSubscriptionB(subscriptionRequest));
    }



    @GetMapping("/allSubscriptions")
    public ResponseEntity<List<SubscriptionResponse>> findAllSubscriptions()
    {
        return ResponseEntity.ok(paymentClient.findAllSubscriptions());
    }


    @DeleteMapping("/deleteSubscription/{user-id}")
    public ResponseEntity<Void> deleteByIdUser(
            @PathVariable("user-id") String userId)
    {
        paymentClient.deleteByIdUser(userId);
        return ResponseEntity.accepted().build();
    }



    //---------------------------------Offre------------------------------------//

    @PostMapping("/addOffre")
    public ResponseEntity<String> createOfferWithImmobilier(@RequestBody @Valid OffreRequest request) {
        return ResponseEntity.ok(offreClient.createOfferWithImmobilier(request));
    }


    @PutMapping("/updateOffre/{offreId}")
    public ResponseEntity<String> updateOfferWithImmobilier(@RequestBody OffreRequest request, @PathVariable String offreId) {
        return ResponseEntity.ok(offreClient.updateOfferWithImmobilier(request, offreId));
    }



    @DeleteMapping("/deleteOffre/{offreId}")
    public ResponseEntity<String> deleteOfferWithImmobilier(@PathVariable String offreId) {
        try {
            offreClient.deleteOfferWithImmobilier(String.valueOf(offreId));
            return new ResponseEntity<>("Offer and its associated immobilier deleted successfully", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getOffre/{offerId}")
    public ResponseEntity<OffreResponse> getOfferWithImmobilier(@PathVariable String offerId) {
        return ResponseEntity.ok(offreClient.getOfferWithImmobilier(offerId));
    }


    @GetMapping("/allOffers")
    public ResponseEntity<List<OffreResponse>> getAllOffers() {
        return ResponseEntity.ok(offreClient.getAllOffers());
    }


    @GetMapping("/allOffers/{userId}")
    public ResponseEntity<List<OffreResponse>> getOffresByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(offreClient.getOffresByUserId(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<OffreResponse>> searchOffresByCity(@RequestParam String city) {
        List<OffreResponse> offres = offreClient.searchOffresByCity(city);
        if (offres.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(offres);

    }


}


