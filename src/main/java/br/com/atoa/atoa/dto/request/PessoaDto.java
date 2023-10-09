package br.com.atoa.atoa.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDto {
    private String nome;
    private String cpf;
    private String rg;
    private String cep;
}
