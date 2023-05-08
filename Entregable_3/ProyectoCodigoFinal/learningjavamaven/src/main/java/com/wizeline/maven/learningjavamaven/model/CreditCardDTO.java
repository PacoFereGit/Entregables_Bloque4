package com.wizeline.maven.learningjavamaven.model;

import java.time.LocalDateTime;

public class CreditCardDTO extends CardDTO{
    private LocalDateTime cutOffDate;
    private long creditAmount;
    public CreditCardDTO (){

    }
    public CreditCardDTO(CreditCardDTO target){
        super(target);
        if(target != null){
            this.creditAmount = target.creditAmount;
            this.cutOffDate = target.cutOffDate;
        }
    }

    @Override
    public CardDTO clone(){
        return new CreditCardDTO(this);
    }

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof CreditCardDTO) || !super.equals(object2)) return false;
        CreditCardDTO creditCardDTO2 = (CreditCardDTO) object2;
        return creditCardDTO2.cutOffDate == cutOffDate && creditCardDTO2.creditAmount == creditAmount;
    }

    public LocalDateTime getCutOffDate() {
        return cutOffDate;
    }

    public void setCutOffDate(LocalDateTime cutOffDate) {
        this.cutOffDate = cutOffDate;
    }

    public long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(long creditAmount) {
        this.creditAmount = creditAmount;
    }
}
