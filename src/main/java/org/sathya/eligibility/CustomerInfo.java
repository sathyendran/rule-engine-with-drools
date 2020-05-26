package org.sathya.eligibility;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerInfo implements Serializable {
    private Boolean answerFound = false;
    private Boolean isNameDiffered ;
    private Integer noOfDelinquency;
    private Integer score;
    private Double revolvingBalance;
    private Double realEstateBalance;
    private String realEstatePayment;
    private Double pastDueAmount;
    private Double loanAmountRequested = 0.0;
}
