package br.com.cidadao_sinaliza.politics.entities;

import br.com.cidadao_sinaliza.location.entities.Cidade;
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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mandato")
public class Mandato {
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
    @OneToMany(mappedBy = "mandato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrefeitoMandato> prefeitosMandato = new ArrayList<>();

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id", nullable = false, referencedColumnName = "id")
    private Cidade cidade;

    @Transient
    private AvaliacaoMandato avaliacaoMandatoAtual;
}
