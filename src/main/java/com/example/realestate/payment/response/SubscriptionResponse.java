package com.example.realestate.payment.response;

public record SubscriptionResponse(
        String id,
        String namePlan,
        String userId,
        int nbrPrediction
) {
}

