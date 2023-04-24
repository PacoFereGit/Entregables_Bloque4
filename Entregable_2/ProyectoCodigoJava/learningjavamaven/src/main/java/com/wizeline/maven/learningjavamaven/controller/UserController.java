package com.wizeline.maven.learningjavamaven.controller;

import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import com.wizeline.maven.learningjavamaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    String msgProcPeticion = "LearningJava - Inicia procesamiento de peticion UserController...";

    @GetMapping(value="/login", produces = "application/json")
    public ResponseEntity<ResponseDTO> login(@RequestParam String user, String password){
        LOGGER.info("LearningJava - Procesando petición http de tipo get - Starting ... /login");
        ResponseDTO response = new ResponseDTO();
        response = userService.login(user,password);
        LOGGER.info("Login  -  Completado");
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    @PostMapping(value="/createUser", produces = "application/json")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO request){
        LOGGER.info("LearningJava - Procesando petición http de tipo POST - Starting ... /createUser");
        ResponseDTO response = new ResponseDTO();
        response = userService.createUser(request.getUser(),request.getPassword());
        LOGGER.info("createUser  - Completado");
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

    }

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
