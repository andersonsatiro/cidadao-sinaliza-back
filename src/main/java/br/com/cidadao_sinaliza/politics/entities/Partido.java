package br.com.cidadao_sinaliza.politics.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "partido")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @NotBlank
    @Column(nullable = false, unique = true, length = 20)
    private String sigla;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataFundacao;

    @Min(0)
    @Column(nullable = false, length = 3)
    private int numero;

    @NotBlank
    @Column(nullable = false, length = 9)
    private String corPrincipal;

    @NotBlank
    @Column(name = "logo_imagem_url", nullable = false, unique = true)
    private String urlLogo;
}
