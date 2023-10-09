package br.com.atoa.atoa.utils;

import br.com.atoa.atoa.domain.Endereco;
import br.com.atoa.atoa.mapper.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PesquisaEnderecoUtils {
    private final String PRIMEIRO = "https://viacep.com.br/ws/";
    private final String SEGUNDO = "/json/";
    @Autowired
    private EnderecoMapper enderecoMapeaia;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FormatadorDados formatadorDados;

    public Endereco pesquisarCep(String cep) {
        String dado = formatadorDados.formatacaoCep(cep);
        String endereco = PRIMEIRO + dado + SEGUNDO;
        try {
            Object body = restTemplate.getForObject(endereco, Object.class);
            Endereco end;
            end = enderecoMapeaia.mapearCep(body);
            return end;
        } catch ( Exception ex) {

        }
        return null;
    }
}
