package com.pratice.java.domain.utils;

import java.math.BigDecimal;

public final class Formatter {

    private Formatter() {
        throw new UnsupportedOperationException("Classe utilitária não deve ser instanciada");
    }

    public static String formatarCpfCnpj(String numero) {
        if (numero == null) return null;

        return switch (numero.length()) {
            case 11 -> numero.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            case 14 -> numero.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
            default -> numero;
        };
    }

    public static String extrairBase(String cpfCnpj) {
        return cpfCnpj.substring(0, cpfCnpj.length() - 2);
    }

    public static BigDecimal extrairFinal(String cpfCnpj) {
        return new BigDecimal(cpfCnpj.substring(cpfCnpj.length() - 2));
    }
}