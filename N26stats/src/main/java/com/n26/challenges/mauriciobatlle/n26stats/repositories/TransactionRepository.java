package com.n26.challenges.mauriciobatlle.n26stats.repositories;


import org.springframework.data.repository.CrudRepository;

import com.n26.challenges.mauriciobatlle.n26stats.pojos.persistence.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {




}
