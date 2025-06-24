package Game.friends.GameFriends.security;

import Game.friends.GameFriends.entity.UsuarioEntity;
import Game.friends.GameFriends.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService
{

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioService.findbyLogin(username);

        return usuarioEntityOptional
                .orElseThrow(() -> new UsernameNotFoundException("usuario invalido"));
    }
}

