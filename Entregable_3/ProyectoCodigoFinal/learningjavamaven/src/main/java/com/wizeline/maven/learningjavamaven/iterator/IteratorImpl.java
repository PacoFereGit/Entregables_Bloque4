package com.wizeline.maven.learningjavamaven.iterator;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

import java.util.List;

public class IteratorImpl implements Iterator{

    private String accountName;

    private List<BankAccountDTO> bankAccountDTOList;

    private int position;

    public IteratorImpl(String accountName, List<BankAccountDTO> bankAccountDTOList){
        this.accountName = accountName;
        this.bankAccountDTOList = bankAccountDTOList;
    }

    @Override
    public boolean hasNext(){
        while(position < bankAccountDTOList.size()){
            BankAccountDTO bankAccountDTO = bankAccountDTOList.get(position);

            if(bankAccountDTO.getAccountName().equals(accountName) || accountName.equals("ALL")){
                return true;
            }else{
                position++;
            }
        }
        return false;
    }


    @Override
    public BankAccountDTO next(){
        BankAccountDTO bankAccountDTO = bankAccountDTOList.get(position);
        position++;
        return bankAccountDTO;
    }

}
