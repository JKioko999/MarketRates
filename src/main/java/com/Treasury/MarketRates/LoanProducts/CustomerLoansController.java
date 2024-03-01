package com.Treasury.MarketRates.LoanProducts;

import com.Treasury.MarketRates.CalculationDTO.BankCalculation.LoanInterestCalc;
import com.Treasury.MarketRates.Response.ResponseEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
public class CustomerLoansController {

    @Autowired
    public CustomerLoanService customerLoanService;

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer (@RequestBody CustomerLoans customerLoan) {
        return customerLoanService.addcustomer(customerLoan);
    }

    @GetMapping("/findCustomers")
    public ResponseEntity findAllCustomers (@RequestBody CustomerLoans customerLoan) {
        return customerLoanService.findAllCustomers(customerLoan);
    }

    @GetMapping("/findCustomer/{LoanId}")
    public ResponseEntity findCustomer (@PathVariable Long LoanId) {
        return customerLoanService.findCustomerById(LoanId);
    }

    @Transactional
    @PutMapping("/updateCustomer")
    public ResponseEntity updateCustomer (@RequestBody CustomerLoans customerLoans) {
        return customerLoanService.updateCustomer(customerLoans);
    }

    @DeleteMapping("/deleteCustomer/{LoanId}")
    public ResponseEntity deleteCustomerById (@PathVariable Long LoanId) {
        return customerLoanService.deleteCustomerById(LoanId);
    }
    @PostMapping("/calculateInterest")
    public ResponseEntity calculateInterest(@RequestBody LoanInterestCalc request) {
        return customerLoanService.calculateInterest(request);
    }
}
