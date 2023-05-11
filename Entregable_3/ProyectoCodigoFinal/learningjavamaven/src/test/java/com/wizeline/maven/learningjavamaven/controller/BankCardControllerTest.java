package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.CardDTO;
import com.wizeline.maven.learningjavamaven.model.CreditCardDTO;
import com.wizeline.maven.learningjavamaven.model.DebitCardDTO;
import com.wizeline.maven.learningjavamaven.service.BankCardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankCardControllerTest {

    private static final Logger LOGGER = Logger.getLogger(BankCardControllerTest.class.getName());

    @Mock
    private BankCardService bankCardService;

    @InjectMocks
    private BankCardController bankCardController;

    @Test
    public void DadoServicioBankCardServiceEntregaUnaListaDeTarjetas_CuandoObtieneTodasLasTarjetas_EntoncesRegresaMismaLista(){
        LOGGER.info("Test BankCardControllerTest");
        LOGGER.info("DadoServicioBankCardServiceEntregaUnaListaDeTarjetas_CuandoObtieneTodasLasTarjetas_EntoncesRegresaMismaLista");

        DebitCardDTO debitCardDTO = new DebitCardDTO();
        debitCardDTO.setCardNumber(4536789087654327L);
        debitCardDTO.setCardType("Tarjeta de Debito");
        debitCardDTO.setCardIssuer("Masterd Card");
        debitCardDTO.setCardVerificationValue("456");
        debitCardDTO.setMinimumAmount(5000);
        debitCardDTO.setCurrentAccount(4534256789L);
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCardVerificationValue("234");
        creditCardDTO.setCreditAmount(7000);
        creditCardDTO.setCardType("Tarjeta de Credito");
        creditCardDTO.setCardNumber(45360987786543L);
        creditCardDTO.setCardIssuer("Masterd Card");

        List<CardDTO> cardDTOList = List.of(debitCardDTO,creditCardDTO);
        when(bankCardService.getCards()).thenReturn(cardDTOList);

        // Aqui implementaremos la programación funcional
        Map<String,List<CardDTO>> groupedCards;
        Function<CardDTO,String> groupFunction = (card) -> card.getCardType().toString();
        groupedCards = cardDTOList.stream().collect(Collectors.groupingBy(groupFunction));

        final ResponseEntity<Map<String,List<CardDTO>>> responseEntity = bankCardController.getCardsGroupByType();

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
               () -> assertEquals(groupedCards,responseEntity.getBody())
        );

    }





}
