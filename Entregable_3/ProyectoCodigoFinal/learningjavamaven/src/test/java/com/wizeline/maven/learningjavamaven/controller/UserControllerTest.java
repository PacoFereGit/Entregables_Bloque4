package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import com.wizeline.maven.learningjavamaven.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest{

    private static final Logger LOGGER = Logger.getLogger(UserControllerTest.class.getName());

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    public void DadoServicioUserServiceLoginEntregaUnResposeSuccess_CuandoObtieneUnLoginPorUsuarioPassword_EntoncesRegresaCodigoOK000(){
        String user = "admin";
        String password = "password123";
        ResponseDTO response = new ResponseDTO();
        response.setCode("OK000");
        response.setStatus("success");
        when(userService.login(user,password)).thenReturn(response);

        final ResponseEntity<ResponseDTO> responseEntity = userController.login(user, password);

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );
    }

    @Test
    public void DadoServicioUserServiceCreateUserEntragaUnResponseSuccess_CuandoRegistraUsuario_EntoncesRegresaCodigoOK000(){
        System.out.println("Test");
        System.out.println("DadoServicioUserServiceCreateUserEntragaUnResponseSuccess_CuandoRegistraUsuario_EntoncesRegresaCodigoOK000()");
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("admin");
        userDTO.setPassword("password123");
        ResponseDTO response = new ResponseDTO();
        response.setCode("OK000");
        response.setStatus("success");

        when(userService.createUser(userDTO.getUser(),userDTO.getPassword())).thenReturn(response);

        final ResponseEntity<ResponseDTO> responseEntity = userController.createUser(userDTO);

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
        );
    }


    @Test
    public void DadoServicioUserServiceCreateUserConUsuarioOPasswordNullEntragaUnResponseError_CuandoRegistraUsuario_EntoncesRegresaErrorAlCrearUsuario(){
        System.out.println("Test");
        System.out.println("DadoServicioUserServiceCreateUserConUsuarioOPasswordNullEntragaUnResponseError_CuandoRegistraUsuario_EntoncesRegresaErrorAlCrearUsuario()");
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("admin");
        userDTO.setPassword(null);
        ResponseDTO response = new ResponseDTO();
        response.setCode("ER001");
        response.setStatus("fail");

        when(userService.createUser(userDTO.getUser(),userDTO.getPassword())).thenReturn(response);

        final ResponseEntity<ResponseDTO> responseEntity = userController.createUser(userDTO);

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(response.getStatus(), responseEntity.getBody().getStatus())
        );
    }

}

