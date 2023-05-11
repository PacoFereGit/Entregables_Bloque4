package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

import java.util.List;

public interface BankAccountService{
    /**
     * Gets accounts.
     *
     * @return todas las cuentas existentes en formato de lista.
     */
    List<BankAccountDTO> getAccounts();

    /**
     * Gets account details.
     *
     * @param user      nombre de usuario.
     * @param lastUsage último uso de la cuenta.
     * @return detalles de una sola cuenta.
     */
    BankAccountDTO getAccountDetails(String user, String lastUsage);

    //Borrar registros en Mongo
    void deleteAccounts();

    //Actualizar registro en Mongo
    void updateAccounts(String user);

    //Realizar Query
    List<BankAccountDTO> getAccountByUser(String user);


}
