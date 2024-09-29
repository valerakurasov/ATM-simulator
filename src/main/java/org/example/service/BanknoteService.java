package org.example.service;

import java.util.List;

public interface BanknoteService {
    List<BanknoteDto> getBanknotes(int sum);

    void addBanknote(List<BanknoteDto> banknoteDto);
}
