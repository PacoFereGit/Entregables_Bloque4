package com.wizeline.maven.learningjavamaven.service;


import java.time.LocalDateTime;
import java.util.List;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

public interface BankAccountBO {

    List<BankAccountDTO> getAccounts();
    BankAccountDTO getAccountDetails(String user, String lastUsage);
}
