package org.example.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
