package org.example.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.BanknoteMapper;
import org.example.controller.response.BanknoteResponse;
import org.example.service.BanknoteDto;
import org.example.service.BanknoteService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ATMControllerImpl implements ATMController {
    private final BanknoteService banknoteService;
    private final BanknoteMapper banknoteMapper;

    @Override
    public void putBanknotes(Map<Integer, Integer> putBanknotesRequest) {
        banknoteService.addBanknote(putBanknotesRequest.entrySet().stream().map(e -> BanknoteDto.builder()
                        .denomination(e.getKey())
                        .count(e.getValue())
                        .build())
                .toList());
    }

    @Override
    @Transactional
    public List<BanknoteResponse> getBanknotes(int sum) { // map to response
        return banknoteService.getBanknotes(sum).stream().map(banknoteMapper::banknoteDtoToBanknoteResponse).toList();
    }
}
