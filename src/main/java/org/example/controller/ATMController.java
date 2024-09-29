package org.example.controller;

import jakarta.validation.constraints.PositiveOrZero;
import org.example.validator.Denomination;
import org.example.controller.response.BanknoteResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Проект представляет симулятор банкомата.
 * Принимает купюры POST /currencies
 * тело запроса включает информацию о количестве купюр каждого номинала (10, 50, 100, 200 500, 1000, 2000, 5000)
 * Выдает купюры GET /currencies/{sum}
 * Ответ включает информацию о количестве купюр каждого номинала для выдачи
 * (10, 50, 100, 200 500, 1000, 2000, 5000)
 * @author Valera
 * @version 1.0
 */
@RequestMapping("/currencies")
@Validated
public interface ATMController {
    @PostMapping
    void putBanknotes(@RequestBody Map<@Denomination Integer, @PositiveOrZero Integer> putBanknotesRequest);

    @GetMapping("/{sum}")
    List<BanknoteResponse> getBanknotes(@PathVariable int sum);
}
