package com.Treasury.MarketRates.Securities.TreasuryBills;

import com.Treasury.MarketRates.CalculationDTO.BankCalculation.TBillCommissionCalc;
import com.Treasury.MarketRates.Response.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TBillService {

    @Autowired
    public TBillsRepo tBillsRepo;

    public ResponseEntity addCustomer(TBills tBills) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {

            tBills = tBillsRepo.save(tBills);
            responseEntity.setMessage("Customer Added Successfully");
            responseEntity.setEntity(tBills);
            responseEntity.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("Error adding Customer",e);
            responseEntity.setMessage("Failed to add the customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity findAllCustomers(TBills tBills) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            List<TBills> allCustomers = tBillsRepo.findAll();
            if (!allCustomers.isEmpty()) {
                responseEntity.setMessage("Customers found Successfully");
                responseEntity.setEntity(allCustomers);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customers Not Found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error finding customers",e);
            responseEntity.setMessage("Failed to retrieve the customers");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }


    public ResponseEntity findCustomerById(Long tBillId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        Optional<TBills> customerOptional = tBillsRepo.findById(tBillId);
        try {
            if (customerOptional.isPresent()) {
                responseEntity.setMessage("Customer Found");
                responseEntity.setEntity(customerOptional.get());
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer Not Found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error finding the customer",e);
            responseEntity.setMessage("Failed to retrieve the customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return responseEntity;
    }

    public ResponseEntity deleteCustomerById(Long tBillId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<TBills> customerOptional = tBillsRepo.findById(tBillId);
            if (customerOptional.isPresent()) {
                tBillsRepo.deleteById(tBillId);
                responseEntity.setMessage("Customer deleted successfully");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer Not Found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error deleting Customer",e);
            responseEntity.setMessage("Failed to delete Customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity updateCustomer(TBills updatedCustomer) {
        ResponseEntity responseEntity = new ResponseEntity<>();

        try {
            Optional<TBills> existingcustomerUpdate = tBillsRepo.findById(updatedCustomer.getTBillId());
            if (existingcustomerUpdate.isPresent()) {
                TBills customerUpdate = existingcustomerUpdate.get();
                customerUpdate.setFaceValue(updatedCustomer.getFaceValue());
                customerUpdate.setCommissionRate(updatedCustomer.getCommissionRate());
                customerUpdate.setYearPeriod(updatedCustomer.getYearPeriod());

                customerUpdate = tBillsRepo.save(customerUpdate);

                responseEntity.setMessage("Customer updated successfully");
                responseEntity.setEntity(customerUpdate);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer Not Found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error updating Customer");
            responseEntity.setMessage("Failed to update Cutomer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity commissionCalculation(TBillCommissionCalc request) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {

            Double commissionRate = request.getCommissionRate()/ 100;
            Double commission = commissionRate * request.getFaceValue();

            Map<String, Double> commissionResponse = new HashMap<>();
            commissionResponse.put("Commission", commission);

            responseEntity.setMessage("Commission calculated successfully");
            responseEntity.setEntity(commissionResponse);
            responseEntity.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("Error calculating commission");
            responseEntity.setMessage("Failed to calculate commission");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return responseEntity;
    }
}
