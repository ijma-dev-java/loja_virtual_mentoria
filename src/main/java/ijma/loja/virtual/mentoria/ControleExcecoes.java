package ijma.loja.virtual.mentoria;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ijma.loja.virtual.mentoria.model.dto.ObjetoErroDTO;

@ControllerAdvice
@RestControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {

	// Capitura exceções customizada do projeto
	@ExceptionHandler(ExceptionMentoriaJava.class)
	public ResponseEntity<Object> handleExceptionCustom(ExceptionMentoriaJava ex) {

		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();

		objetoErroDTO.setError(ex.getMessage());
		objetoErroDTO.setCode(HttpStatus.OK.toString());

		return new ResponseEntity<Object>(objetoErroDTO.getError(), HttpStatus.OK);

	}

	// Capitura exceções do projeto
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();

		String msg = "";

		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
		}
		
		if (ex instanceof HttpMessageNotReadableException) {
			msg = "Não está sendo enviado dados para o BODY - corpo da requisição";
		}

		else {
			msg = ex.getMessage();
		}

		objetoErroDTO.setError(msg);
		objetoErroDTO.setCode(status.value() + " ==> " + status.getReasonPhrase());

		ex.printStackTrace();

		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// Capitura erro na parte de banco de dados
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();

		String msg = "";

		if (ex instanceof DataIntegrityViolationException) {
			msg = "ERRO DE INTEGRIDADE NO BANCO DE DADOS: "
					+ ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		} else {
			msg = ex.getMessage();
		}

		if (ex instanceof ConstraintViolationException) {
			msg = "ERRO DE CHAVE ESTRANGEIRA: "
					+ ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		} else {
			msg = ex.getMessage();
		}

		if (ex instanceof SQLException) {
			msg = "ERRO DE SQL DO BANCO DE DADOS: " + ((SQLException) ex).getCause().getCause().getMessage();
		} else {
			msg = ex.getMessage();
		}

		objetoErroDTO.setError(msg);
		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

		ex.printStackTrace();

		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
