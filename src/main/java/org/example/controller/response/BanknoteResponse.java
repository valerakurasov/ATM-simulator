package org.example.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BanknoteResponse {
    private int denomination;
    private int count;
}
