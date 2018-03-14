package com.icbc.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.icbc.exception.PrivException;
import com.icbc.restFull.RestResult;
import com.icbc.restFull.RestResultGenerator;
import com.icbc.restFull.ReturnCode;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> RestResult<T> runtimeExceptionHandler(Exception e) {
		logger.info("异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genErrorResult(ReturnCode.LOGIC_ERROR);
    }
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> RestResult<T> privExceptionHandler(PrivException e) {
		logger.info("异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genErrorResult(ReturnCode.PRIV_ERROR);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    private <T> RestResult<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
//        return RestResultGenerator.genErrorResult(ReturnCode.PARAMETER_ERROR);
//    }
//
//    @ExceptionHandler(BindException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//	public RestResult<Object> ErrorHandler(HttpServletRequest req, BindException e) throws Exception {
//        return RestResultGenerator.genResult(false, ReturnCode.PARAMETER_ERROR.getMessage(), null);
//	}
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//	public RestResult<Object> ErrorHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
//        return RestResultGenerator.genResult(false, ReturnCode.PARAMETER_ERROR.getMessage(), null);
//	}
//
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//	public RestResult<Object> ErrorHandler(HttpServletRequest req, ConstraintViolationException e) throws Exception {
//        return RestResultGenerator.genResult(false, ReturnCode.PARAMETER_ERROR.getMessage(), null);
//	}
}

