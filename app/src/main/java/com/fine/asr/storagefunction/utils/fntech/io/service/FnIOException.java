package com.fine.asr.storagefunction.utils.fntech.io.service;

@SuppressWarnings("serial")
public class FnIOException extends Exception{
	private Throwable cause;
	
	public FnIOException(String message){
		super(message);
	}
	
	public FnIOException(Throwable t){
		super(t.getMessage());
		this.cause = t;
	}
	
	public Throwable getCause(){
		return this.cause;
	}
}
