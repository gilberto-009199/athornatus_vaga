package br.com.attornatus.clientes.api.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.attornatus.clientes.api.exception.APIException;
import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.api.response.ResponseError;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.domain.exception.DomainException;


@RestControllerAdvice()
public class ExceptionController extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({APIException.class})
    private ResponseEntity<?> handleAPI(APIException ex) {
		
		String message = messageSource.getMessage( ex.getMessage(), null, null);
		
		ResponseBody<?> res = new ResponseBody<>(new  ResponseError( ex.getCode(), message));
		
        return ResponseEntity.badRequest().body( res );
    }

	@ExceptionHandler({BusinessException.class})
    private ResponseEntity<?> handleBusiness(BusinessException ex) {
		
		String message = messageSource.getMessage( ex.getMessage(), null, null);
		
		ResponseBody<?> res = new ResponseBody<>(new  ResponseError( ex.getCode(), message));
		
        return ResponseEntity.badRequest().body( res );
    }

	@ExceptionHandler({DomainException.class})
    private ResponseEntity<?> handleDomain(DomainException ex) {
		
		String message = messageSource.getMessage( ex.getMessage(), null, null);
		
		ResponseBody<?> res = new ResponseBody<>(new  ResponseError( ex.getCode(), message));
		
        return ResponseEntity.badRequest().body( res );
    }

	// VALIDATION SPRING FRAMEWORK
	@Override 
	public ResponseEntity<Object> handleMethodArgumentNotValid(	MethodArgumentNotValidException ex, 
																HttpHeaders headers,
																HttpStatusCode status,
																WebRequest request) {
		
		List<ResponseError> erros = new ArrayList<ResponseError>();
		
		for(ObjectError methodArgumentNotValidException : ex.getBindingResult().getAllErrors()) {
			erros.add( new ResponseError("VALID", methodArgumentNotValidException.getDefaultMessage()) );
		}
		
		ResponseBody<?> res = new ResponseBody<>(erros);
        return ResponseEntity.badRequest().body( res );	 
	}
	
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(	HttpMessageNotReadableException ex,
																HttpHeaders headers, 
																HttpStatusCode status, 
																WebRequest request) {
		
		ResponseBody<?> res = new ResponseBody<>(new ResponseError( "VALID", ex.getMessage()));
		
        return ResponseEntity.badRequest().body( res );	 
	}

}
