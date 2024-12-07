package com.example.realestate.prediction;


import java.time.LocalDate;

public record PredictionRequest(
        String city,
        LocalDate date_to_predict,
        double current_price

) {

}
