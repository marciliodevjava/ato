package br.com.atoa.atoa.mapper;

import br.com.atoa.atoa.domain.Endereco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnderecoMapper {
    public Endereco mapearCep(Object body) {
        Endereco endereco = new Endereco();
        if (Objects.nonNull(body)&& body.toString().length() > 11){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                endereco = objectMapper.readValue(objectMapper.writeValueAsString(body), Endereco.class);
                return endereco;
            } catch (JsonProcessingException ex){
                System.out.println("Não foi possivel converter");
            }
        }
        return null;
    }
}
