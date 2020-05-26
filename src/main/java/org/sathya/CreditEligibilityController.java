package org.sathya;

import lombok.extern.log4j.Log4j2;
import org.sathya.eligibility.CreditEligibility;
import org.sathya.eligibility.CreditEligibilityRuleEngine;
import org.sathya.eligibility.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Log4j2
public class CreditEligibilityController {

    @Autowired
    private CreditEligibilityRuleEngine creditEligibilityRuleEngine;

    @PostMapping("/rule-engine")
    public CreditEligibility checkRule(@RequestBody CustomerInfo customerInfo) {
        log.info("Checking the credit eligibility : {} ",customerInfo);
        return creditEligibilityRuleEngine.checkCreditEligibility(customerInfo) ;
    }
}
