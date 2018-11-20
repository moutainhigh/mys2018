package com.jing.core.model.entity;

import com.jing.utils.BaseEntity;

/**
 * 会员充值活动 实体类
 * @author codeing gen
 */
public class MemberPlus extends BaseEntity{
	private static final long serialVersionUID = 1L;

	private Integer plusId;   // 活动标识
	private String plusName;   // 活动名称
	private java.math.BigDecimal plusMoney;   // 充值金额
	private java.math.BigDecimal giftMoney;   // 赠送金额
	private java.math.BigDecimal money;   // 提成金额
	private Integer status;   // 活动状态 0正常













