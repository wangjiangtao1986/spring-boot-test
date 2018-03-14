package com.icbc.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class ICBCAspect1 implements Ordered {

	private final static Logger logger = LoggerFactory.getLogger(ICBCAspect1.class);

	@Override
	public int getOrder() {
		return 1;
	}	

	@Pointcut("execution(public * *..controller..*(..))")
	public void fullService() {}

	@Around("fullService()")
	public Object doAroundFullService(ProceedingJoinPoint pjp) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}" + url + method + uri + queryString);
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}