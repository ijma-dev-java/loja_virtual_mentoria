package ijma.loja.virtual.mentoria.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ijma.loja.virtual.mentoria.ApplicationContextLoad;
import ijma.loja.virtual.mentoria.model.Usuario;
import ijma.loja.virtual.mentoria.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService { // Criar e retornar a autenticação JWT

	// Token de validade de 11 dias
	private static final long EXPIRATION_TIME = 959990000;

	// Chave de senha para juntar com o JWT
	private static final String SECRET = "!@#$%&*(27-07-1987)";

	// Prefixo padrão do token
	private static final String TOKEN_PREFIX = "Bearer";

	// Cabeçalho da autenticação
	private static final String HEADER_STRING = "Authorization";

	// Gerar o token e dar a resposta para o cliente com o JWT
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {

		// Montagem do token

		String JWT = Jwts.builder() // Chama o gerador de token
				.setSubject(username) // Adiciona o usuário
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Tempo de expiração
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); // Gerar o token e compactar juntando com a senha
																		// secreta

		// Exemplo: Bearer
		// !@#!@#!@#r3r535434g56fd4gfd33!.@#!@3sfsdfdsfkdsflspor889486094809.er809e7987987fds)
		String token = TOKEN_PREFIX + " " + JWT;

		// Dando a resposta para a tela e para o cliente
		response.addHeader(HEADER_STRING, token);

		// Utilizando a liberação contra erro de cors no navegador
		liberacaoCors(response);

		// Usado para ver no postman para teste
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

	// Retornando o usuário validado com token, caso contrário retorna null
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim(); // Limpando valores do token

			// Fazendo a validação do token do usuário na requisão e obtendo o usuário
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenLimpo).getBody().getSubject();

			if (user != null) {

				Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
						.findUserByLogin(user);

				if (usuario != null) {

					return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(),
							usuario.getAuthorities());

				}

			}

		}

		liberacaoCors(response);

		return null;

	}

	// Fazendo liberação contra erro de cors no navegador
	private void liberacaoCors(HttpServletResponse response) {

		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}

		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}

		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}

		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}

	}

}
