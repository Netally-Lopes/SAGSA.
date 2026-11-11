package tarefas02.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sapz_historico_pdf")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoPdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull(message = "A situação de aprendizagem é obrigatória")
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "A versão é obrigatória")
    private String versao = "1.0";

    @Column(name = "caminho_arquivo_pdf", nullable = false, length = 255)
    @NotBlank(message = "O caminho do arquivo PDF é obrigatório")
    private String caminhoArquivoPdf;

    @Column(name = "hash_conteudo", length = 64)
    private String hashConteudo;

    @Column(name = "data_geracao", nullable = false, updatable = false)
    private LocalDateTime dataGeracao;

    @ManyToOne
    @JoinColumn(name = "criado_por_usuario_id", nullable = false)
    @NotNull(message = "O usuário criador é obrigatório")
    private Usuario criadoPorUsuario;

    @PrePersist
    public void prePersist() {
        if (this.dataGeracao == null) {
            this.dataGeracao = LocalDateTime.now();
        }
    }
}