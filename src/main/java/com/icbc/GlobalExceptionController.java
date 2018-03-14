package com.icbc;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icbc.exception.MineException;
import com.icbc.restFull.RestResult;
import com.icbc.restFull.RestResultGenerator;
import com.icbc.restFull.ReturnCode;


/**
 * 创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
 * 
 * @ControllerAdvice统一定义不同Exception映射到不同错误处理页面
 */
@ControllerAdvice
public class GlobalExceptionController {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	/**
	 * 在@ControllerAdvice类中，根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理
	 */

	public static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 捕获自定义异常，返回json信息
	 */
	@ExceptionHandler(value = MineException.class)
	@ResponseBody
	public RestResult<Object> ErrorHandler(HttpServletRequest req, MineException e) throws Exception {
		logger.info("异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		e.printStackTrace();
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genResult(false, "Not Found Exception ！！！", null);
	}
	
	@ExceptionHandler(value = ArithmeticException.class)
	@ResponseBody
	public RestResult<Object> ErrorHandler(HttpServletRequest req, ArithmeticException e) throws Exception {
		logger.info("异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genResult(false, "java.lang.ArithmeticException: / by zero", null);
	}
	
	/**
	 * @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
	 */
	@ExceptionHandler(value = Exception.class)
	public RestResult<Object> ErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.info("异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genResult(false, ReturnCode.SYSTEM_ERROR.getMessage(), null);
	}

	@ExceptionHandler(value = BindException.class)
	public RestResult<Object> ErrorHandler(HttpServletRequest req, BindException e) throws Exception {
		logger.info("BindException异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genResult(false, ReturnCode.PARAMETER_ERROR.getMessage(), null);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public RestResult<Object> ErrorHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
		logger.info("MethodArgumentNotValidException异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genResult(false, ReturnCode.PARAMETER_ERROR.getMessage(), null);
	}


	@ExceptionHandler(value = ConstraintViolationException.class)
	public RestResult<Object> ErrorHandler(HttpServletRequest req, ConstraintViolationException e) throws Exception {
		logger.info("ConstraintViolationException异常输出：");
		logger.info("-----------------------------------------------------------------------------------------------------------");
		logger.info(e.getMessage());
		logger.info("-----------------------------------------------------------------------------------------------------------");
        return RestResultGenerator.genResult(false, ReturnCode.PARAMETER_ERROR.getMessage(), null);
	}
}
