package com.report.sys.base.service.exception;

/**
 * author：ccf
 */
@SuppressWarnings("serial")
public class RuntimeFunctionException extends RuntimeException {

	public RuntimeFunctionException() {
		super();
	}

	public RuntimeFunctionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuntimeFunctionException(String message) {
		super(message);
	}

	public RuntimeFunctionException(Throwable cause) {
		super(cause);
	}
	
}
