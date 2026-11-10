package tarefas02.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.models.Escola;
import tarefas02.api.repositories.EscolaRepository;
import tarefas02.api.services.exceptions.DataBindingViolationException;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository escolaRepository;

    public Escola findById(Long id) {
        Optional<Escola> escola = this.escolaRepository.findById(id);
        return escola.orElseThrow(() -> new ObjectNotFoundException(
                "Escola não encontrada! Id: " + id + ", Tipo: " + Escola.class.getName()));
    }

    public List<Escola> findAll() {
        return this.escolaRepository.findAll();
    }

    public List<Escola> findAllAtivas() {
        return this.escolaRepository.findByAtivoTrue();
    }

    @Transactional
    public Escola create(Escola obj) {
        obj.setId(null);
        obj.setAtivo(true);

        if (obj.getCodigoUnidade() != null && this.escolaRepository.existsByCodigoUnidade(obj.getCodigoUnidade())) {
            throw new DataBindingViolationException("Já existe uma escola cadastrada com este Código de Unidade!");
        }

        return this.escolaRepository.save(obj);
    }

    @Transactional
    public Escola update(Escola obj) {
        Escola newObj = findById(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setCodigoUnidade(obj.getCodigoUnidade());
        newObj.setCidade(obj.getCidade());
        newObj.setEstado(obj.getEstado());
        if (obj.getAtivo() != null) {
            newObj.setAtivo(obj.getAtivo());
        }
        return this.escolaRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.escolaRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possível excluir pois há entidades (como turmas) relacionadas!");
        }
    }
}