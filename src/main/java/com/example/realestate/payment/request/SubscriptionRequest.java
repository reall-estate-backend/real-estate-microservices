package com.example.realestate.payment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record SubscriptionRequest(
        String id,
        String namePlan,
        String userId,
        int nbrPrediction
) {
}



