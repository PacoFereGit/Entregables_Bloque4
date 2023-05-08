package com.wizeline.maven.learningjavamaven.model;

import java.util.Objects;

public class DebitCardDTO extends CardDTO {
    private int minimumAmount;
    private long currentAccount;

    public DebitCardDTO(){
    }

    public DebitCardDTO(DebitCardDTO target){
        super(target);
        if(target != null){
            this.minimumAmount = target.minimumAmount;
            this.currentAccount = target.currentAccount;
        }
    }


    @Override
    public CardDTO clone(){
        return new DebitCardDTO(this);
    }

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof DebitCardDTO) || !super.equals(object2)) return false;
        DebitCardDTO debitCardDTO2 = (DebitCardDTO) object2;
        return debitCardDTO2.minimumAmount ==minimumAmount && debitCardDTO2.currentAccount==currentAccount;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(int minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public long getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(long currentAccount) {
        this.currentAccount = currentAccount;
    }
}
