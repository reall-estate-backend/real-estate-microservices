package com.example.realestate.payment.mapper;

import com.example.realestate.payment.entity.Subscription;
import com.example.realestate.payment.request.SubscriptionRequest;
import com.example.realestate.payment.response.SubscriptionResponse;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {
    public Subscription toSubscription(SubscriptionRequest request) {
        if (request == null) {
            return null;
        }
        return Subscription.builder()
                .id(request.id())
                .namePlan(request.namePlan())
                .userId(request.userId())
                .nbrPrediction(request.nbrPrediction())
                .build();
    }

    public SubscriptionResponse fromSubscription(Subscription subscription) {

        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getNamePlan(),
                subscription.getUserId(),
                subscription.getNbrPrediction()
        );
    }
}
