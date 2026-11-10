package tarefas02.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @Column(name = "senha", nullable = false)
    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    @NotNull(message = "O perfil é obrigatório")
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}