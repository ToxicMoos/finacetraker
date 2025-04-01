package com.financial_tracker.financialtracker_springboot.service;

import com.financial_tracker.financialtracker_springboot.dto.TransactionRequest;
import com.financial_tracker.financialtracker_springboot.dto.TransactionResponse;
import com.financial_tracker.financialtracker_springboot.dto.UserResponse;
import com.financial_tracker.financialtracker_springboot.model.MyTransaction;
import com.financial_tracker.financialtracker_springboot.model.UserEntity;
import com.financial_tracker.financialtracker_springboot.repository.TransactionRepository;
import com.financial_tracker.financialtracker_springboot.repository.UserRepository;
import com.financial_tracker.financialtracker_springboot.validation.Validation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final Validation validate;

//    public MyTransaction createTransaction(MyTransaction myTransaction) {
//        validate.validateTransaction(myTransaction);
//
//        if (myTransaction.getUser() == null || myTransaction.getUser().getId() == null) {
//            throw new IllegalArgumentException("User ID is required");
//        }
//
//        UserEntity user = userRepository.findById(myTransaction.getUser().getId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        myTransaction.setUser(user); // Устанавливаем пользователя
//        return transactionRepository.save(myTransaction);
//    }

    public TransactionResponse createTransaction(TransactionRequest transactionRequest){
        MyTransaction myTransaction = new MyTransaction();

        myTransaction.setAmount(transactionRequest.getAmount());
        myTransaction.setDate(LocalDate.now());
        myTransaction.setType(transactionRequest.getType());
        myTransaction.setCategory(transactionRequest.getCategory());
        myTransaction.setDescription(transactionRequest.getDescription());

        UserEntity user = userRepository.findById(transactionRequest.getUserId()).orElseThrow();
        myTransaction.setUser(user);
        MyTransaction savedTrans = transactionRepository.save(myTransaction);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(savedTrans.getId());
        transactionResponse.setAmount(savedTrans.getAmount());
        transactionResponse.setDate(savedTrans.getDate());
        transactionResponse.setType(savedTrans.getType());
        transactionResponse.setCategory(savedTrans.getCategory());
        transactionResponse.setDescription(savedTrans.getDescription());

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setUserName(user.getUserName());
        userResponse.setId(user.getId());
        transactionResponse.setUserResponse(userResponse);


        return transactionResponse;
    }


    public List<MyTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public MyTransaction getTransactionById(Long id) {
        validate.validateId(id);
        return transactionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Транзакция с ID " + id + " не найдена"));
    }

    public MyTransaction updateTransaction(Long id, MyTransaction updatedTransaction) {
        validate.validateId(id);
        validate.validateTransaction(updatedTransaction);
        MyTransaction myTransaction = transactionRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Транзакция с ID " + id + " не найдена "));

        if (updatedTransaction.getAmount() != null) {
            myTransaction.setAmount(updatedTransaction.getAmount());
        }
        if (updatedTransaction.getDate() != null) {
            myTransaction.setDate(updatedTransaction.getDate());
        }
        if (updatedTransaction.getType() != null) {
            myTransaction.setType(updatedTransaction.getType());
        }
        if (updatedTransaction.getCategory() != null) {
            myTransaction.setCategory(updatedTransaction.getCategory());
        }
        if (updatedTransaction.getDescription() != null) {
            myTransaction.setDescription(updatedTransaction.getDescription());
        }

        return transactionRepository.save(myTransaction);
    }

    public void deleteTransactionById(Long id) {
        validate.validateId(id);
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException(" Транзакция с ID " + id + " не найдена ");
        }
        transactionRepository.deleteById(id);
    }

}
