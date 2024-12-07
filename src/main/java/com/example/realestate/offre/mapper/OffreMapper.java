package com.example.realestate.offre.mapper;

import com.example.realestate.offre.entity.Offre;
import com.example.realestate.offre.request.OffreRequest;
import com.example.realestate.offre.response.OffreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class OffreMapper {



    public static Offre toOffre(OffreRequest request) {
        if (request == null) {
            return null;
        }

        return Offre.builder()
                .id(request.id())
                .immobilier(ImmobilierMapper.toImmobilier(request.immobilierRequest()))
                .userId(request.userId())
                .build();
    }

    public static OffreResponse fromOffre(Offre offre) {
        if (offre == null) {
            return null;
        }

        return new OffreResponse(
                offre.getId(),
                ImmobilierMapper.fromImmobilier(offre.getImmobilier()),
                offre.getUserId(),
                offre.getDateDePublication(),
                offre.getDateDeUpdate()
        );
    }
}
