package com.wizeline.maven.learningjavamaven.iterator;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

import java.util.ArrayList;
import java.util.List;

public class BankAccountDTOCollectionImpl implements BankAccountDTOCollection {
    private List<BankAccountDTO> bankAccountDTOList;

    public BankAccountDTOCollectionImpl(){bankAccountDTOList = new ArrayList<>();}

    public void addBankAccountDTO(BankAccountDTO bankAccountDTO){this.bankAccountDTOList.add(bankAccountDTO);}

    public void removeBankAccountDTO(BankAccountDTO bankAccountDTO){this.bankAccountDTOList.remove(bankAccountDTO);}

    @Override
    public Iterator iterator (String accountName){return new IteratorImpl(accountName, this.bankAccountDTOList);}

}
