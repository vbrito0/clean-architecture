package com.pratice.java.domain.utils;

import org.springframework.stereotype.Component;

@Component
public class Formatter {
    public static String formatarCpfCnpj(String numero) {
        if (numero == null) return null;

        return switch (numero.length()) {
            case 11 -> numero.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            case 14 -> numero.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
            default -> numero;
        };
    }
}