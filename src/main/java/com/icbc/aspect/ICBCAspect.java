package com.icbc.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;;

@Aspect
@Configuration
public class ICBCAspect implements Ordered {

	private final static Logger logger = LoggerFactory.getLogger(ICBCAspect.class);

	@Override
	public int getOrder() {
		return 0;
	}
}