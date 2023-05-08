package com.wizeline.maven.learningjavamaven.iterator;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

public interface BankAccountDTOCollection {
    public void addBankAccountDTO(BankAccountDTO bankAccountDTO);

    public void removeBankAccountDTO(BankAccountDTO bankAccountDTO);
    public Iterator iterator(String accountName);
}
