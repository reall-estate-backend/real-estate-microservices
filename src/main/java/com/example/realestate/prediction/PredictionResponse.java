package com.example.realestate.prediction;



public record PredictionResponse(
        String city,
        String date_to_predict,
        double predicted_price
) {

}
