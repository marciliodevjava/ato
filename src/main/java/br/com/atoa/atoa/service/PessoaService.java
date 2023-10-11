package br.com.atoa.atoa.service;

import br.com.atoa.atoa.domain.Endereco;
import br.com.atoa.atoa.domain.Pessoa;
import br.com.atoa.atoa.dto.request.PessoaDto;
import br.com.atoa.atoa.dto.response.DadosDtoResponse;
import br.com.atoa.atoa.dto.response.EnderecoDtoResponse;
import br.com.atoa.atoa.dto.response.ListDadosDtoResponse;
import br.com.atoa.atoa.dto.response.PessoaDtoRespose;
import br.com.atoa.atoa.mapper.PessoaMapper;
import br.com.atoa.atoa.repository.EnderecoRepository;
import br.com.atoa.atoa.repository.PessoaRepositry;
import br.com.atoa.atoa.utils.PesquisaEnderecoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PessoaService {
    @Autowired
    private PessoaMapper pessoaMapper;
    @Autowired
    private PessoaRepositry pessoaRepositry;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PesquisaEnderecoUtils pesquisaEndereco;
    public DadosDtoResponse salvarPessoa(PessoaDto dto) {

        Endereco endereco = pesquisaEndereco.pesquisarCep(dto.getCep());
        Pessoa pessoa = pessoaMapper.mapearPessoaDtoParaPessoa(dto);
        if (Objects.nonNull(endereco)){
            pessoa.setEndereco(endereco);
            endereco.setPessoa(pessoa);
        }
        if (Objects.nonNull(pessoa)) pessoa = pessoaRepositry.save(pessoa);
        if (Objects.nonNull(endereco)) endereco = enderecoRepository.save(endereco);
        DadosDtoResponse dados = this.montarDadosRetorno(pessoa);
        return dados;
    }

    private DadosDtoResponse montarDadosRetorno(Pessoa pessoa) {
        DadosDtoResponse dados = new DadosDtoResponse();
        if (Objects.nonNull(pessoa)){
            PessoaDtoRespose dto = new PessoaDtoRespose();
            dto.setId(pessoa.getId());
            dto.setNome(pessoa.getNome());
            dto.setCpf(pessoa.getCpf());
            dto.setRg(pessoa.getRg());
            dados.setPessoa(dto);
            dados.getPessoa().setEndereco(this.mapeiaEndereco(pessoa.getEndereco()));
            return dados;
        }
        return dados;
    }

    private EnderecoDtoResponse mapeiaEndereco(Endereco endereco) {
        if (Objects.nonNull(endereco)){
            EnderecoDtoResponse dto = new EnderecoDtoResponse();
            dto.setCep(endereco.getCep());
            dto.setUf(endereco.getUf());
            dto.setDdd(endereco.getDdd());
            dto.setBairro(endereco.getBairro());
            dto.setLocalidade(endereco.getLocalidade());
            dto.setComplemento(endereco.getComplemento());
            dto.setLogradouro(endereco.getLogradouro());
            return dto;
        }
        return null;
    }

    public DadosDtoResponse buscarPessoa(Long id) {
        DadosDtoResponse dto = new DadosDtoResponse();
        if (id != null){
            Optional<Pessoa> pessoa = pessoaRepositry.findById(id);
            dto = this.montarDadosRetorno(this.conversePessoa(pessoa));
            return dto;
        }
        return dto;
    }

    private Pessoa conversePessoa(Optional<Pessoa> pessoa) {
        Pessoa ps = pessoa.get();
        return ps;
    }

    public ListDadosDtoResponse buscarListaPessoa(String nome) {
        ListDadosDtoResponse listaDto = new ListDadosDtoResponse();
        if (Objects.nonNull(nome)){
            List<Pessoa> listPessoa = pessoaRepositry.findAllByNome(nome);
            listPessoa.forEach( pessoa -> {
                List<PessoaDtoRespose> dto = pessoaMapper.mapearPessoaParaDto(pessoa);
                listaDto.setPessoa(dto);
            });
            return listaDto;
        }
        return null;
    }
}
