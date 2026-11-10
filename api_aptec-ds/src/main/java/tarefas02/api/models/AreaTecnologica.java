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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "areas_tecnologicas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AreaTecnologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eixo_tecnologico_id", nullable = false)
    @NotNull(message = "O eixo tecnológico é obrigatório")
    private EixoTecnologico eixoTecnologico;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome da área tecnológica é obrigatório")
    private String nome;
}