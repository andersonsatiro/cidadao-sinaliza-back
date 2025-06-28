package br.com.cidadao_sinaliza.people.entities;

import br.com.cidadao_sinaliza.politics.entities.Partido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "filiacao_partidaria")
public class FiliacaoPartidaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Past
    private LocalDate dataFiliacao;

    @Past
    private LocalDate dataTermino;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "prefeito_id", nullable = false, referencedColumnName = "id")
    private Prefeito prefeito;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "partido_id", nullable = false, referencedColumnName = "id")
    private Partido partido;
}
