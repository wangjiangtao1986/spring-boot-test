package com.icbc.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.icbc.exception.PrivException;
import com.icbc.oa.service.RedisSessionService;
import com.icbc.utile.MD5Util;;

@Aspect
@Configuration
public class ICBCAspect2 implements Ordered {
	
	private final static Logger logger = LoggerFactory.getLogger(ICBCAspect2.class);

	@Autowired
	RedisSessionService redisSessionService;
	
	@Override
	public int getOrder() {
		return 2;
	}
	
	// 权限验证
	@Pointcut("execution(public * *..privController..*(..))")
	public void privService() {}

	@Around("privService()")
	public Object doAroundPrivService(ProceedingJoinPoint pjp) throws Exception {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		String soaCertString = request.getParameter("soaCertString");
		String sysNo = request.getParameter("sysNo");
		String userId = request.getParameter("userId");

		logger.info(userId + "验证权限！！！" + request.getRequestURI());

		if (StringUtils.isEmpty(soaCertString) || StringUtils.isEmpty(sysNo) || StringUtils.isEmpty(userId)) {
			throw new PrivException(
					"缺少缓存验证参数信息，soaCertString：" + soaCertString + "，sysNo：" + sysNo + "，userId：" + userId + "。");
		} else {

			/**
			 * 签名验证 是否有必要
			 */
			boolean right = MD5Util.verifySign(request);
			if (!right) {
				throw new PrivException("没有通过签名验证，用户权限验证未通过。");
			}

			String signStr = request.getParameter("signStr");
			JSONObject jo = redisSessionService.getSession(signStr);
			if (jo.isEmpty()) {
				throw new PrivException("没有足够的访问权限，用户权限验证未通过。");
			}
		}
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}