package com.carParkingNew.carParking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.carParkingNew.carParking.bean.ApiResponse;





@RestControllerAdvice
public class GlobalExceptionHandler {
	
		@ExceptionHandler(ResourceNotFound.class)
		public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFound ex){
				String message=ex.getMessage();
				ApiResponse apiResponse=new ApiResponse(message,false);
				return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		}
		
		

	}