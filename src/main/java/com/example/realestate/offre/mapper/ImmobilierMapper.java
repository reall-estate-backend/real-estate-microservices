package com.example.realestate.offre.mapper;

import com.example.realestate.offre.entity.Immobilier;
import com.example.realestate.offre.request.ImmobilierRequest;
import com.example.realestate.offre.response.ImmobilierResponse;
import org.springframework.stereotype.Component;

@Component
public class ImmobilierMapper {

    // Method to map ImmobilierRequest to Immobilier entity
    public static Immobilier toImmobilier(ImmobilierRequest request) {
        if (request == null) {
            return null;
        }

        return Immobilier.builder()
                .id(request.id())
                .title(request.title())
                .images(request.images())
                .bedroom(request.bedroom())
                .bathroom(request.bathroom())
                .price(request.price())
                .address(request.address())
                .city(request.city())
                .description(request.description())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .type(request.type())                  
                .property(request.property())          
                .utilities(request.utilities())        
                .petPolicy(request.petPolicy())        
                .incomePolicy(request.incomePolicy())  
                .size(request.size())                  
                .schoolDistance(request.schoolDistance()) 
                .busDistance(request.busDistance())    
                .restaurantDistance(request.restaurantDistance()) 
                .build();
    }

    // Method to map Immobilier entity to ImmobilierResponse (optional)
    public static ImmobilierResponse fromImmobilier(Immobilier immobilier) {
        return new ImmobilierResponse(
                immobilier.getId(),
                immobilier.getTitle(),
                immobilier.getImages(),
                immobilier.getBedroom(),
                immobilier.getBathroom(),
                immobilier.getPrice(),
                immobilier.getAddress(),
                immobilier.getCity(),
                immobilier.getLatitude(),
                immobilier.getLongitude(),
                immobilier.getDescription(),
                immobilier.getType(),                 
                immobilier.getProperty(),             
                immobilier.getUtilities(),            
                immobilier.getPetPolicy(),            
                immobilier.getIncomePolicy(),         
                immobilier.getSize(),                 
                immobilier.getSchoolDistance(),       
                immobilier.getBusDistance(),          
                immobilier.getRestaurantDistance()    
        );
    }
}
