package br.com.cidadao_sinaliza.location.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bairro")
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Column(name = "area_km_2")
    private int areaKm2;

    @Min(0)
    @Column(nullable = false)
    private int populacao;

    @Column(nullable = false)
    private boolean ativo = true;

    @NotNull
    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id", nullable = false, referencedColumnName = "id")
    private Cidade cidade;
}
