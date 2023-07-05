package br.com.petz.clientepet.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.petz.clientepet.handler.ErrorApiResponse.ErrorApiResponseBuilder;
import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class RestRespondeEntitExceptionHandler {
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorApiResponse> handlerGenericException(APIException ex) {
		return ex.buildErrorResponseEntity();
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApiResponse> handlerGenericException(Exception ex) {
		log.error("Exception: ", ex);
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorApiResponse.builder()
						.description("INTERNAL_SERVER_ERROR!")
						.message("POR FAVOR INFORME AO ADMINISTRADOR DO SISTEMA!")
						.build());
	}

}
