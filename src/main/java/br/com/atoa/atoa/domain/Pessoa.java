package br.com.atoa.atoa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_pessoa")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    @Setter(onMethod = @__({@JsonProperty}))
    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Endereco endereco;
}
