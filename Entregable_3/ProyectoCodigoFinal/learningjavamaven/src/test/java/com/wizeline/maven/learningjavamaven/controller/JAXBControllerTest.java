package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.XmlBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JAXBControllerTest {

    @InjectMocks
    JAXBController jaxbController;

    @Test
    public void DadoServicioLoginUserEntregaUnEstatusOK_CuandoObtieneUnLogin_EntoncesRegresaCodigoOK(){

        ResponseEntity<XmlBean> responseEntityXmlBean = jaxbController.loginUser();

        assertAll(
                () -> assertNotNull(responseEntityXmlBean),
                () -> assertEquals(HttpStatus.OK, responseEntityXmlBean.getStatusCode())
        );

    }


}
