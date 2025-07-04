package br.com.cidadao_sinaliza.location.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 5, unique = true)
    private String sigla;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String regiao;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private int populacao;

    @Column(nullable = false)
    private boolean ativo = true;

    @NotNull
    @Column(name = "bandeira_imagem_url", nullable = false, unique = true)
    private String urlBandeira;

    @OneToOne
    @JoinColumn(name = "capital_id", referencedColumnName = "id", nullable = false, unique = true)
    private Cidade capital;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Cidade> cidades;
}
