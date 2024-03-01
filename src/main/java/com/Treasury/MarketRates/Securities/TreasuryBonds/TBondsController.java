package com.Treasury.MarketRates.Securities.TreasuryBonds;

import com.Treasury.MarketRates.CalculationDTO.BankCalculation.TBondCommissionCalc;
import com.Treasury.MarketRates.CalculationDTO.CustomerCalculation.TBondsCouponCalc;
import com.Treasury.MarketRates.Response.ResponseEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bonds")
public class TBondsController {

    @Autowired
    public TBondsService tBondsService;

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer (@RequestBody TBonds tBonds) {
        return tBondsService.addCustomer(tBonds);
    }

    @GetMapping("/findCustomers")
    public ResponseEntity findAllCustomers (@RequestBody TBonds tBonds) {
        return tBondsService.findAllCustomers(tBonds);
    }

    @GetMapping("/findCustomer/{tBondId}")
    public ResponseEntity findCustomer (@PathVariable Long tBondId) {
        return tBondsService.findCustomerById(tBondId);
    }

    @Transactional
    @PutMapping("/updateCustomer")
    public ResponseEntity updateCustomer (@RequestBody TBonds tBonds) {
        return tBondsService.updateCustomer(tBonds);
    }

    @DeleteMapping("/deleteCustomer/{tBondId}")
    public ResponseEntity deleteCustomer (@PathVariable Long tBondId) {
        return tBondsService.deleteCustomerById (tBondId);
    }

    @PostMapping("/calculateCoupon")
    public ResponseEntity couponCalculation (@RequestBody TBondsCouponCalc request) {
        return tBondsService.couponCalculation (request);
    }

    @PostMapping("/calculateCommission")
    public ResponseEntity commissionCalculation (@RequestBody TBondCommissionCalc request) {
        return tBondsService.commissionCalculation (request);
    }
}
