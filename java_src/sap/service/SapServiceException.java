package sap.service;

public class SapServiceException extends Exception {
	public SapServiceException(String msg){
		super(msg);
	}
	
	public SapServiceException(){
		super();
	}
}
