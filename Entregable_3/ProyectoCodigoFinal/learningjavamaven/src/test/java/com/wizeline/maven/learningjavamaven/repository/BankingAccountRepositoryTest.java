package com.wizeline.maven.learningjavamaven.repository;


import com.wizeline.maven.learningjavamaven.enums.Country;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static com.wizeline.maven.learningjavamaven.utils.Utils.*;
import static com.wizeline.maven.learningjavamaven.utils.Utils.pickRandomAccountType;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankingAccountRepositoryTest {

    @Autowired
    BankingAccountRepository bankingAccountRepository;

    @BeforeAll
    public void inicializar() {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(randomAccountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(randomInt()));
        bankAccountDTO.setUser("Paco1");
        bankAccountDTO.setAccountBalance(randomBalance());
        bankAccountDTO.setAccountType(pickRandomAccountType());
        bankAccountDTO.setCountry(Country.MX.toString());
        bankAccountDTO.setAccountActive(true);
        bankingAccountRepository.save(bankAccountDTO);
    }

    @AfterAll
    public void limpiar() {
        bankingAccountRepository.deleteAll();
    }

    @Test
    public void DadaBDConDatos_CuandoRepositorioObtieneTodosLosDatos_EntoncesEntregaTodosLosRegistros(){
        List<BankAccountDTO> bankAccountDTOList = bankingAccountRepository.findAll();
        assertAll(
                () -> assertEquals(1, bankAccountDTOList.size()),
                () -> assertTrue(
                        bankAccountDTOList.stream()
                                .map(BankAccountDTO::getUser)
                                .collect(Collectors.toList())
                                .containsAll(List.of("Paco1")))
        );
    }


    @Test
    public void DadaBDConDatos_CuandoRepositorioBorraTodosLosDatos_EntoncesEntregaLIstaVacia(){
        bankingAccountRepository.deleteAll();///Borrar todos los registros

        List<BankAccountDTO> bankAccountDTOList = bankingAccountRepository.findAll();
        assertAll(
                () -> assertEquals(0, bankAccountDTOList.size())
        );
    }

    @Test
    public void DadaBDConDatos_CuandoRepositorioInsertaUnNuevoRegistro_EntoncesEntregaListaCompletaConNuevoRegistro(){
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(randomAccountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(randomInt()));
        bankAccountDTO.setUser("Paco2");
        bankAccountDTO.setAccountBalance(randomBalance());
        bankAccountDTO.setAccountType(pickRandomAccountType());
        bankAccountDTO.setCountry(Country.MX.toString());
        bankAccountDTO.setAccountActive(true);
        bankingAccountRepository.save(bankAccountDTO);

        List<BankAccountDTO> bankAccountDTOList = bankingAccountRepository.findAll();
        assertAll(
                () -> assertEquals(2, bankAccountDTOList.size()),
                () -> assertTrue(
                               bankAccountDTOList.stream()
                               .map(BankAccountDTO::getUser).collect(Collectors.toList()).containsAll(List.of("Paco1","Paco2")))
        );

    }

}
