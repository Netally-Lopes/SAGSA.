package tarefas02.api.services;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import tarefas02.api.exceptions.AuthorizationException;
import tarefas02.api.models.Perfil;
import tarefas02.api.models.User;
import tarefas02.api.models.dtos.UserCreateDTO;
import tarefas02.api.models.dtos.UserUpdateDTO;
import tarefas02.api.repositories.PerfilRepository;
import tarefas02.api.repositories.UserRepository;
import tarefas02.api.security.UserSpringSecurity;
import tarefas02.api.services.exceptions.DataBindingViolationException;
import tarefas02.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public User findById(Long id) {
        UserSpringSecurity userSpringSecurity = authenticated();

        // Permite o acesso se o usuário for ADMIN/ADMINISTRADOR ou se estiver consultando o próprio ID
        if (Objects.isNull(userSpringSecurity) || 
           (!userSpringSecurity.hasRole("ADMIN") && !userSpringSecurity.hasRole("ADMINISTRADOR") && !id.equals(userSpringSecurity.getId()))) {
            throw new AuthorizationException("Acesso negado! Você não possui permissão para visualizar este perfil.");
        }

        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    public User findByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);

        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Email: " + email + ", Tipo: " + User.class.getName()));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj.setSenhaHash(this.bCryptPasswordEncoder.encode(obj.getSenhaHash()));
        obj.setAtivo(true);
        obj.setDataCriacao(LocalDateTime.now());
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setSenhaHash(this.bCryptPasswordEncoder.encode(obj.getSenhaHash()));
        newObj.setDataAtualizacao(LocalDateTime.now());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
            this.userRepository.flush();
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

    public User fromDTO(@Valid UserCreateDTO obj) {
        User user = new User();
        user.setNome(obj.getNome());
        user.setEmail(obj.getEmail());
        user.setSenhaHash(obj.getSenhaHash());

        if (obj.getPerfilId() != null) {
            Perfil perfil = perfilRepository.findById(obj.getPerfilId())
                .orElseThrow(() -> new ObjectNotFoundException("Perfil não encontrado! Id: " + obj.getPerfilId()));
            user.setPerfil(perfil);
        }

        return user;
    }

    public User fromDTO(@Valid UserUpdateDTO obj) {
        User user = new User();
        user.setId(obj.getId());
        user.setSenhaHash(obj.getSenhaHash());
        return user;
    }
}