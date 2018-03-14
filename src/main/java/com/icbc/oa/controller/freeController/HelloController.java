package com.icbc.oa.controller.freeController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icbc.exception.MineException;

@RestController
public class HelloController {

	private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("/")
	public String hello(ModelMap map) {
		logger.info("访问到了 hello ...");
		return "访问到了 hello ...";
	}

	@RequestMapping("/anyone")
	public String anyone(ModelMap map) throws Exception {
		logger.info("访问到了 index ...");
		throw new Exception();
	}

	@RequestMapping("/mine")
	public String mine(ModelMap map) throws MineException {
		logger.info("访问到了 mine ...");
		Throwable throwable = new Throwable();
		throw new MineException("我们访问到了 mine ...", throwable);
	}

	@RequestMapping("/sum")
	public String sum(ModelMap map) throws ArithmeticException {
		int i = 1 / 0;
		return "" + i;
	}
}