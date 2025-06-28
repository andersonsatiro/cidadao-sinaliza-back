package br.com.cidadao_sinaliza.post.entities;

import br.com.cidadao_sinaliza.profile.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "resposta_comentario")
@Where(clause = "deleted_at IS NULL")
public class RespostaComentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 2000)
    private String descricao;

    @Min(0)
    private int qtdApoios;

    @Min(0)
    private int qtdContestacoes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "comentario_post_id", nullable = false, referencedColumnName = "id")
    private ComentarioPost comentarioPost;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
