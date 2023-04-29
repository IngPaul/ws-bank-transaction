package Util;

import com.pichincha.core.model.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class MockData {
    public static ServerWebExchange buildServerWebExchange() {
        MockServerHttpRequest request = MockServerHttpRequest.get("http://localhost")
                .header("X-CF-Forwarded-Url", "https://example.com")
                .header("Accept", "application/json")
                .build();
        return MockServerWebExchange.from(request);
    }


        public static Customer getCustomer() {
            Customer customer = new Customer();
            customer.setCustomerId(1L);
            customer.setName("John Doe");
            customer.setGender("male");
            customer.setAge(35);
            customer.setIdentification("123456789");
            customer.setAddress("123 Main St.");
            customer.setPhone("555-555-1234");
            customer.setPassword("password");
            customer.setStatus("active");
            return customer;
        }

        public static AccountType getAccountType() {
            AccountType accountType = new AccountType();
            accountType.setAccountTypeId(1L);
            accountType.setDetailType("Checking");
            return accountType;
        }

        public static Account getAccount() {
            Account account = new Account();
            account.setAccountId(1L);
            account.setAccountNumber("1234567890");
            account.setInitialBalance(BigDecimal.valueOf(1000.0));
            account.setStatus("active");
            account.setAccountTypeId(1L);
            account.setCustomerId(1L);
            return account;
        }

        public static TransactionType getTransactionType() {
            TransactionType transactionType = new TransactionType();
            transactionType.setTransactionTypeId(1L);
            transactionType.setDetailType("Deposit");
            return transactionType;
        }

        public static Transaction getTransaction() {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(1L);
            transaction.setDate(OffsetDateTime.now());
            transaction.setTransactionTypeId(Transaction.TransactionTypeIdEnum.fromValue(1L));
            transaction.setValue(BigDecimal.valueOf(100.0));
            transaction.setBalance(BigDecimal.valueOf(1100.0));
            transaction.setAccountId(1L);
            return transaction;
        }

        public static TransactionRequest getTransactionRequest() {
            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setTransactionId(1L);
            transactionRequest.setDate(OffsetDateTime.now());
            transactionRequest.setTransactionTypeId(TransactionRequest.TransactionTypeIdEnum.fromValue(1L));
            transactionRequest.setValue(BigDecimal.valueOf(100.0));
            transactionRequest.setAccountNumber("1234567890");
            return transactionRequest;
        }

        public static PostAccountStatusResponse getPostAccountStatusResponse() {
            PostAccountStatusResponse postAccountStatusResponse = new PostAccountStatusResponse();
            postAccountStatusResponse.setDate(String.valueOf(OffsetDateTime.now()));
            postAccountStatusResponse.setCustomer("John Doe");
            postAccountStatusResponse.setAccountNumber("1234567890");
            postAccountStatusResponse.setDetailTransactionType("Deposit");
            postAccountStatusResponse.setInitialValue(BigDecimal.valueOf(1000.0));
            postAccountStatusResponse.setValue(BigDecimal.valueOf(100.0));
            postAccountStatusResponse.setBalance(BigDecimal.valueOf(1100.0));
            return postAccountStatusResponse;
        }

        public static PostAccountStatusRequest getPostAccountStatusRequest() {
            PostAccountStatusRequest postAccountStatusRequest = new PostAccountStatusRequest();
            postAccountStatusRequest.setDateStart(OffsetDateTime.now());
            postAccountStatusRequest.setDateEnd(OffsetDateTime.now());
            postAccountStatusRequest.setCustomerId(1L);
            return postAccountStatusRequest;
        }

    public static com.pichincha.core.domain.Transaction getTransactionRepository() {
        return new com.pichincha.core.domain.Transaction(1L, LocalDateTime.now(), 2L, BigDecimal.valueOf(100), BigDecimal.valueOf(500), 3L);
    }

    public static com.pichincha.core.domain.Account getAccountRepository() {
        com.pichincha.core.domain.Account account = new com.pichincha.core.domain.Account();
        account.setAccountId(1234L);
        account.setCustomerId(5678L);
        account.setAccountNumber("1234567890");
        account.setAccountTypeId(1L);
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setStatus("Active");
        return account;
    }

    public static com.pichincha.core.domain.Customer getCustomerRepository() {
        com.pichincha.core.domain.Customer customer = new com.pichincha.core.domain.Customer();
        customer.setCustomerId(1L);
        customer.setName("Juan");
        customer.setAddress("Av Loj.");
        customer.setGender("Masculino");
        customer.setPassword("secreto123");
        customer.setStatus("active");
        return customer;
    }
}
