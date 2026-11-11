package tarefas02.api.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tarefas02.api.models.Perfil;
import tarefas02.api.models.Usuario;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Construtor a partir da Entidade Usuario diretamente
    public UserSpringSecurity(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.password = usuario.getSenha();
        
        if (usuario.getPerfil() != null && usuario.getPerfil().getNome() != null) {
            String nomePerfil = usuario.getPerfil().getNome().toUpperCase();
            if (!nomePerfil.startsWith("ROLE_")) {
                nomePerfil = "ROLE_" + nomePerfil;
            }
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(nomePerfil));
        } else {
            this.authorities = Collections.emptyList();
        }
    }

    // Construtor manual (mantendo compatibilidade com o seu código anterior)
    public UserSpringSecurity(Long id, String email, String password, Perfil perfil) {
        this.id = id;
        this.email = email;
        this.password = password;
        if (perfil != null && perfil.getNome() != null) {
            String nomePerfil = perfil.getNome().toUpperCase();
            if (!nomePerfil.startsWith("ROLE_")) {
                nomePerfil = "ROLE_" + nomePerfil;
            }
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(nomePerfil));
        } else {
            this.authorities = Collections.emptyList();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(String roleName) {
        if (getAuthorities() == null) {
            return false;
        }
        String targetRole = roleName.toUpperCase().startsWith("ROLE_") 
                ? roleName.toUpperCase() 
                : "ROLE_" + roleName.toUpperCase();
                
        return getAuthorities().contains(new SimpleGrantedAuthority(targetRole));
    }
}