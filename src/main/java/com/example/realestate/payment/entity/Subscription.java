package com.example.realestate.payment.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Subscription {
    @Id
    private String id;
    private String namePlan;
    private String userId;
    private int nbrPrediction;
}
