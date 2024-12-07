package com.example.realestate.payment.service;

import com.example.realestate.payment.entity.Subscription;
import com.example.realestate.payment.exception.SubscriptionNotFoundException;
import com.example.realestate.payment.mapper.SubscriptionMapper;
import com.example.realestate.payment.repository.SubscriptionRepository;
import com.example.realestate.payment.request.SubscriptionRequest;
import com.example.realestate.payment.response.PlanResponse;
import com.example.realestate.payment.response.SubscriptionResponse;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;


@Service
public class SubscriptionService {

    @Autowired
    private PlanService planService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Transactional(rollbackFor = Exception.class)
    public String createSubscription(SubscriptionRequest request){

        if (request == null) return null;

        PlanResponse plan = planService.getPlanByName(request.namePlan());
        if (plan == null) {
            throw new NotFoundException("Unknown Given plan");
        }

        Subscription subscription = Subscription.builder()
                .namePlan(request.namePlan())
                .userId(request.userId())
                .nbrPrediction(plan.maxPrediction())
                .build();

        var sub =  subscriptionRepository.save(subscription);

        return sub.getId();
    }


    public SubscriptionResponse getSubscriptionByIdUser(String idUser){
        return subscriptionRepository.findSubscriptionByUserId(idUser)
                .map(subscriptionMapper::fromSubscription)
                .orElseThrow(()-> new SubscriptionNotFoundException(format("Cannot find Subscription :: %s", idUser)));
    }


    public String updateUserSubscription(SubscriptionRequest subscriptionRequest) {

        var subExist = getSubscriptionByIdUser(subscriptionRequest.userId());
        if (subExist != null) {
            Subscription subscription = Subscription.builder()
                    .namePlan(subscriptionRequest.namePlan())
                    .userId(subscriptionRequest.userId())
                    .nbrPrediction(subscriptionRequest.nbrPrediction())
                    .build();

            var sub = subscriptionRepository.save(subscription);
            return sub.getId();
        }

        return null;
    }


    public List<SubscriptionResponse> SubscriptionList(){
        return subscriptionRepository.findAll()
                .stream()
                .map(subscriptionMapper :: fromSubscription)
                .collect(Collectors.toList());
    }

    public void deleteUserSubscription(String userId) {
        subscriptionRepository.deleteById(getSubscriptionByIdUser(userId).id());
    }


}
