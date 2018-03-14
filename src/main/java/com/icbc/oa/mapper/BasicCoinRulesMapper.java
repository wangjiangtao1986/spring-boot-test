package com.icbc.oa.mapper;

import com.icbc.oa.model.BasicCoinRules;
import com.icbc.oa.model.BasicCoinRulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasicCoinRulesMapper {
	
    int countByExample(BasicCoinRulesExample example);

    int deleteByExample(BasicCoinRulesExample example);

    int deleteByPrimaryKey(Integer coinrulesid);

    int insert(BasicCoinRules record);

    int insertSelective(BasicCoinRules record);

    List<BasicCoinRules> selectByExample(BasicCoinRulesExample example);

    BasicCoinRules selectByPrimaryKey(Integer coinrulesid);

    int updateByExampleSelective(@Param("record") BasicCoinRules record, @Param("example") BasicCoinRulesExample example);

    int updateByExample(@Param("record") BasicCoinRules record, @Param("example") BasicCoinRulesExample example);

    int updateByPrimaryKeySelective(BasicCoinRules record);

    int updateByPrimaryKey(BasicCoinRules record);
}