package org.example.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;
/**
 * Данный класс предназначен для формирования сообщения о возникших ошибках для дальнейшей передачи непосредственно пользователям.
 * @author Valera
 * @version 1.0
 */
@Builder
@Data
public class BadResponse {
    private List<String> messages;
}
