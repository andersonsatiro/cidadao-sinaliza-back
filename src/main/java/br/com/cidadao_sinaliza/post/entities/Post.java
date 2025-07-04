package br.com.cidadao_sinaliza.post.entities;

import br.com.cidadao_sinaliza.location.entities.Bairro;
import br.com.cidadao_sinaliza.location.entities.Cidade;
import br.com.cidadao_sinaliza.people.entities.Usuario;
import br.com.cidadao_sinaliza.politics.entities.Mandato;
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
import jakarta.persistence.OneToOne;
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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
@Where(clause = "deteted_at IS NULL")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank
    @Column(nullable = false, length = 2000)
    private String descricao;

    @Column(nullable = false)
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

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_post_id", referencedColumnName = "id")
    private CategoriaPost categoriaPost;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "status_post_id", nullable = false, referencedColumnName = "id")
    private StatusPost statusPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ComentarioPost> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ImagemPost> imagens = new ArrayList<>();

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "mandato_id", nullable = false, referencedColumnName = "id")
    private Mandato mandato;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id", nullable = false, referencedColumnName = "id")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "bairro_id", referencedColumnName = "id")
    private Bairro bairro;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "posicao_post_id", nullable = false, referencedColumnName = "id")
    private PosicaoPost posicaoPost;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
