package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import com.wizeline.maven.learningjavamaven.service.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@Tag(name = "Login", description = "Acceso y Creacion de Usuarios .")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    private final Bucket bucket;

    public UserController(){
        Refill refill = Refill.intervally(5, Duration.ofMinutes(1));
        Bandwidth limit =Bandwidth.classic(5,refill);
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    String msgProcPeticion = "LearningJava - Inicia procesamiento de peticion UserController...";

    @Operation(summary = "Loguea Usuarios")
    @GetMapping(value="/login", produces = "application/json")
    public ResponseEntity<ResponseDTO> login(@RequestParam String user, String password){
        //Logica para limitar las peticiones.
        if(bucket.tryConsume(1)) {
            LOGGER.info("LearningJava - Procesando petición http de tipo get - Starting ... /login");
            ResponseDTO response = new ResponseDTO();
            response = userService.login(user, password);
            LOGGER.info("Login  -  Completado");
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }

    @PostMapping(value="/createUser", produces = "application/json")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO request){
            LOGGER.info("LearningJava - Procesando petición http de tipo POST - Starting ... /createUser");
            ResponseDTO response = new ResponseDTO();
            response = userService.createUser(request.getUser(),request.getPassword());
            LOGGER.info("createUser  - Completado");
            return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

    @Operation(summary = "Alta de usuarios nuevos para el aplicativo")
    @PostMapping(value="/createUsers", produces = "application/json")
    public ResponseEntity<List<ResponseDTO>> createUsersAccount(@RequestBody List<UserDTO> userDTOList){
        LOGGER.info(msgProcPeticion);
        AtomicReference<ResponseDTO> response = new AtomicReference<>(new ResponseDTO());
        List<ResponseDTO> responseList = new ArrayList<>();

        userDTOList.stream().forEach(userDTO -> {
            String user = userDTO.getUser();
            String password = userDTO.getPassword();
            response.set(userService.createUser(user,password));
            responseList.add(response.get());
        });
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");

        return new ResponseEntity<List<ResponseDTO>>(responseList, responseHeaders, HttpStatus.OK);
    }

}
