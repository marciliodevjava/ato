package br.com.atoa.atoa.mapper;

import br.com.atoa.atoa.domain.Pessoa;
import br.com.atoa.atoa.dto.request.PessoaDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PessoaMapper {
    public Pessoa mapearPessoaDtoParaPessoa(PessoaDto dto) {
        if (Objects.nonNull(dto)){
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(dto.getNome());
            pessoa.setCpf(dto.getCpf());
            pessoa.setRg(dto.getRg());
            return pessoa;
        }
        return null;
    }
}
