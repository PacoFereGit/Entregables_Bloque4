package com.wizeline.maven.learningjavamaven.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.enums.Country;

import static com.wizeline.maven.learningjavamaven.utils.Utils.*;


public class BankAccountBOImpl implements BankAccountBO{

    @Override
    public BankAccountDTO getAccountDetails (String user, String lastUsage){
        DateTimeFormatter dateformatter =DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.parse(lastUsage,dateformatter);
        return buildBankAccount(user,true,Country.MX,usage.atStartOfDay()); //Agegar código de pais
    }

    @Override
    public List<BankAccountDTO> getAccounts(){
        //Definicion de lista con la información de las cuentas existentes.
        List<BankAccountDTO> accountDTOList = new ArrayList<>();
        accountDTOList.add(buildBankAccount("user3@wizeline.com",true, Country.MX, LocalDateTime.now().minusDays(7)));
        accountDTOList.add(buildBankAccount("user1@wizeline.com",false, Country.FR,LocalDateTime.now().minusMonths(2)));
        accountDTOList.add(buildBankAccount("user2@wizeline.com",false, Country.US,LocalDateTime.now().minusYears(4)));

        return accountDTOList;
    }

    //Creacion tipo de dato BankAccount con ayuda de la clase Utils.java
    private BankAccountDTO buildBankAccount(String user, boolean isActive, Country country, LocalDateTime lastUsage){
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(randomAccountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(randomInt()));
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(randomBalance());
        bankAccountDTO.setAccountType(pickRandomAccountType());
        bankAccountDTO.setCountry(getCountry(country));
        //bankAccountDTO.getLastUsage(); // <- Se invoca al metodo de acceso get() para obtener la fecha actual
        bankAccountDTO.setLastUsage(lastUsage);
        bankAccountDTO.setCreationDate(lastUsage);
        bankAccountDTO.setAccountActive(isActive);
        return bankAccountDTO;
    }
}
