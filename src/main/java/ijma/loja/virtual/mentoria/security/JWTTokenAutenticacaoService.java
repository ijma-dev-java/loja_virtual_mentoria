package ijma.loja.virtual.mentoria.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService { // Criar e retornar a autenticação JWT
	
	// Token de validade de 11 dias
	private static final long EXPIRATION_TIME = 950400000;
	
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
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); // Gerar o token e compacta juntando com a senha secreta
		
		// Excemplo: Bearer !@#!@#!@#r3r535434g56fd4gfd33!.@#!@3sfsdfdsfkdsflspor889486094809.er809e7987987fds)
		String token = TOKEN_PREFIX + " " + JWT;
		
		// Dando a resposta para a tela e para o cliente
		response.addHeader(HEADER_STRING, token);
		
		// Usado para ver no postman para teste
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}

}




