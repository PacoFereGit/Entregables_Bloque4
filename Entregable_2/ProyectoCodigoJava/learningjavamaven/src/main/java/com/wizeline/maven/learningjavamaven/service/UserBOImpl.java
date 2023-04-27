package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.model.ErrorDTO;
import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.repository.UserDAO;
import com.wizeline.maven.learningjavamaven.repository.UserDAOImpl;
import com.wizeline.maven.learningjavamaven.utils.Utils;

import java.util.logging.Logger;

public class UserBOImpl implements UserBO{
    private static final Logger LOGGER = Logger.getLogger(UserBOImpl.class.getName());

    @Override
    public ResponseDTO createUser(String user, String password){
        LOGGER.info("Inicia procesamiento en capa de negocio");
        ResponseDTO response = new ResponseDTO();
        String result = "fail";
        if(Utils.validateNullValue(user) && Utils.validateNullValue(password)){
            UserDAO userDao = new UserDAOImpl();
            result = userDao.createUser(user,password);
            response.setCode("OK000");
            response.setStatus(result);
        }else{
            response.setCode("OK000");
            response.setStatus(result);
            response.setErrors(new ErrorDTO("ER001","Error al crear usuario"));
        }
        return response;
    }

    @Override
    public ResponseDTO login (String user, String password){
        LOGGER.info("Inicia procesamiento en capa de negocio");
        ResponseDTO response = new ResponseDTO();
        String result ="";
        if(Utils.validateNullValue(user) && Utils.validateNullValue(password)){
            UserDAO userDao = new UserDAOImpl();
            result = userDao.login(user,password);
        }
        if("success".equals(result)){
            response.setCode("OK000");
            response.setStatus(result);
        }else{
            response.setCode("ER001");
            response.setErrors(new ErrorDTO("ER001",result));
            response.setStatus("fail");
        }
        return response;
    }

}
