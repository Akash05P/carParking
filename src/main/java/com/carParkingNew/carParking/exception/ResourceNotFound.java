package com.carParkingNew.carParking.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException{
	
		private static final long serialVersionUID = 1L;
		String resourceName;
		String fielddName;
		long fieldValue;
		public ResourceNotFound(String resourceName, String fielddName, long fieldValue) {
			super(String.format("%s not found with %s : %s",resourceName,fielddName,fieldValue));
			this.resourceName = resourceName;
			this.fielddName = fielddName;
			this.fieldValue = fieldValue;
		}
}
