package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Класс, представляющий банкноту.
 *
 * @author Valera
 * @version 1.0
 */
@Data
@Entity
public class Banknote {
    @Id
    private long id;
    /**
     * Поле, представляющее номинал банкноты.
     */
    @Column
    private int denomination;
    /**
     * Поле, представляющее количество банкнот определенного номинала.
     */
    @Column
    private int count;
}
