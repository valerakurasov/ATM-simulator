package org.example.repository;

import org.example.entity.Banknote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BanknoteRepository extends JpaRepository<Banknote, Long> {
    List<Banknote> findAllByCountGreaterThanOrderByDenomination(int greaterThan);
}
