package com.n26.challenges.mauriciobatlle.n26stats.services.interfaces;

import com.n26.challenges.mauriciobatlle.n26stats.pojos.persistence.Transaction;

public interface AsyncInterface {
	void AddTransactionRecord(Transaction currentTransaction);
	void UpdateStats();

}
