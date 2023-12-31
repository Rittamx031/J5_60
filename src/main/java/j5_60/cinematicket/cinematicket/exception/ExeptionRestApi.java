package j5_60.cinematicket.cinematicket.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ExeptionRestApi
 */
@RestControllerAdvice
public class ExeptionRestApi extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    List<String> errors = new ArrayList<String>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
        ex.getLocalizedMessage(), errors);
    return new ResponseEntity<Object>(
        apiError, new HttpHeaders(), apiError.getStatus());
  }

  // @Override
  // public ResponseEntity<Object> handleConstraintViolation(
  // ConstraintViolationException ex, WebRequest request) {
  // List<String> errors = new ArrayList<String>();
  // for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
  // errors.add(violation.getRootBeanClass().getName() + " " +
  // violation.getPropertyPath() + ": " + violation.getMessage());
  // }

  // ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
  // ex.getLocalizedMessage(), errors);
  // return new ResponseEntity<Object>(
  // apiError, new HttpHeaders(), apiError.getStatus());
  // }

  // @Override
  // public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
  // MethodArgumentTypeMismatchException ex, WebRequest request) {
  // String error = ex.getName() + " should be of type " +
  // ex.getRequiredType().getName();
  // ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
  // ex.getLocalizedMessage(), error);
  // return new ResponseEntity<Object>(
  // apiError, new HttpHeaders(), apiError.getStatus());
  // }

  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex,
      HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    List<String> errors = new ArrayList<String>();
    for (ObjectError iterable_element : ex.getBindingResult().getAllErrors()) {
      errors.add(iterable_element.getObjectName() + " not validated yet" + iterable_element.getDefaultMessage());
    }
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return new ResponseEntity<Object>(
        apiError, new HttpHeaders(), apiError.getStatus());
  }
  // @Override
  // protected ResponseEntity<Object> createResponseEntity(@Nullable Object body,
  // HttpHeaders headers,
  // HttpStatusCode statusCode, WebRequest request) {

  // return super.createResponseEntity(body, headers, statusCode, request);
  // }
  // @Override
  // protected ProblemDetail createProblemDetail(Exception ex, HttpStatusCode
  // status, String defaultDetail,
  // @Nullable String detailMessageCode, @Nullable Object[]
  // detailMessageArguments, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.createProblemDetail(ex, status, defaultDetail,
  // detailMessageCode, detailMessageArguments, request);
  // }

  // @Override
  // @Nullable
  // protected MessageSource getMessageSource() {
  // // TODO Auto-generated method stub
  // return super.getMessageSource();
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
  // HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleAsyncRequestTimeoutException(ex, headers, status,
  // request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders
  // headers,
  // HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleConversionNotSupported(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers,
  // HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleErrorResponseException(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
  // @Nullable Object body, HttpHeaders headers,
  // HttpStatusCode statusCode, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleExceptionInternal(ex, body, headers, statusCode, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
  // HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
  // HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders
  // headers,
  // HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleHttpMessageNotReadable(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders
  // headers,
  // HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleHttpMessageNotWritable(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException
  // ex,
  // HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleHttpRequestMethodNotSupported(ex, headers, status,
  // request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders
  // headers,
  // HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleMissingPathVariable(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleMissingServletRequestParameter(MissingServletRequestParameterException
  // ex,
  // HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleMissingServletRequestParameter(ex, headers, status,
  // request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleMissingServletRequestPart(MissingServletRequestPartException ex,
  // HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleMissingServletRequestPart(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders
  // headers,
  // HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleNoHandlerFoundException(ex, headers, status, request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object>
  // handleServletRequestBindingException(ServletRequestBindingException ex,
  // HttpHeaders headers, HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleServletRequestBindingException(ex, headers, status,
  // request);
  // }

  // @Override
  // @Nullable
  // protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
  // HttpHeaders headers,
  // HttpStatusCode status, WebRequest request) {
  // // TODO Auto-generated method stub
  // return super.handleTypeMismatch(ex, headers, status, request);
  // }

  // @Override
  // public void setMessageSource(MessageSource messageSource) {
  // // TODO Auto-generated method stub
  // super.setMessageSource(messageSource);
  // }

  // @Override
  // protected Object clone() throws CloneNotSupportedException {
  // // TODO Auto-generated method stub
  // return super.clone();
  // }

  // @Override
  // public boolean equals(Object obj) {
  // // TODO Auto-generated method stub
  // return super.equals(obj);
  // }

  // @Override
  // protected void finalize() throws Throwable {
  // // TODO Auto-generated method stub
  // super.finalize();
  // }

  // @Override
  // public int hashCode() {
  // // TODO Auto-generated method stub
  // return super.hashCode();
  // }

  // @Override
  // public String toString() {
  // // TODO Auto-generated method stub
  // return super.toString();
  // }

}