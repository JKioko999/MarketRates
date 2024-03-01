package com.Treasury.MarketRates.ForeignExchange.Spots;


import com.Treasury.MarketRates.Response.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SpotsService {

    @Autowired
    public SpotsRepository spotsRepository;

    public ResponseEntity addSpot(Spots spots) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            spots = spotsRepository.save(spots);
            responseEntity.setMessage("Spot added successfully");
            responseEntity.setEntity(spots);
            responseEntity.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("Error adding Spot", e);
            responseEntity.setMessage("Failed to add Spot");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity findAllSpots(Spots spots) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            List<Spots> allSpots = spotsRepository.findAll();
            if (!allSpots.isEmpty()) {
                responseEntity.setMessage("Spots retrieved successfully");
                responseEntity.setEntity(allSpots);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Spots not found");
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
                responseEntity.setEntity(null);
            }
        } catch (Exception e) {
            log.error("Error retrieving spots",e);
            responseEntity.setMessage("Failed retrieving spots");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity findSpotById(Integer spotId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<Spots> spotOption = spotsRepository.findById(spotId);
            if (spotOption.isPresent()) {
                responseEntity.setMessage("Spot found successfully");
                responseEntity.setEntity(spotOption.get());
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Spot not found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error retrieving spot");
            responseEntity.setMessage("Failed to retrieve the spot");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }



    public ResponseEntity deleteSpotById(Integer spotId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<Spots> spotsOptional = spotsRepository.findById(spotId);
            if (spotsOptional.isPresent()) {
                spotsRepository.deleteById(spotId);
                responseEntity.setMessage("Spot deleted successfully");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Spot not deleted");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error deleting Spot");
            responseEntity.setMessage("Failed to delete spot");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity updateSpot(Spots spots) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<Spots> existingSpotUpdate = spotsRepository.findById(spots.getSpotId());
            if (existingSpotUpdate.isPresent()) {
                Spots spotUpdate = existingSpotUpdate.get();
                spotUpdate.setBidPrice(spotUpdate.getBidPrice());
                spotUpdate.setAskPrice(spotUpdate.getAskPrice());

                spotUpdate = spotsRepository.save(spotUpdate);

                responseEntity.setMessage("Spot updated successfully");
                responseEntity.setEntity(spotUpdate);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Spot not found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error updating Spot");
            responseEntity.setMessage("Failed to update spot");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }
}
