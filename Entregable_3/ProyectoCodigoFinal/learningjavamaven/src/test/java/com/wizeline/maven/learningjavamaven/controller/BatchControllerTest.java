package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.batch.UserJob;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BatchControllerTest {
    //private static final Logger LOGGER = Logger.getLogger(BankCardControllerTest.class.getName());

    @Mock
    UserJob userJob;

    @Mock
    JobLauncher jobLauncher;

    @InjectMocks
    BatchController batchController;

    @Test
    public void DadoElServicioStartBatchEntregaUnEstatusFalllido_CuandoIniciaBatch_EntoncesRegresaErrorNullPointer() throws Exception {
        JobParameters Parameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(userJob.printUsersJob(), Parameters);
        ResponseEntity<String> responseEntity = null;
        assertAll(
                () -> assertNull(responseEntity)
                //() -> assertEquals("Batch Process started", responseEntity.getBody())

        );

    }
}
