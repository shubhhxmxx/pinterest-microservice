package com.infy.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.infy.exception.UserException;

public class LoggingAspect {

	private static Log logger = LogFactory.getLog(LoggingAspect.class);

	
	public void logExceptionFromService(UserException exception) {
		logger.error(exception.getMessage(), exception);
	}

}
