package com.wizeline.maven.learningjavamaven.utils;

import com.wizeline.maven.learningjavamaven.enums.AccountType;
import com.wizeline.maven.learningjavamaven.enums.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class UtilsTest {

    @Test
    public void DadoLaFuncionGetStringEntregaCadena_CuandoObtieneValorParametroString_EntoncesRegresaMssmaCadenaIngresada(){
        String parametro = "Hola";
        String respuesta = Utils.getString(parametro);
        assertEquals(respuesta,parametro);
    }

    @Test
    public void DadoLaFuncionvalidateNullValueEntregaValorBoolean_CuandoObtieneValorDiferenteNullComoParametro_EntoncesRegresaTrue(){
        String parametro = "Ejemplo";
        Boolean respuesta = Utils.validateNullValue(parametro);
        assertEquals(true,respuesta);
    }

    @Test
    public void DadoLaFuncionRandomAccountNumberEntregaValorLongAleatorio_CuandoSeEjecutaFuncion_EntoncesRegresaValorLongAleatorio(){
        long respuesta = Utils.randomAccountNumber();
        System.out.println(respuesta);
    }

    @Test
    public void DadoLaFuncionRandomBalanceEntregaValorDoubleAleatorio_CuandoEjecutaLaFuncion_EntoncesRegresaValorDoubleAleatorio(){
        double respuesta = Utils.randomBalance();
        System.out.println(respuesta);
    }

    @Test
    public void DadoLaFuncionAccountTypeEntreUnTipoDeCuenta_CuandoEjecutaLaFuncion_EntonceRegresaNOMINAoAHORROoPLATINUM(){
        AccountType accountType = Utils.pickRandomAccountType();
        System.out.println(accountType);
    }

    @Test
    public void DadoLaFuncionRandomIntEntregaUnNumeroAleatorio_CuandoEjecutaLaFuncion_EntoncesRegresaUnNUmeroAleatoioDel1al10(){
        String respuesta = Utils.randomInt();
        System.out.println(respuesta);
    }

    @Test
    public void DadoLaFuncionGetCountryEntregaUnNombrePais_CuandoEjecutaLaFuncionMetiendoComoParametroMX_EntoncesRegresaMexico(){
        String country = Utils.getCountry(Country.MX);
        System.out.println(country);
    }

    @Test
    public void DadoLaFuncionisDateFormatValidEntreUnBoolean_CuandoEjecutaLaFuncionConUnParametroValido_EntoncesRegresaTrue(){
        boolean valida = Utils.isDateFormatValid("17-01-2023");
        System.out.println("respuesta "+valida);
    }

    @Test
    public void DadoLaFuncionIsPasswordValidEntregaUnBoolen_CuandoEjecutaLaFuncionValidaElFormatoDelPasswordIncorrecto_EntoncesRegresaFalse(){
        boolean valida = Utils.isPasswordValid("Pass");
        System.out.println(valida);
    }


}
