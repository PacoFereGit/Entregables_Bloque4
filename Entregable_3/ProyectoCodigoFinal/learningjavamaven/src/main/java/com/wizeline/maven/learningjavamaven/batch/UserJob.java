package com.wizeline.maven.learningjavamaven.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job printUsersJob() {
        return jobBuilderFactory.get("printUsersJob")
                .incrementer(new RunIdIncrementer())
                .flow(printUserStep())
                .end().listener(new BatchJobCompletionListener())
                .build();

    }//termina metodo printUsersJob

    @Bean
    public Step printUserStep() {
        return stepBuilderFactory.get("printUserStep")
                .<String, String>chunk(3)
                .reader(new UserReader())
                .processor(new UserProcessor())
                .writer(new UserWriter())
                .build();
    }//termina metodo printUserStep

}//termina clase UserJob
