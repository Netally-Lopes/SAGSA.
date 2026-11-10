package tarefas02.api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tarefas02.api.exceptions.AuthorizationException;
import tarefas02.api.models.Task;
import tarefas02.api.models.User;
import tarefas02.api.models.projections.TaskProjection;
import tarefas02.api.repositories.TaskRepository;
import tarefas02.api.security.UserSpringSecurity;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);

        Task t = task.orElseThrow(() -> new ObjectNotFoundException(
                "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || 
           (!userSpringSecurity.hasRole("ADMIN") && !userSpringSecurity.hasRole("ADMINISTRADOR") && !userHasTask(userSpringSecurity, t))) {
            throw new AuthorizationException("Acesso negado! Você não possui permissão para acessar esta tarefa.");
        }
        return t;
    }

    @Transactional(readOnly = true)
    public List<TaskProjection> findByLoggedUser() {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationException("Acesso negado! Usuário não autenticado.");
        }

        return this.taskRepository.findByUser_Id(userSpringSecurity.getId());
    }

    @Transactional
    public Task create(Task obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationException("Acesso negado! Usuário não autenticado.");
        }

        User user = this.userService.findById(userSpringSecurity.getId());

        obj.setId(null);
        obj.setUser(user);

        return this.taskRepository.save(obj);
    }

    @Transactional(readOnly = true)
    public List<TaskProjection> findAllByLoggedUser() {
        UserSpringSecurity userSS = UserService.authenticated();

        if (Objects.isNull(userSS)) {
            throw new AuthorizationException("Acesso negado! Usuário não autenticado.");
        }

        return this.taskRepository.findByUser_Id(userSS.getId());
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new AuthorizationException("Não foi possível excluir a tarefa especificada.");
        }
    }

    private boolean userHasTask(UserSpringSecurity userSpringSecurity, Task task) {
        return task.getUser().getId().equals(userSpringSecurity.getId());
    }
}