package com.example.realestate.offre.controller;

import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.entity.Offre;
import com.example.realestate.offre.request.OffreRequest;
import com.example.realestate.offre.response.OffreResponse;
import com.example.realestate.offre.service.OffreService;
import com.example.realestate.prediction.PredictionClient;
import com.example.realestate.prediction.PredictionRequest;
import com.example.realestate.prediction.PredictionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin(origins = "http://localhost:5173")
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


    @PostMapping
    public ResponseEntity<String> createOfferWithImmobilier(@RequestBody @Valid OffreRequest request) {
        if (request == null || request.immobilierRequest() == null) {
            throw new RuntimeException("Offre or Immobilier data is missing in the request body");
        }
        return ResponseEntity.ok(offreService.createOfferWithImmobilier(request));
    }

    @PutMapping("/{offreId}")
    public ResponseEntity<String> updateOfferWithImmobilier(@RequestBody OffreRequest request, @PathVariable String offreId) {
        return ResponseEntity.ok(offreService.updateOfferWithImmobilier(request, offreId));
    }


    @DeleteMapping("/{offreId}")
    public ResponseEntity<String> deleteOfferWithImmobilier(@PathVariable String offreId) {
        try {
            offreService.deleteOfferWithImmobilier(String.valueOf(offreId));
            return new ResponseEntity<>("Offer and its associated immobilier deleted successfully", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{offerId}")
    public ResponseEntity<OffreResponse> getOfferWithImmobilier(@PathVariable String offerId) {
        return ResponseEntity.ok(offreService.getOfferWithImmobilier(offerId));
    }



    @GetMapping("/allOffers")
    public ResponseEntity<List<OffreResponse>> getAllOffers() {
        return ResponseEntity.ok(offreService.getAllOffers());
    }



    @PostMapping("/PredictHousePrice")
    ResponseEntity<PredictionResponse> predictHousePrice(@RequestBody PredictionRequest request){
        return ResponseEntity.ok(predictionClient.predictHousePrice(request).getBody());
    }

    //get offers by user id
    @GetMapping("/allOffers/{userId}")
    public ResponseEntity<List<OffreResponse>> getOffresByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(offreService.findOffersByUserId(userId));
    }


    @GetMapping("/search")
    public ResponseEntity<List<OffreResponse>> searchOffresByCity(@RequestParam String city) {
        List<OffreResponse> offres = offreService.searchByCity(city);
        if (offres.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(offres);

    }




}
