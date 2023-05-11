package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.model.CardDTO;

import java.util.List;

public interface BankCardService {
    List<CardDTO> getCards();
}
