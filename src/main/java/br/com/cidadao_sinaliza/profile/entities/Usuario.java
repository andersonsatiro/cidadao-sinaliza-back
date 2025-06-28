package br.com.cidadao_sinaliza.profile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@Where(clause = "deleted_at IS NULL")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 40, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 320)
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String senhaHash;

    @Column(name = "foto_perfil_imagem_url", unique = true)
    private String urlFotoPerfil;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime lastLoginAt;

    @Column(nullable = false)
    private boolean ativo = true;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false, referencedColumnName = "id")
    private Pessoa pessoa;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
