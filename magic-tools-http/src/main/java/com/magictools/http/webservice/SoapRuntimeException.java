package com.magictools.http.webservice;

import com.magictools.core.util.StrUtil;

/**
 * SOAP异常
 *
 * @author xiaoleilu
 */
public class SoapRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 8247610319171014183L;

	public SoapRuntimeException(Throwable e) {
		super(e.getMessage(), e);
	}

	public SoapRuntimeException(String message) {
		super(message);
	}

	public SoapRuntimeException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public SoapRuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SoapRuntimeException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
