package com.Treasury.MarketRates.Securities.TreasuryBills;

import com.Treasury.MarketRates.CalculationDTO.BankCalculation.TBillCommissionCalc;
import com.Treasury.MarketRates.Response.ResponseEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bills")
public class TBillsController {

    @Autowired
    public TBillService tBillService;

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer (@RequestBody TBills tBills) {
        return tBillService.addCustomer(tBills);
    }

    @GetMapping("/findCustomers")
    public ResponseEntity findAllCustomers (@RequestBody TBills tBills) {
        return tBillService.findAllCustomers(tBills);
    }

    @GetMapping("/findCustomer/{tBillId}")
    public ResponseEntity findCustomerById (@PathVariable Long tBillId) {
        return tBillService.findCustomerById(tBillId);
    }

    @DeleteMapping("/deleteCustomer/{tBillId}")
    public ResponseEntity deleteCustomer (@PathVariable Long tBillId) {
        return tBillService.deleteCustomerById(tBillId);
    }

    @Transactional
    @PutMapping("/updateCustomer")
    public ResponseEntity updateCustomer (@RequestBody TBills tBills) {
        return tBillService.updateCustomer(tBills);
    }

    @PostMapping("/calculateCommission")
    ResponseEntity commissionCalculation (@RequestBody TBillCommissionCalc request) {
        return tBillService.commissionCalculation(request);
    }
}
