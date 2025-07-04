package br.com.cidadao_sinaliza.politics.entities;

import br.com.cidadao_sinaliza.people.entities.Prefeito;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "prefeito_mandato")
public class PrefeitoMandato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataInicio;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataFim;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "prefeito_id", nullable = false, referencedColumnName = "id")
    private Prefeito prefeito;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "mandato_id", nullable = false, referencedColumnName = "id")
    private Mandato mandato;
}
