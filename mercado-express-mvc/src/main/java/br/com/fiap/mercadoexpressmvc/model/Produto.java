package br.com.fiap.mercadoexpressmvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Produto do Mercado Express (Parte II - MVC).
 * Mapeado para a tabela TDS_MVC_TB_MERCADO no mesmo banco Oracle da Parte I.
 *
 * No MVC o formulario Thymeleaf faz o "binding" direto nesta entidade, entao
 * as validacoes ficam aqui e sao disparadas pelo @Valid no controller.
 */
@Entity
@Table(name = "TDS_MVC_TB_MERCADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O tipo é obrigatório")
    @Column(name = "TIPO", length = 50)
    private String tipo;

    @NotBlank(message = "O setor é obrigatório")
    @Column(name = "SETOR", length = 50)
    private String setor;

    @NotBlank(message = "O tamanho é obrigatório")
    @Column(name = "TAMANHO", length = 50)
    private String tamanho;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    @Column(name = "PRECO", precision = 10, scale = 2)
    private BigDecimal preco;
}
