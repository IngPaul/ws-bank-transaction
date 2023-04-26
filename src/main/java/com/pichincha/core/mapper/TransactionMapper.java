package com.pichincha.core.mapper;



import com.pichincha.core.domain.Account;
import com.pichincha.core.domain.Transaction;
import com.pichincha.core.model.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.*;

@Mapper(componentModel = "spring", imports = OffsetDateTime.class)
public interface TransactionMapper {

    //Transaction toTransactionDomain(com.pichincha.core.model.Transaction customerDTO);
    @Mapping(target = "transactionTypeId", expression = "java(getEnum(transaction.getTransactionTypeId()))")
    @Mapping(target = "date", expression = "java(parseOffsetDateTime(transaction.getDate()))")
    com.pichincha.core.model.Transaction toTransactionModel(Transaction transaction);
    default com.pichincha.core.model.Transaction.TransactionTypeIdEnum getEnum(Long transactionTypeId){
       return com.pichincha.core.model.Transaction.TransactionTypeIdEnum.fromValue(transactionTypeId);
    }
    default OffsetDateTime parseOffsetDateTime(LocalDateTime date){
        OffsetDateTime offsetDateTime = date.atOffset(ZoneOffset.systemDefault().getRules().getOffset(date));
        return  offsetDateTime;
    }

    @Mapping(target = "transactionTypeId", expression = "java(transaction.getTransactionTypeId().getValue())")
    @Mapping(target = "date", expression = "java(transaction.getDate().toLocalDateTime())")
    @Mapping(target = "value", source = "transaction.value")
    @Mapping(target = "balance", source = "balance1")
    @Mapping(target = "accountId", source = "account.accountId")
    com.pichincha.core.domain.Transaction toTransactionDomain(TransactionRequest transaction, Account account, BigDecimal balance1);

}

