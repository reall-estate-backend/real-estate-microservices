package com.example.realestate.payment;

public record SubscriptionResponse(
        String id,
        String namePlan,
        String userId,
        int nbrPrediction
) {
}

