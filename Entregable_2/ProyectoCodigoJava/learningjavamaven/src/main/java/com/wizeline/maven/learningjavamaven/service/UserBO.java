package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.model.ResponseDTO;

public interface UserBO {
    ResponseDTO createUser(String user, String password);
    ResponseDTO login(String user, String password);

}
