package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String f1(Exception ex) {
		return "/error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String f2(HttpServletRequest request) {
		return "/error/error404";
	}
	
}
