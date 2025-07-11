package Game.friends.GameFriends.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter
{

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String tokenFromHeader = getTokenFromHeader(request);

        if(tokenFromHeader != null)
        {
            UsernamePasswordAuthenticationToken user = tokenService.isValid(tokenFromHeader);
            SecurityContextHolder.getContext().setAuthentication(user);
        }

        filterChain.doFilter(request, response);

    }

    private String getTokenFromHeader(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        if (token == null)
        {
            return null;
        }
        return token.replace("Bearer ", "");
    }
}