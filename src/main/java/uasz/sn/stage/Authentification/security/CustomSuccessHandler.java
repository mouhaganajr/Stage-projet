package uasz.sn.stage.Authentification.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect("/ADMIN/home"); // Redirige vers admin
                return;
            } else if (role.equals("ROLE_ETUDIANT")) {
                response.sendRedirect("/etudiant/home"); // Redirige vers étudiant
                return;
            } else if (role.equals("ROLE_RESPONSABLE_UFR")) {
                response.sendRedirect("/RESPONSABLE_UFR/home"); // Redirige responsable UFR
                return;
            }
        }

        response.sendRedirect("/"); // Redirection par défaut si aucun rôle trouvé
    }
}
