package com.wizeline.maven.learningjavamaven.iterator;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

public interface Iterator {
    boolean hasNext();

    BankAccountDTO next();

}
