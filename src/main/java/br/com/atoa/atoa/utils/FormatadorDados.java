package br.com.atoa.atoa.utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FormatadorDados {
    private final List<CharSequence> LIST_CHAR = Arrays.asList(".", "-", " ");

    public String formatacaoCep(String cep) {
        for (int i = 0; i < LIST_CHAR.size(); i++) {
            cep = cep.replace(LIST_CHAR.get(i), "").trim();
        }
        return cep;
    }
}
