package org.example.controller.response;

import lombok.Builder;
import lombok.Data;
/**
 * Данный класс содержит в себе информацию о банкнотах на выдачу непосредственно пользователям.
 * Создан с целью обеспечения большего контроля над передаваемыми наружу данными
 *
 * @author Valera
 * @version 1.0
 */
@Data
@Builder
public class BanknoteResponse {
    private int denomination;
    private int count;
}
