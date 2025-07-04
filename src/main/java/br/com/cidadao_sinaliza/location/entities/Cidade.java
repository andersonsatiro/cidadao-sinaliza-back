package br.com.cidadao_sinaliza.location.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 5)
    private String sigla;

    @Min(0)
    @Column(nullable = false)
    private int populacao;

    @Column(unique = true)
    private String codigoIbge;

    @NotNull
    @Column(name = "bandeira_imagem_url", nullable = false, unique = true)
    private String urlBandeira;

    @Column(nullable = false)
    private boolean ativo = true;

    @NotNull
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "estado_id", nullable = false, referencedColumnName = "id")
    private Estado estado;

    @NotNull
    @JsonBackReference
    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bairro> bairros;
}
