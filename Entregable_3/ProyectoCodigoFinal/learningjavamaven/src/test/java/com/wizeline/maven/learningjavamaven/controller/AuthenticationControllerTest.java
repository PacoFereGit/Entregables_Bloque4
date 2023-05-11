package com.wizeline.maven.learningjavamaven.controller;

//import com.wizeline.maven.learningjavamaven.config.JwtTokenConfig;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

//import java.util.logging.Logger;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    //@Mock
    // JwtTokenConfig jwtTokenConfig;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    AuthenticationController authenticationController;

   /*@Test
    public void DadoServicioUserDetailsEntregaUserNameNotFound_CuandoObtieneTokenPorUsuario_EntoncesRegresaNoAutorizado() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("guest");
        userDTO.setPassword("password");

        userDetailsService.loadUserByUsername(userDTO.getUser());
       //ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

       ResponseEntity responseEntity = authenticationController.getAuthenticationToken(userDTO);



        //when(authenticationController.getAuthenticationToken(userDTO)).thenReturn(throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
        //then(responseEntity);

       //assertAll(
           //    () -> assertNotNull(responseEntity),
         //    () -> assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getBody())
       //);

        //ResponseEntity.unprocessableEntity();
        //ResponseEntity.noContent();


       //throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")




    }*/
}
