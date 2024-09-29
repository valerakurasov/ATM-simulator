package org.example.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BanknoteDto {
    private int denomination;
    private int count;
}
