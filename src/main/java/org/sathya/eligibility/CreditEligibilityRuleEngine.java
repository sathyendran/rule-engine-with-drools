package org.sathya.eligibility;

import lombok.extern.log4j.Log4j2;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CreditEligibilityRuleEngine {

    @Autowired
    private KieBase kieBase;

    public CreditEligibility checkCreditEligibility(CustomerInfo customerInfo) {
        log.info("Validating the rules for the input  : {} ", customerInfo);
        KieSession kieSession = newKieSession();
        Agenda agenda = kieSession.getAgenda();
        agenda.getAgendaGroup("regulatory-rule").setFocus();
        kieSession.insert(customerInfo);
        CreditEligibility creditEligibility = new CreditEligibility();
        kieSession.setGlobal("creditEligibility", creditEligibility);
        int noOfRulesFired = kieSession.fireAllRules();
        kieSession.dispose();
        log.info("No of Rules fired : {}", noOfRulesFired);
        log.info("Credit Eligibility Response : {} ", creditEligibility);
        return creditEligibility;
    }

    protected KieSession newKieSession() {
        return kieBase.newKieSession();
    }

}
