package ijma.loja.virtual.mentoria.security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ijma.loja.virtual.mentoria.service.ImplUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener {

	@Autowired
	private ImplUserDetailsService implUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable().authorizeRequests()
				.antMatchers("/").permitAll().antMatchers("/index").permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
				.permitAll()

				// Redireciona ou retorna para o index quando efetua o logout
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")

				// Mapeando o logout do sistema
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

				// Filtra as requisições para login de JWT
				.and()
				.addFilterAfter(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	// Consultar o usuário no banco de dados com o spring security
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(implUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}

	// Ignorando algumas URLs para deixar livre de autenticação
	@Override
	public void configure(WebSecurity web) throws Exception {

		// web.ignoring()
		// .antMatchers(HttpMethod.GET, "/salvarAcesso", "/deleteAcesso")
		// .antMatchers(HttpMethod.POST, "/salvarAcesso", "/deleteAcesso");

	}

}
