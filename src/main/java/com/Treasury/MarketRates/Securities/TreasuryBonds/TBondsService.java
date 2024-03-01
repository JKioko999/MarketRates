package com.Treasury.MarketRates.Securities.TreasuryBonds;

import com.Treasury.MarketRates.CalculationDTO.BankCalculation.TBondCommissionCalc;
import com.Treasury.MarketRates.CalculationDTO.CustomerCalculation.TBondsCouponCalc;
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
public class TBondsService {

    @Autowired
    public TBondsRepo tBondsRepo;

    public ResponseEntity addCustomer(TBonds tBonds) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            tBonds = tBondsRepo.save(tBonds);
            responseEntity.setMessage("Customer added Successfully");
            responseEntity.setEntity(tBonds);
            responseEntity.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("Error adding customer", e);
            responseEntity.setMessage("Failed to add customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity findAllCustomers(TBonds tBonds) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            List<TBonds> allCustomers = tBondsRepo.findAll();
            if (!allCustomers.isEmpty()) {
                responseEntity.setMessage("Customers found successfully");
                responseEntity.setEntity(allCustomers);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customers not found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error finding customers", e);
            responseEntity.setMessage("Failed to find customers");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity findCustomerById(Long tBondId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<TBonds> tBondsOptional = tBondsRepo.findById(tBondId);
            if (tBondsOptional.isPresent()) {
                responseEntity.setMessage("Customer found successfully");
                responseEntity.setEntity(tBondsOptional.get());
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer not found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error retrieving customer",e);
            responseEntity.setMessage("Failed to retrieve customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity updateCustomer(TBonds updatedCustomer) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<TBonds> existingCustomerOptional = tBondsRepo.findById(updatedCustomer.getTBondId());
            if (existingCustomerOptional.isPresent()) {
                TBonds existingCustomer = existingCustomerOptional.get();
                existingCustomer.setFaceValue(updatedCustomer.getFaceValue());
                existingCustomer.setCouponRate(updatedCustomer.getCouponRate());
                existingCustomer.setPeriod(updatedCustomer.getPeriod());

                existingCustomer = tBondsRepo.save(existingCustomer);

                responseEntity.setMessage("Customer updated successfully");
                responseEntity.setEntity(existingCustomer);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer not updated");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error updating customer");
            responseEntity.setMessage("Failed to update customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return  responseEntity;
    }

    public ResponseEntity deleteCustomerById(Long tBondId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<TBonds> customerOptional = tBondsRepo.findById(tBondId);
            if (customerOptional.isPresent()) {
                tBondsRepo.deleteById(tBondId);
                responseEntity.setMessage("Customer deleted successfully");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer not deleted");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error deleting customer");
            responseEntity.setMessage("Failed to delete customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity couponCalculation(TBondsCouponCalc request) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {

            Double couponRate = request.getCouponRate() / 100;
            Double yearlyCouponAmount = couponRate * request.getFaceValue();
            Double payableCouponAmount = yearlyCouponAmount / 2;
            Double numberPayable = request.getPeriod() * 2;
            Double totalCouponAmount = yearlyCouponAmount * request.getPeriod();

            Map<String, Double> couponResponse = new HashMap<>();
            couponResponse.put("Total Coupon Amount", totalCouponAmount);
            couponResponse.put("Yearly Coupon Amount", yearlyCouponAmount);
            couponResponse.put("Payable Coupon Amount", payableCouponAmount);
            couponResponse.put("Number of Coupons", numberPayable);

            responseEntity.setMessage("Coupon calculated successfully");
            responseEntity.setEntity(couponResponse);
            responseEntity.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("Error calculating Coupon");
            responseEntity.setMessage("Failed to calculate coupon");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity commissionCalculation(TBondCommissionCalc request) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {

            Double commissionRate = request.getCommissionRate()/ 100;
            Double commission = commissionRate * request.getFaceValue();

            Map<String,Double> commissionResponse = new HashMap<>();

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
