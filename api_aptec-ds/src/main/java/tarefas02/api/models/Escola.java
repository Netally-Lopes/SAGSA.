package tarefas02.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "escolas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    @NotBlank(message = "O nome da escola é obrigatório")
    @Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres")
    private String nome;

    @Column(name = "codigo_unidade", unique = true, length = 20)
    private String codigoUnidade;

    @Column(name = "cidade", nullable = false, length = 100)
    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado = "RS";

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}