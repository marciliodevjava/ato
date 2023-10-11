package br.com.atoa.atoa.resource;

import br.com.atoa.atoa.dto.request.PessoaDto;
import br.com.atoa.atoa.dto.response.DadosDtoResponse;
import br.com.atoa.atoa.dto.response.ListDadosDtoResponse;
import br.com.atoa.atoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<DadosDtoResponse> salvarPessoa(@RequestBody PessoaDto dto, UriComponentsBuilder builder) {
        DadosDtoResponse dados = pessoaService.salvarPessoa(dto);
        URI uri = builder.path("/pessoa/{id}").buildAndExpand(dados.getPessoa().getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDtoResponse> buscarPessoa(@PathVariable Long id) {
        DadosDtoResponse dto = pessoaService.buscarPessoa(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/buscar/{nome}")
    public ResponseEntity<ListDadosDtoResponse> buscarAllPessoa(@PathVariable String nome) {
        ListDadosDtoResponse dto = pessoaService.buscarListaPessoa(nome);
        return ResponseEntity.ok(dto);
    }
}
