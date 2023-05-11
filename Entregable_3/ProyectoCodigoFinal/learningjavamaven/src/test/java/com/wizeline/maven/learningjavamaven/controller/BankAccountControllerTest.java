package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.enums.AccountType;
import com.wizeline.maven.learningjavamaven.enums.Country;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.service.BankAccountService;
import com.wizeline.maven.learningjavamaven.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountControllerTest {

    private static Logger LOGGER = Logger.getLogger(BankAccountControllerTest.class.getName());
    @Mock
    BankAccountService bankAccountService;

    @Mock
    UserService userService;

    @InjectMocks
    BankAccountController bankAccountController;

    @Test
    public void DadoServicioUserAccountEntregaDetalleCuentaUsuario_CuandoIngresaUsuarioyFechaUltimoUso_EntoncesRegresaDetalleCuentaUsuarioIngresado(){
        System.out.println("Test");
        System.out.println("DadoServicioUserAccountEntregaDetalleCuentaUsuario_CuandoIngresaUsuarioyFechaUltimoUso_EntoncesRegresaDetalleCuentaUsuarioIngresado()");
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.parse("01-01-2023", dateformatter);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountActive(true);
        bankAccountDTO.setUser("Paco");
        bankAccountDTO.setAccountBalance(876786l);
        bankAccountDTO.setAccountName("Debito");
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        bankAccountDTO.setCountry(Country.MX.toString());
        bankAccountDTO.setLastUsage(usage.atStartOfDay());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("success");
        responseDTO.setCode("OK000");
        String password = "password123";
        String lastUsage = "01-01-2013";

        when(userService.login(bankAccountDTO.getUser(),password)).thenReturn(responseDTO);
        when(bankAccountService.getAccountDetails(bankAccountDTO.getUser(),lastUsage)).thenReturn(bankAccountDTO);

        ResponseEntity<?> responseEntity = bankAccountController.getUserAccount(bankAccountDTO.getUser(),password,lastUsage);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );
    }

    @Test
    public void DadoServicioUserAccountEntregaMensajeError_CuandoIngresaFormatoFechaIncorrecto_EntoncesRegresaMensajeError(){
        System.out.println("Test");
        System.out.println("DadoServicioUserAccountDetailsEntregaMensajeError_CuandoIngresaFormatoFechaIncorrecto_EntoncesRegresaMensajeError()");
        String user= "Paco";
        String password = "paco123";
        String lastUsage="01/01/2023";
        String mensajeError = "Formato de fecha incorrecto";

        ResponseEntity<?> responseEntity = bankAccountController.getUserAccount(user,password,lastUsage);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(mensajeError, responseEntity.getBody())
        );

    }

    @Test
    public void DadoServicioGetAccountsEntregalistadoCuentas_CuandoSeEjecutaElServicio_EntoncesRegresaTodoElListadoDeCuentas(){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.parse("01-01-2023", dateformatter);
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountActive(true);
        bankAccountDTO.setUser("Paco");
        bankAccountDTO.setAccountBalance(876786l);
        bankAccountDTO.setAccountName("Debito");
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        bankAccountDTO.setCountry(Country.MX.toString());
        bankAccountDTO.setLastUsage(usage.atStartOfDay());
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        bankAccountDTOList.add(bankAccountDTO);

        when(bankAccountService.getAccounts()).thenReturn(bankAccountDTOList);
        ResponseEntity<?> responseEntity = bankAccountController.getAccounts();

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );
    }

    @Test
    public void DadoServicioGetAccountsPorTipoEntregalistadoCuentasAgrupadas_CuandoSeEjecutaElServicio_EntoncesRegresaTodoElListadoDeCuentasAgrupadas(){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.parse("01-01-2023", dateformatter);
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountActive(true);
        bankAccountDTO.setUser("Paco");
        bankAccountDTO.setAccountBalance(876786l);
        bankAccountDTO.setAccountName("Debito");
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        bankAccountDTO.setCountry(Country.MX.toString());
        bankAccountDTO.setLastUsage(usage.atStartOfDay());
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        bankAccountDTOList.add(bankAccountDTO);

        when(bankAccountService.getAccounts()).thenReturn(bankAccountDTOList);
        ResponseEntity<Map<String, List<BankAccountDTO>>> responseEntity = bankAccountController.getAccountsGroupByType();

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );
    }

    @Test
    public void DadoServicioSayHelloGuestEntregaStringSaludos_CuandoSeEjecutaElServicio_EntoncesRegresaSaludoAlCliente(){
        ResponseEntity<String> responseEntity = bankAccountController.sayHelloGuest("Paco");
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );

    }


    @Test
    void DadoServicioexecuteApiExternaEntregaEstatusOk_CuandoSeEjecutaServicio_EntoncesRegresaStrinConCOntenidoApiExterna(){
        ResponseEntity<String> responseEntity = bankAccountController.executeApiExterna();

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );
    }

    @Test
    void DadoServicioDeleteAccountsEntregaEstatusOK_CuandoSeEjcutaServicio_EntoncesRegresaEstatusOK(){
        bankAccountController.deleteAccounts();
        ResponseEntity responseEntity = new ResponseEntity("Todas las cuentas borradas",HttpStatus.OK);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );

    }

    @Test
    public void DadoServicioUpdateAccountsEntregaListaActualizada_CuandoSeEjecutaServicioPorUsuario_EntoncesRegresaestatusOK(){
        String user = "paco1";
        bankAccountController.updateAccounts(user);
        ResponseEntity responseEntity = new ResponseEntity("Se actualiza accountName del usuario ".concat(user),HttpStatus.OK);
        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );

    }

    @Test
    public void DadoServicioGetAccountsByUserEntregaCuentaSeleccionada_CuandoSeEjecutaServicioPorUsuario_EntoncesRegresaCuentaSeleccionada(){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.parse("01-01-2023", dateformatter);
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountActive(true);
        bankAccountDTO.setUser("Paco");
        bankAccountDTO.setAccountBalance(876786l);
        bankAccountDTO.setAccountName("Debito");
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        bankAccountDTO.setCountry(Country.MX.toString());
        bankAccountDTO.setLastUsage(usage.atStartOfDay());
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        bankAccountDTOList.add(bankAccountDTO);

        when(bankAccountService.getAccountByUser(bankAccountDTO.getUser())).thenReturn(bankAccountDTOList);

        ResponseEntity<List<BankAccountDTO>> listResponseEntity = bankAccountController.getAccountByUser(bankAccountDTO.getUser());
        assertAll(
                () -> assertNotNull(listResponseEntity),
                () -> assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode())
        );

    }

}
