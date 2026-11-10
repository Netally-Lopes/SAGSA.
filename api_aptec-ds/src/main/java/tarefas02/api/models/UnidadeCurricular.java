package tarefas02.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidades_curriculares")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modulo_id", nullable = false)
    @NotNull(message = "O módulo é obrigatório")
    private Modulo modulo;

    @Column(name = "nome", nullable = false, length = 150)
    @NotBlank(message = "O nome da unidade curricular é obrigatório")
    @Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres")
    private String nome;

    @Column(name = "sigla", length = 20)
    private String sigla;

    @Column(name = "carga_horaria", nullable = false)
    @NotNull(message = "A carga horária é obrigatória")
    private Integer cargaHoraria;
}