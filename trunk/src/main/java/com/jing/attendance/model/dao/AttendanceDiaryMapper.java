package com.jing.attendance.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jing.utils.paginator.domain.PageBounds;
import com.jing.attendance.model.entity.AttendanceDiary;
import com.jing.attendance.service.bo.AttendanceDiaryBo;

/**
 * @ClassName: AttendanceDiaryMapper
 * @Description: 打卡记录映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2018年02月05日 22时39分
 */
@Mapper
public interface AttendanceDiaryMapper {

	/**
	 * @Title: addAttendanceDiary
	 * @Description:添加打卡记录
	 * @param attendanceDiary 实体
	 * @return Integer
	 */
	Integer addAttendanceDiary(AttendanceDiary attendanceDiary);
	
	/**
	 * @Title modifyAttendanceDiary
	 * @Description:修改打卡记录
	 * @param attendanceDiary 实体
	 * @return Integer
	 */
	Integer modifyAttendanceDiary(AttendanceDiary attendanceDiary);
	
	/**
	 * @Title: dropAttendanceDiaryByAttId
	 * @Description:删除打卡记录
	 * @param attId 实体标识
	 * @return Integer
	 */
	Integer dropAttendanceDiaryByAttId(Integer attId);
	
	/**
	 * @Title: queryAttendanceDiaryByAttId
	 * @Description:根据实体标识查询打卡记录
	 * @param attId 实体标识
	 * @return AttendanceDiary
	 */
	AttendanceDiary queryAttendanceDiaryByAttId(Integer attId);
	 
	/**
	 * @Title: queryAttendanceDiaryForPage
	 * @Description: 根据打卡记录属性与分页信息分页查询打卡记录信息
	 * @param pageBounds 分页信息
	 * @param attendanceDiary 实体
	 * @return List<AttendanceDiary>
	 */
	List<AttendanceDiary> queryAttendanceDiaryForPage(PageBounds pageBounds, @Param("attendanceDiary") AttendanceDiary attendanceDiary);
	
	 /**
	  * @Title: queryAttendanceDiaryByProperty
	  * @Description:根据属性查询打卡记录
	  * @return List<AttendanceDiary>
	  */
	List<AttendanceDiary> queryAttendanceDiaryByProperty(@Param("attendanceDiary") Map<String, Object> map);
	
	/** 
	* @Title: initEmployeeBindingData 
	* @Description: 生成当日已绑定规则的员工考勤信息
	* @return  Integer    返回类型 
	* @throws 
	*/
	Integer initEmployeeBindingData();
	
	/** 
	* @Title: initEmployeeNotBindingData 
	* @Description: 生成当日已未绑定规则的员工考勤信息
	* @return  Integer    返回类型 
	* @throws 
	*/
	Integer initEmployeeNotBindingData();
	
	/** 
	* @Title: queryEmployeeCountsToday 
	* @Description: 查询当日考勤员工数
	* @return  Integer    返回类型 
	* @throws 
	*/
	Integer queryEmployeeCountsToday();
	 
	/** 
	* @Title: queryEmployeeYesterdayToday 
	* @Description: 取昨天、今天员工考勤详情及签到、应到与当前时间的分差 
	* @param employeeId
	* @return  List<AttendanceDiaryBo>    返回类型 
	* @throws 
	*/
	List<AttendanceDiaryBo> queryEmployeeYesterdayToday(String employeeId);

	/** 
	* @Title: queryAttendanceDiaryAllForPage 
	* @Description: 查询用户考勤信息
	* @param pageBounds
	* @param query
	* @return  List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryAttendanceDiaryAllForPage(PageBounds pageBounds, @Param("params") Map<String, Object> query);
	
	/** 
	* @Title: queryAttendanceDiaryForPageMap 
	* @Description: 查询用户考勤信息
	* @param pageBounds
	* @param query
	* @return  List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryAttendanceDiaryForPageMap(PageBounds pageBounds, @Param("params") Map<String, Object> query);

	/** 
	* @Title: clearFutureDiaryRecord 
	* @Description: 清空员工末来考勤
	* @param empId  员工标识
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer clearFutureDiaryRecord(String empId);	 
	
	/** 
	* @Title: clearFutureDiaryRecordByAttendanceId 
	* @Description: 清空员工末来考勤
	* @param attendanceId 考勤标识
	* @return  Integer    返回类型 
	* @throws 
	*/
	Integer clearFutureDiaryRecordByAttendanceId(Integer attendanceId);	
	
	/** 
	* @Title: insertFutureDiaryRecordByEmployeeId 
	* @Description: 重建员工末来考勤数据
	* @param empId  员工标识
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer insertFutureDiaryRecordByEmployeeId(@Param("empId") String empId);

	/** 
	* @Title: insertFutureDiaryRecordByAttendanceId 
	* @Description:  重建规则下所有员工末来考勤数据
	* @param attendanceId  考勤标识
	* @return  Integer    返回类型 
	* @throws 
	*/
	Integer insertFutureDiaryRecordByAttendanceId(@Param("attendanceId") Integer attendanceId);	
	 
}
