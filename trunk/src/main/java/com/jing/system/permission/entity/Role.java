package com.jing.system.permission.entity;

import com.jing.config.web.security.SecurityRoleDetail;
import com.jing.utils.BaseEntity;

/**
 * 角色 实体类
 * @author codeing gen
 */
public class Role extends BaseEntity implements SecurityRoleDetail{
	private static final long serialVersionUID = 1L;

	private String roleId;   // 角色ID:角色ID
	private String roleName;   // 角色名称:角色名称
	private String roleTitle;   // 角色别名:角色别名
	private Integer deptCode;   // 部门编号:部门编号
	private String parentRole;   // 上级角色:上级角色
	private Integer status;   // 状态:状态















}