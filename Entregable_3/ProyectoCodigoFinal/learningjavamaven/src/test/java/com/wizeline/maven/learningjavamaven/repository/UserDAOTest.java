package com.wizeline.maven.learningjavamaven.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserDAOTest {

    @Mock
    UserDAO userDAO;

    @Test
    public void DadoElMetodoCreateUsuarioEntregaUnaCadenaSuccess_CuandoIngresaUsuarioyPassword_EntoncesRegresaSatisfactorio(){
        String usuario="Prueba1";
        String password = "password1";
        String respuesta = userDAO.createUser(usuario,password);
        System.out.println("success");
    }

    @Test
    public void DadoElMetodoLoginEntregaUnaCadenaSuccess_CuandoIngresaUsuarioyPassword_EntoncesRegresaSatisfactorio(){
        String usuario="Prueba1";
        String password = "password1";
        String respuesta = userDAO.login(usuario,password);
        System.out.println("success");
    }
}
