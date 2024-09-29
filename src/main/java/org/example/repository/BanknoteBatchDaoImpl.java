package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.entity.Banknote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BanknoteBatchDaoImpl implements BanknoteBatchDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String UPDATE_SQL = "UPDATE banknote set count = count + ? WHERE denomination = ?";

    public void decrementBanknotesCount(List<Banknote> pickedBanknotes) {
        jdbcTemplate.batchUpdate(UPDATE_SQL, pickedBanknotes, pickedBanknotes.size(), (ps, banknote) -> {
            ps.setInt(1, -banknote.getCount());
            ps.setInt(2, banknote.getDenomination());
        });
    }

    public void incrementBanknotesCount(List<Banknote> addedBanknotes) {
        jdbcTemplate.batchUpdate(UPDATE_SQL, addedBanknotes, addedBanknotes.size(), (ps, banknote) -> {
            ps.setInt(1, banknote.getCount());
            ps.setInt(2, banknote.getDenomination());
        });
    }
}
