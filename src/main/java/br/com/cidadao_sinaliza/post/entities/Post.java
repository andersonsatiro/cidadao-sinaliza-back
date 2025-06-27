package br.com.cidadao_sinaliza.post.entities;

import br.com.cidadao_sinaliza.usuario.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descricao;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean permitirComentarios = true;

    @Column(nullable = false)
    private boolean anonimo;

    @Min(0)
    private int qtdApoios;

    @Min(0)
    private int qtdContestacoes;

    @Min(0)
    private int qtdVisualizacoes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "nicho_post_id")
    private NichoPost nichoPost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_post_id", nullable = false)
    private StatusPost statusPost;
}
