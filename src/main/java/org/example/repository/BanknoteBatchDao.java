package org.example.repository;

import org.example.entity.Banknote;

import java.util.List;

public interface BanknoteBatchDao {
    void decrementBanknotesCount(List<Banknote> pickedBanknotes);

    void incrementBanknotesCount(List<Banknote> addedBanknotes);
}
