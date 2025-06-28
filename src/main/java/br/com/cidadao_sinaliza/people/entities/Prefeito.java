package br.com.cidadao_sinaliza.people.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prefeito")
public class Prefeito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foto_perfil_imagem_url", unique = true)
    private String urlFotoPerfil;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id")
    private Usuario usuario;
}
