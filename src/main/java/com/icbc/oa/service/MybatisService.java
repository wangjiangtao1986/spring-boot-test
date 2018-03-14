package com.icbc.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icbc.oa.mapper.BasicCoinRulesMapper;
import com.icbc.oa.model.BasicCoinRules;
import com.icbc.oa.model.BasicCoinRulesExample;

@Service(value = "mybatisService")
public class MybatisService {

	@Autowired
	private BasicCoinRulesMapper basicCoinRulesMapper;

	
	public List<BasicCoinRules> listBasicCoinRules() {
		return basicCoinRulesMapper.selectByExample(new BasicCoinRulesExample());
	}

	public BasicCoinRules listBasicCoinRule(BasicCoinRules basicCoinRules) {
		BasicCoinRules list = basicCoinRulesMapper.selectByPrimaryKey(basicCoinRules.getCoinrulesid());
		return list;
	}


	public BasicCoinRules listBasicCoinRule(Integer coinrulesid) {
		BasicCoinRules list = basicCoinRulesMapper.selectByPrimaryKey(coinrulesid);
		return list;
	}

}