package tarefas02.api.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tarefas02.api.models.Perfil;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String email, String password, Perfil perfil) {
        this.id = id;
        this.email = email;
        this.password = password;
        if (perfil != null && perfil.getNome() != null) {
            // Define a permissão do Spring Security baseada no nome do perfil (ex: ROLE_ADMINISTRADOR)
            this.authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + perfil.getNome().toUpperCase())
            );
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
        return getAuthorities().contains(new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase()));
    }
}