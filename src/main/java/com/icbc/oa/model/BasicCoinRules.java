package com.icbc.oa.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * basic_coin_rules
 *
 * @mbggenerated 2018-02-12 15:26:15
 */
public class BasicCoinRules {
    /**
     * 数据字典ID
     *
     * basic_coin_rules.coinRulesId
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    @NotNull(message = "ID不能为空")
    @Min(value = 1, message = "ID最小只能1")    
    @Max(value = 99, message = "ID最大只能99")
    private Integer coinrulesid;

    /**
     * 值
     *
     * basic_coin_rules.value
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private String value;

    /**
     * 名称
     *
     * basic_coin_rules.name
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private String name;

    /**
     * 类型
     *
     * basic_coin_rules.type
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private String type;

    /**
     * 类型名称
     *
     * basic_coin_rules.typeName
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private String typename;

    /**
     * 操作人ID
     *
     * basic_coin_rules.userId
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private Integer userid;

    /**
     * 操作人名称
     *
     * basic_coin_rules.userName
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private String username;

    /**
     * 创建时间
     *
     * basic_coin_rules.createTime
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private Date createtime;

    /**
     * 修改时间
     *
     * basic_coin_rules.updateTime
     *
     * @mbggenerated 2018-02-12 15:26:15
     */
    private Date updatetime;

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public Integer getCoinrulesid() {
        return coinrulesid;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setCoinrulesid(Integer coinrulesid) {
        this.coinrulesid = coinrulesid;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public String getValue() {
        return value;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public String getName() {
        return name;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public String getType() {
        return type;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public String getUsername() {
        return username;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @mbggenerated 2018-02-12 15:26:15
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}