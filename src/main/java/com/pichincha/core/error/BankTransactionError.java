package com.pichincha.core.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class BankTransactionError {
    public static ResponseStatusException TRANSACTION_ERROR_ACCOUNT_NOT_FOUND
            = new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede ejecutar la transaccion, no se encontro la cuenta");
   public static ResponseStatusException TRANSACTION_ERROR_INSUFFICIENT_BALANCE
            = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Insuficiente saldo para ejecutar la transaccion");
}