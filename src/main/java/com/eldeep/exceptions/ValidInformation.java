package com.eldeep.exceptions;

import org.springframework.http.HttpStatus;

public class ValidInformation extends AbstractHttpException{
	
	public ValidInformation() {
		super("Please Enter Valied information", HttpStatus.FORBIDDEN);
	}
}