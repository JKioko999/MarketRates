package com.Treasury.MarketRates.LoanProducts;

import com.Treasury.MarketRates.CalculationDTO.BankCalculation.LoanInterestCalc;
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
public class CustomerLoanService {

    @Autowired
    public CustomerLoanRepo customerLoanRepo;

    public ResponseEntity addcustomer(CustomerLoans customerLoan) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try{
            customerLoan = customerLoanRepo.save(customerLoan);
            responseEntity.setMessage("Customer Added Successfully");
            responseEntity.setEntity(customerLoan);
            responseEntity.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("Error adding customer", e);
            responseEntity.setMessage("Failed to add the customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return responseEntity;
    }

    public ResponseEntity findAllCustomers(CustomerLoans customerLoan) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            List<CustomerLoans> allCustomers = customerLoanRepo.findAll();
            if (!allCustomers.isEmpty()) {
                responseEntity.setMessage("Customers found successfully");
                responseEntity.setEntity(allCustomers);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("No customers found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error finding all customers", e);
            responseEntity.setMessage("Failed to retrieve customers");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity findCustomerById(Long LoanId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<CustomerLoans> customerOptional = customerLoanRepo.findById(LoanId);
            if (customerOptional.isPresent()) {
                responseEntity.setMessage("Customer Found Successfully");
                responseEntity.setEntity(customerOptional.get());
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer Not Found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error finding customer", e);
            responseEntity.setMessage("Failed to find the customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return responseEntity;
    }

    public ResponseEntity updateCustomer(CustomerLoans updatedCustomer) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<CustomerLoans> existingCustomerOptional = customerLoanRepo.findById(updatedCustomer.getLoanId());
            if (existingCustomerOptional.isPresent()) {
                CustomerLoans existingCustomer = existingCustomerOptional.get();
                existingCustomer.setPrincipal(updatedCustomer.getPrincipal());
                existingCustomer.setAnnualRate(updatedCustomer.getAnnualRate());
                existingCustomer.setPeriod(updatedCustomer.getPeriod());
                // Update any other fields as needed

                // Save the updated customer
                existingCustomer = customerLoanRepo.save(existingCustomer);

                responseEntity.setMessage("Customer updated successfully");
                responseEntity.setEntity(existingCustomer);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer not found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error updating customer", e);
            responseEntity.setMessage("Failed to update customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity deleteCustomerById(Long LoanId) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            Optional<CustomerLoans> customerOptional = customerLoanRepo.findById(LoanId);
            if (customerOptional.isPresent()) {
                customerLoanRepo.deleteById(LoanId);
                responseEntity.setMessage("Customer deleted successfully");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.OK.value());
            } else {
                responseEntity.setMessage("Customer not found");
                responseEntity.setEntity(null);
                responseEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            log.error("Error deleting customer", e);
            responseEntity.setMessage("Failed to delete customer");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }

    public ResponseEntity calculateInterest(LoanInterestCalc request) {
        ResponseEntity responseEntity = new ResponseEntity<>();
        try {
            double monthlyInterestRate = request.getAnnualRate() / 12 / 100;
            double interestPerMonth = request.getPrincipal() * monthlyInterestRate;
            double totalInterest = interestPerMonth * request.getPeriod();

            Map<String, Double> interestResponse = new HashMap<>();
            interestResponse.put("interestPerMonth", interestPerMonth);
            interestResponse.put("totalInterest", totalInterest);

            responseEntity.setMessage("Interest calculated successfully");
            responseEntity.setEntity(interestResponse);
            responseEntity.setStatusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            log.error("Error calculating interest", e);
            responseEntity.setMessage("Failed to calculate interest");
            responseEntity.setEntity(null);
            responseEntity.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseEntity;
    }
}
