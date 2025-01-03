package com.example.realestate.offre.controller;

import com.example.realestate.offre.request.OffreRequest;
import com.example.realestate.offre.response.OffreResponse;
import com.example.realestate.offre.service.OffreService;
import com.example.realestate.prediction.PredictionClient;
import com.example.realestate.prediction.PredictionRequest;
import com.example.realestate.prediction.PredictionResponse;
import feign.FeignException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/offres")
@RequiredArgsConstructor
public class OffreController {

    @Autowired
    private OffreService offreService;

    @Autowired
    private PredictionClient predictionClient;


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service Offer is working!");
    }


    @PostMapping("/addOffre")
    public String createOfferWithImmobilier(@RequestBody @Valid OffreRequest request) {
        if (request == null || request.immobilierRequest() == null) {
            throw new RuntimeException("Offre or Immobilier data is missing in the request body");
        }
        return offreService.createOfferWithImmobilier(request);
    }

    @PutMapping("/updateOffre/{offreId}")
    public String updateOfferWithImmobilier(@RequestBody OffreRequest request, @PathVariable String offreId) {
        return offreService.updateOfferWithImmobilier(request, offreId);
    }


    @DeleteMapping("/deleteOffre/{offreId}")
    public void deleteOfferWithImmobilier(@PathVariable String offreId) {
        offreService.deleteOfferWithImmobilier(String.valueOf(offreId));
    }


    @GetMapping("/getOffre/{offerId}")
    public OffreResponse getOfferWithImmobilier(@PathVariable String offerId) {
        return offreService.getOfferWithImmobilier(offerId);
    }


    @GetMapping("/allOffers")
    public List<OffreResponse> getAllOffers() {
        return offreService.getAllOffers();
    }



    @PostMapping("/predictHousePrice")
    public PredictionResponse predictHousePrice(@RequestBody PredictionRequest request){
        return predictionClient.predictHousePrice(request).getBody();
    }


    //get offers by user id
    @GetMapping("/allOffers/{userId}")
    public List<OffreResponse> getOffresByUserId(@PathVariable("userId") String userId) {
        try {
            List<OffreResponse> offres = offreService.findOffersByUserId(userId);
            if (offres == null || offres.isEmpty()) {
                return new ArrayList<>();
            }
            return offres;
        } catch (FeignException e) {
            return new ArrayList<>();
        }
    }


    @GetMapping("/search")
    public List<OffreResponse> searchOffresByCity(@RequestParam String city) {
        return offreService.searchByCity(city);

    }




}
