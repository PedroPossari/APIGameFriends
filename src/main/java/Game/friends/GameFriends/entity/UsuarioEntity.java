package Game.friends.GameFriends.entity;

import Game.friends.GameFriends.entity.UsuarioJogo.UsuarioJogoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Usuario")
public class UsuarioEntity implements UserDetails
{

    @Id
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "SENHA")
    private String senha;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USUARIO_CARGO",
            joinColumns = @JoinColumn(name = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_CARGO")
    )
    private Set<CargoEntity> cargos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioJogoEntity> usuarioJogos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return cargos;
    }

    @Override
    public String getPassword()
    {
        return senha;
    }

    @Override
    public String getUsername()
    {
        return login;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}

