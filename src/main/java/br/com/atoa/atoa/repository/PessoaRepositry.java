package br.com.atoa.atoa.repository;

import br.com.atoa.atoa.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepositry extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findAllByNome(String nome);
}
