package com.example.realestate.payment;


public record SubscriptionRequest(
        String id,
        String namePlan,
        String userId,
        int nbrPrediction
) {
}



