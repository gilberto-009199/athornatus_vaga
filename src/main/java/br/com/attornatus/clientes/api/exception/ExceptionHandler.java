package br.com.attornatus.clientes.api.exception;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.api.response.ResponseError;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;



@RestControllerAdvice(basePackages = {"br.com.attornatus.clientes.api.controllers"})
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler({APIException.class})
    private ResponseBody<?> handleAPI(APIException ex) {
		
		String message = messageSource.getMessage( ex.getMessage(), null, null);
		
		log.info("stage=exception method=ExceptionController.handleAPI {} - {}", ex.getCode(), message);
		
        return new ResponseBody<>(new  ResponseError( ex.getCode(), message));
    }

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler({BusinessException.class})
    private ResponseBody<?> handleBusiness(BusinessException ex) {
		
		String message = messageSource.getMessage( ex.getMessage(), null, null);
		
		log.info("stage=exception method=ExceptionController.handleBusiness {} - {}", ex.getCode(), message);
		
		return new ResponseBody<>(new  ResponseError( ex.getCode(), message));
    }
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler({DomainException.class})
    private ResponseBody<?> handleDomain(DomainException ex) {
		
		String message = messageSource.getMessage( ex.getMessage(), null, null);
		
		log.info("stage=exception method=ExceptionController.handleDomain {} - {}", ex.getCode(), message);
		
        return new ResponseBody<>(new  ResponseError( ex.getCode(), message));
    }
	
	// VALIDATION SPRING FRAMEWORK
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(	MethodArgumentNotValidException ex, 
																HttpHeaders headers,
																HttpStatusCode status,
																WebRequest request) {
		
		List<ResponseError> erros = new ArrayList<ResponseError>();
		
		for(ObjectError methodArgumentNotValidException : ex.getBindingResult().getAllErrors()) {
			erros.add( new ResponseError("VALID", methodArgumentNotValidException.getDefaultMessage()) );

			log.info("stage=exception method=ExceptionController.handleMethodArgumentNotValid VALID - {}", methodArgumentNotValidException.getDefaultMessage() );
			
		}
		
		ResponseBody<?> res = new ResponseBody<>(erros);
		
        return ResponseEntity.badRequest().body( res );	 
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(	HttpMessageNotReadableException ex,
																HttpHeaders headers, 
																HttpStatusCode status, 
																WebRequest request) {
		
		ResponseBody<?> res = new ResponseBody<>(new ResponseError( "VALID", ex.getMessage()));
		
		log.info("stage=exception method=ExceptionController.handleHttpMessageNotReadable VALID - {}", ex.getMessage() );

        return ResponseEntity.badRequest().body( res );	 
	}

}
