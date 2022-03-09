package edu.cnm.deepdive.codebreaker.controller;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exceptions {

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource (game or guess) not found.")
  public  void notFound (){
    // DO nothing for now
  }


 @ExceptionHandler(IllegalStateException.class)
 @ResponseStatus(value = HttpStatus.CONFLICT, reason = "game already completed (solved")
  public void  alreadySolved(){
    // do nothing for now
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST    , reason = "requesr body contains invalid property values")
  public void badPayload(){

  }

}
