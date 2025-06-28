package br.com.cidadao_sinaliza.post.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "imagem_post",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "ordem"})
        }
)
public class ImagemPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String urlImagem;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Min(0)
    @NotNull
    @Column(name = "tamanho_KB", nullable = false)
    private int tamanhoKB;

    @NotBlank
    @Column(nullable = false)
    private String nomeArquivo;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private int ordem;

    @NotNull
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id")
    private Post post;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
