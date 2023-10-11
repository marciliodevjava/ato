package br.com.atoa.atoa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDadosDtoResponse {
    private List<PessoaDtoRespose> pessoa;
}
