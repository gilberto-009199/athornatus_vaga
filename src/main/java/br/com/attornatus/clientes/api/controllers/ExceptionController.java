package br.com.attornatus.clientes.api.controllers;

import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.attornatus.clientes.api.exception.APIException;
import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.api.response.ResponseError;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.domain.exception.DomainException;


@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({APIException.class})
    private ResponseEntity<?> handleAPI(APIException ex) {

		ResponseBody res = new ResponseBody<>(new  ResponseError( ex.getCode(), ex.getMessage()));
		
        return ResponseEntity.badRequest().body( res );
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handle(BindValidationException ex) {

		ResponseBody res = new ResponseBody<>(new  ResponseError( "VALID", ex.getMessage()));
		
        return ResponseEntity.badRequest().body( res );	
	}
	
	@ExceptionHandler({BusinessException.class})
    private ResponseEntity<?> handleBusiness(BusinessException ex) {
		
		ResponseBody res = new ResponseBody<>(new  ResponseError( ex.getCode(), ex.getMessage()));
		
        return ResponseEntity.badRequest().body( res );
    }
	
	@ExceptionHandler({DomainException.class})
    private ResponseEntity<?> handleDomain(DomainException ex) {
		
		ResponseBody res = new ResponseBody<>(new  ResponseError( ex.getCode(), ex.getMessage()));
		
        return ResponseEntity.badRequest().body( res );
    }
	
}
