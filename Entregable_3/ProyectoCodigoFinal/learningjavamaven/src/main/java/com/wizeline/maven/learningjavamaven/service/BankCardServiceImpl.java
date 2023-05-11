package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.CardDTO;
import com.wizeline.maven.learningjavamaven.model.CreditCardDTO;
import com.wizeline.maven.learningjavamaven.model.DebitCardDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.wizeline.maven.learningjavamaven.utils.Utils.randomAccountNumber;
import static com.wizeline.maven.learningjavamaven.utils.Utils.randomInt;

public class BankCardServiceImpl implements BankCardService{

    private static final Logger LOGGER = Logger.getLogger(BankCardServiceImpl.class.getName());

    private static String cardIssuer = "Masterd Card";
    private static int minimumAmount = 5000;
    private static String expirationDate = "13/01/2025";

    private static String cutOffDate = "01/06/2023";

    private static long creditAmount = 30000;


    @Override
    public List<CardDTO> getCards(){
        List<CardDTO> cardDTOList = new ArrayList<>();

        LOGGER.info("Inicia el clonado de objetos para el patron de diseño prototype");
        DebitCardDTO debitCardDTO1 = buildDebitCard();
        cardDTOList.add(debitCardDTO1);
        DebitCardDTO debitCardDTO2 = (DebitCardDTO) debitCardDTO1.clone();
        debitCardDTO2.setCardVerificationValue(getVerificationValue());
        debitCardDTO2.setCardNumber(randomAccountNumber());
        debitCardDTO2.setCurrentAccount(randomAccountNumber());
        cardDTOList.add(debitCardDTO2);

        CreditCardDTO creditCardDTO1 = buildCreditCard();
        cardDTOList.add(creditCardDTO1);
        CreditCardDTO creditCardDTO2 = (CreditCardDTO) creditCardDTO1.clone();
        creditCardDTO2.setCardNumber(randomAccountNumber());
        creditCardDTO2.setCardVerificationValue(getVerificationValue());
        cardDTOList.add(creditCardDTO2);

        return cardDTOList;
    }

    private LocalDate getDateFormatter(String fecha){
        DateTimeFormatter dateformatter =DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate usage = LocalDate.parse(fecha,dateformatter);
        return usage;
    }

    private String getVerificationValue(){
        String verificationValue="";
        for(int i= 0; i<3; i++){
            verificationValue = verificationValue.concat(randomInt());
        }
        return verificationValue;
    }

    private DebitCardDTO buildDebitCard(){
        LocalDate expirationDateFormatter = getDateFormatter(expirationDate);
        DebitCardDTO debitCardDTO = new DebitCardDTO();
        debitCardDTO.setCardNumber(randomAccountNumber());
        debitCardDTO.setCardIssuer(cardIssuer);
        debitCardDTO.setCardVerificationValue(getVerificationValue());
        debitCardDTO.setMinimumAmount(minimumAmount);
        debitCardDTO.setExpirationDate(expirationDateFormatter.atStartOfDay());
        debitCardDTO.setCurrentAccount(randomAccountNumber());
        debitCardDTO.setCardType("Tarjeta de Debito");
        return debitCardDTO;
    }

    private CreditCardDTO buildCreditCard(){
        LocalDate expirationDateFormatter = getDateFormatter(expirationDate);
        LocalDate cutOffDateFormatter = getDateFormatter(cutOffDate);
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCreditAmount(creditAmount);
        creditCardDTO.setCardIssuer(cardIssuer);
        creditCardDTO.setCardNumber(randomAccountNumber());
        creditCardDTO.setCardVerificationValue(getVerificationValue());
        creditCardDTO.setCutOffDate(cutOffDateFormatter.atStartOfDay());
        creditCardDTO.setExpirationDate(expirationDateFormatter.atStartOfDay());
        creditCardDTO.setCardType("Tarjeta de Credito");;
        return creditCardDTO;
    }
}
