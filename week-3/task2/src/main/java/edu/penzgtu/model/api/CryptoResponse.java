package edu.penzgtu.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
@AllArgsConstructor
@NonNull
@Getter
public class CryptoResponse {
    private long time;
    private double open;
}