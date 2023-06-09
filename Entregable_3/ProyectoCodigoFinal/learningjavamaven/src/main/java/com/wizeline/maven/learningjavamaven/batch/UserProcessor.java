package com.wizeline.maven.learningjavamaven.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UserProcessor implements ItemProcessor<String,String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProcessor.class);

    @Override
    public String process(String data) throws Exception {
        LOGGER.info("UserProcessor: Processing data: {}", data);
        data = data.toUpperCase();
        return data;
    }//termina metodo process

}//termina clase UserProcessor
