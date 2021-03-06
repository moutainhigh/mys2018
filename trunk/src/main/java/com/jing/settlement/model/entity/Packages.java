package com.jing.settlement.model.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.jing.utils.BaseEntity;

/**
 * @ClassName: Packages
 * @Description: 消费套餐实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年01月11日 15时03分
 */
public class Packages extends BaseEntity {
	private static final long serialVersionUID = 1L;
	

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String packageId;	//to_packages:package_id  套餐标识  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String packageName;	//to_packages:package_name  套餐名称  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String pinyin;	//to_packages:pinyin  套餐拼音  
	
	private String img;//图片

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private java.math.BigDecimal price;	//to_packages:price  售价  
	private java.math.BigDecimal originalPrice;//原价
	private java.math.BigDecimal preferentialPrice;//优惠幅度

	private Date validDate;	//to_packages:valid_date  有效日期  

	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	private Integer status;	//to_packages:status  套餐状态 0正常  
	
	
	private List<PackagesDetail> packagesDetails; //套餐详情

	public List<PackagesDetail> getPackagesDetails() {
		return packagesDetails;
	}

	public void setPackagesDetails(List<PackagesDetail> packagesDetails) {
		this.packagesDetails = packagesDetails;
	}

	/**
	* @DatabasetableColumnName: to_packages:package_id
	* @Description: 获取属性        套餐标识
	* @return: String
	*/
	public String getPackageId(){
		return packageId;	
	}
	
	/**
	* @DatabasetableColumnName: to_packages:package_id
	* @Description: 设置属性        套餐标识
	* @return: String
	*/
	public void setPackageId(String packageId){
		this.packageId = packageId;	
	}	
	/**
	* @DatabasetableColumnName: to_packages:package_name
	* @Description: 获取属性        套餐名称
	* @return: String
	*/
	public String getPackageName(){
		return packageName;	
	}
	
	/**
	* @DatabasetableColumnName: to_packages:package_name
	* @Description: 设置属性        套餐名称
	* @return: String
	*/
	public void setPackageName(String packageName){
		this.packageName = packageName;	
	}	
	/**
	* @DatabasetableColumnName: to_packages:pinyin
	* @Description: 获取属性        套餐拼音
	* @return: String
	*/
	public String getPinyin(){
		return pinyin;	
	}
	
	/**
	* @DatabasetableColumnName: to_packages:pinyin
	* @Description: 设置属性        套餐拼音
	* @return: String
	*/
	public void setPinyin(String pinyin){
		this.pinyin = pinyin;	
	}	
	/**
	* @DatabasetableColumnName: to_packages:price
	* @Description: 获取属性        售价
	* @return: java.math.BigDecimal
	*/
	public java.math.BigDecimal getPrice(){
		return price;	
	}
	
	/**
	* @DatabasetableColumnName: to_packages:price
	* @Description: 设置属性        售价
	* @return: java.math.BigDecimal
	*/
	public void setPrice(java.math.BigDecimal price){
		this.price = price;	
	}	
	/**
	* @DatabasetableColumnName: to_packages:valid_date
	* @Description: 获取属性        有效日期
	* @return: Date
	*/
	public Date getValidDate(){
		return validDate;	
	}
	
	/**
	* @DatabasetableColumnName: to_packages:valid_date
	* @Description: 设置属性        有效日期
	* @return: Date
	*/
	public void setValidDate(Date validDate){
		this.validDate = validDate;	
	}	
	/**
	* @DatabasetableColumnName: to_packages:status
	* @Description: 获取属性        套餐状态 0正常
	* @return: Integer
	*/
	public Integer getStatus(){
		return status;	
	}
	
	/**
	* @DatabasetableColumnName: to_packages:status
	* @Description: 设置属性        套餐状态 0正常
	* @return: Integer
	*/
	public void setStatus(Integer status){
		this.status = status;	
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public java.math.BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(java.math.BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public java.math.BigDecimal getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(java.math.BigDecimal preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}	
	
	
	
}

