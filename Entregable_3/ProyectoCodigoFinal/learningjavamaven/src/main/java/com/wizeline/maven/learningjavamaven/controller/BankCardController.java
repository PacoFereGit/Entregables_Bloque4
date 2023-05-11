package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.CardDTO;
import com.wizeline.maven.learningjavamaven.service.BankCardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Tag(name = "BankCard", description = "Acceso información a tarjetas .")
@RequestMapping("/api")
@RestController
public class BankCardController {


    private static final Logger LOGGER = Logger.getLogger(BankCardController.class.getName());
    @Autowired
    BankCardService bankCardService;

    @GetMapping(value="/getCards",produces = "application/json")
    public ResponseEntity<Map<String, List<CardDTO>>> getCardsGroupByType() {
        LOGGER.info("Inicia funcionalidad para obtener las tarjetas ");

        List<CardDTO> cards = bankCardService.getCards();
        LOGGER.info("El tamaño del arreglo obtenido de tarjetas es "+ cards.size());
        for(CardDTO cardsList : cards){
            LOGGER.info("dato  "+cardsList.getCardIssuer());
        }

        // Aqui implementaremos la programación funcional
        Map<String,List<CardDTO>> groupedCards;
        Function<CardDTO,String> groupFunction = (card) -> card.getCardType().toString();
        groupedCards = cards.stream().collect(Collectors.groupingBy(groupFunction));

        return new ResponseEntity<>(groupedCards, HttpStatus.OK);

    }//fin

}
