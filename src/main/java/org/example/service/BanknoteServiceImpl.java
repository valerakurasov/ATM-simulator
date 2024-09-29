package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.BanknoteMapper;
import org.example.entity.Banknote;
import org.example.exception.InsufficientFundsException;
import org.example.repository.BanknoteBatchDaoImpl;
import org.example.repository.BanknoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BanknoteServiceImpl implements BanknoteService {

    private final BanknoteRepository banknoteRepository;
    private final BanknoteBatchDaoImpl banknoteBatchDao;
    private final BanknoteMapper banknoteMapper;

    /**
     * Этот метод выдает банкноты на заданную сумму.
     *
     * @param sum сумма, которую необходимо выдать.
     * @return лист банкнот с количеством выдаваемых купюр каждого номинала.
     *
     */

    public List<BanknoteDto> getBanknotes(int sum) {
        List<Banknote> availableBanknotes = banknoteRepository.findAllByCountGreaterThanOrderByDenomination(0);
        List<Banknote> pickedBanknotes = chooseBanknotesForPickuping(availableBanknotes, sum);

        try {
            banknoteBatchDao.decrementBanknotesCount(pickedBanknotes);
        } catch (Exception e) {
            throw new RuntimeException("Невозможно выбрать банкноты.", e);
        }

        log.info("Текущее состояние банкомата [{}]", banknoteRepository.findAll());

        return pickedBanknotes.stream()
                .map(banknoteMapper::banknoteToBanknoteDto)
                .toList();
    }

    /**
     * Этот метод добавляет банкноты.
     *
     * @param banknoteDto это банкноты, которые необходимо добавить.
     *
     */
    public void addBanknote(List<BanknoteDto> banknoteDto) {
        List<Banknote> banknotesForAddition = banknoteDto.stream().map(banknoteMapper::banknoteDtoToBanknote).toList();
        banknoteBatchDao.incrementBanknotesCount(banknotesForAddition);
        log.info("Добавленные купюры [{}]", banknoteDto);
        List<Banknote> currentBanknotes = banknoteRepository.findAll();
        log.info("Текущее состояние банкомата [{}]", currentBanknotes);
    }

    /**
     * Этот метод по определенной формуле определяет купюры на выдачу, начиная от наибольшего номинала к наименьшему.
     *
     * @param banknotes это банкноты, которые имеются в наличии.
     * @param target сумма, которую необходимо выдать.
     * @return лист отобранных на выдачу банкнот.
     * @throws InsufficientFundsException в случае невозможности сбора точной суммы.
     *
     */

    private List<Banknote> chooseBanknotesForPickuping(List<Banknote> banknotes, int target) {
        int remains = target;

        List<Banknote> pickedBanknotes = new ArrayList<>();
        int[] usedBanknotes = new int[banknotes.size()];

        for (int i = banknotes.size() - 1; i >= 0; i--) {
            Banknote banknote = banknotes.get(i);
            int count = Math.min(remains / banknote.getDenomination(), banknote.getCount());
            usedBanknotes[i] = count;
            remains -= count * banknote.getDenomination();
        }

        if (remains > 0) {
            log.warn("Невозможно собрать точную сумму: {}", target);
            throw new InsufficientFundsException("Невозможно собрать точную сумму: " + target);
        }

        log.info("Выданные купюры:");

        for (int i = 0; i < banknotes.size(); i++) {
            if (usedBanknotes[i] > 0) {
                Banknote banknote = banknotes.get(i);
                banknote.setCount(usedBanknotes[i]);
                pickedBanknotes.add(banknote);
                log.info("Купюра номиналом: {}, количество: {}", banknotes.get(i).getDenomination(), usedBanknotes[i]);
            }
        }
        return pickedBanknotes;
    }
}

