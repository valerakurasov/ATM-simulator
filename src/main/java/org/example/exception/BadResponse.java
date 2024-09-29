package org.example.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BadResponse {
    private List<String> messages;
}
