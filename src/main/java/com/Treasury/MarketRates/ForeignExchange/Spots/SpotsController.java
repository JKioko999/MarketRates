package com.Treasury.MarketRates.ForeignExchange.Spots;


import com.Treasury.MarketRates.Response.ResponseEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SpotsController {

    @Autowired
    public SpotsService spotsService;

    @PostMapping("/spots/addSpot")
    public ResponseEntity addSpot (@RequestBody Spots spots) {
        return spotsService.addSpot(spots);
    }

    @GetMapping("/spots/findSpots")
    public ResponseEntity findSpots (@RequestBody Spots spots) {
        return spotsService.findAllSpots(spots);
    }

    @GetMapping("/spots/findSpot/{spotId}")
    public ResponseEntity findSpots (@PathVariable Integer spotId) {
        return spotsService.findSpotById (spotId);
    }

    @Transactional
    @PutMapping("/spots/updateSpot")
    public ResponseEntity updateSpot (@RequestBody Spots spots) {
        return spotsService.updateSpot (spots);
    }

    @DeleteMapping("/spots/deleteSpot/{spotId}")
    public ResponseEntity deleteSpot (@PathVariable Integer spotId) {
        return spotsService.deleteSpotById (spotId);
    }
}
