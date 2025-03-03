package uasz.sn.stage.Authentification.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] FOR_ADMIN = {"/ADMIN/**"};
    private static final String[] FOR_ETUDIANT = {"/ETUDIANT/**"};
    private static final String[] FOR_RESPONSABLE_UFR = {"/RESPONSABLE_UFR/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/js/**", "/css/**").permitAll() // Autoriser les ressources statiques
                        .requestMatchers("/login", "/logout").permitAll() // Page de connexion accessible
                        .requestMatchers("/h2/**").permitAll() // H2 Console (dev uniquement)
                        .requestMatchers(FOR_ADMIN).hasRole("ADMIN") // Accès admin
                        .requestMatchers(FOR_ETUDIANT).hasRole("ETUDIANT") // Accès étudiant
                        .requestMatchers(FOR_RESPONSABLE_UFR).hasRole("RESPONSABLE_UFR") // Accès responsable UFR
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Page de connexion personnalisée
                        .usernameParameter("username") // Paramètre du formulaire
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true) // Redirection après connexion
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2/**"))) ;// Désactiver CSRF pour H2
        return http.build();
    }
}
