package com.netty.common;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


public class MyValidationUtils {
	
	public static String parseErrors(BindingResult result) {
		if(result.hasErrors()==false) return null;
		
		List<ObjectError> errorList = result.getAllErrors();
	    StringBuilder errorMsg = new StringBuilder();
	    for(ObjectError error : errorList){
	        	errorMsg.append( error.getDefaultMessage()+"; ");
	    }
	    return (errorMsg.toString());
		
	}
}
