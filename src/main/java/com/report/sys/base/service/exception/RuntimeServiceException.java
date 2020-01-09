package com.report.sys.base.service.exception;

/**
 * author：ccf
 */
@SuppressWarnings("serial")
public class RuntimeServiceException extends RuntimeException {

	public RuntimeServiceException() {
		super();
	}

	public RuntimeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuntimeServiceException(String message) {
		super(message);
	}

	public RuntimeServiceException(Throwable cause) {
		super(cause);
	}

}
