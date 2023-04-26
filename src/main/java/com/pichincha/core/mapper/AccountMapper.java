package com.pichincha.core.mapper;

import com.pichincha.core.domain.Account;
import com.pichincha.core.domain.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {


    Account toAccountDomain(com.pichincha.core.model.Account customerDTO);

    com.pichincha.core.model.Account toAccountModel(Account customer);

}

