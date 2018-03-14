package com.icbc.oa.controller.privController;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icbc.oa.model.BasicCoinRules;
import com.icbc.oa.service.MybatisService;
import com.icbc.restFull.RestResultGenerator;

@RestController
public class MybatisController {

	private final static Logger logger = LoggerFactory.getLogger(MybatisController.class);
	
//	mybatis 整合
	@Autowired
	MybatisService mybatisService;


    @RequestMapping(value={"/listBasicCoinRules"})  
    public List<BasicCoinRules> listBasicCoinRules() {
    	logger.info("listBasicCoinRules : 开始啦！！！！！！！");
    	return mybatisService.listBasicCoinRules();
	}

//    参数对接
//    参数校验对接
    @RequestMapping(value={"/listBasicCoinRule"})  
    public Object listBasicCoinRule(@Valid BasicCoinRules basicCoinRules, BindingResult bindingResult) {
    	logger.info("listBasicCoinRule : 开始啦！！！！！！！");
    	if (bindingResult.hasErrors()){
    		return RestResultGenerator.genResult(false, bindingResult.getFieldError().getDefaultMessage(), null);
        }
    	return mybatisService.listBasicCoinRule(basicCoinRules);
	}

    @RequestMapping(value={"/listBasicCoinRule2"})  
    public Object listBasicCoinRule2(
    		@Min(value=1,message="coinrulesid不为负数或0") 
    		@RequestParam Integer coinrulesid, BindingResult bindingResult) {
    	logger.info("listBasicCoinRule : 开始啦！！！！！！！");
    	if (bindingResult.hasErrors()){
    		return RestResultGenerator.genResult(false, bindingResult.getFieldError().getDefaultMessage(), null);
        }
    	return mybatisService.listBasicCoinRule(coinrulesid);
	}

    @GetMapping("/listBasicCoinRuleByCoinrulesid/{coinrulesid}")
    public Object listBasicCoinRuleByCoinrulesid(
    		@Min(value=1,message="coinrulesid不为负数或0") 
    		@PathVariable Integer coinrulesid) {
    	logger.info("listBasicCoinRuleByCoinrulesid : 开始啦！！！！！！！");
    	return mybatisService.listBasicCoinRule(coinrulesid);
	}

    @GetMapping("/listBasicCoinRuleByCoinrulesid2/{coinrulesid}")
    public Object listBasicCoinRuleByCoinrulesid2(@Valid BasicCoinRules basicCoinRules, BindingResult bindingResult) {
    	logger.info("listBasicCoinRuleByCoinrulesid : 开始啦！！！！！！！");
    	if (bindingResult.hasErrors()){
    		return RestResultGenerator.genResult(false, bindingResult.getFieldError().getDefaultMessage(), null);
        }
    	return mybatisService.listBasicCoinRule(basicCoinRules.getCoinrulesid());
	}

}