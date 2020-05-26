package org.sathya.eligibility;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreditEligibility implements Serializable {
    private String creditDecision;
    private String interestRate;
    private String reason;
}
