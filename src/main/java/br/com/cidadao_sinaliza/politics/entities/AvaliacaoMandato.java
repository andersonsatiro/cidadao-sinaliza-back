package br.com.cidadao_sinaliza.politics.entities;

import br.com.cidadao_sinaliza.people.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "avaliacao_mandato")
public class AvaliacaoMandato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Max(100)
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal avaliacao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime data;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
    private Usuario usuario;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "mandato_id", nullable = false, referencedColumnName = "id")
    private Mandato mandato;

    @PrePersist
    protected void onCreated() {
        this.data = LocalDateTime.now();
    }
}
