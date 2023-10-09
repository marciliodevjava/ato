package br.com.atoa.atoa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDtoRespose {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private EnderecoDtoResponse endereco;
}
