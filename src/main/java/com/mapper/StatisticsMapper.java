package com.mapper;

import com.entity.Defect;
import com.util.StatisticsDynamic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Date;
import java.util.List;

public interface StatisticsMapper {

    //type用于动态拼接方法的类名，method为调用方法名
    @SelectProvider(type = StatisticsDynamic.class,method = "provideSql")
    List<Defect> selectDefect(
            @Param("startTime") Date startTime, @Param("endTime")Date endTime,
            @Param("mainboardId") Integer mainboardId,@Param("mainboardModel") String mainboardModel,
            @Param("defectId") Integer defectId,@Param("directorId") Integer directorId,
            @Param("detectionType") String detectionType,@Param("eligibleStatus") String eligibleStatus);

    @Select("select count(1) from detection where gmt_create >= #{startTime} and gmt_create <= #{endTime}")
    Integer selectMainboardTotal(@Param("startTime") Date startTime, @Param("endTime")Date endTime);


    @Select("select count(1) from (select * from detection where gmt_create >= #{startTime} and gmt_create <= #{endTime} and eligible_status = #{status} group by mainboard_id) as result")
    Integer selectDefectOrNoDefectTotal(@Param("startTime") Date startTime, @Param("endTime")Date endTime,@Param("status") String status);

    @Select("select count(1) from (select distinct mainboard_id from defect where gmt_create >= #{startTime} and gmt_create <= #{endTime} and defect_type = #{defectType} group by mainboard_id) as result")
    Integer selectOneDefectTotal(@Param("startTime") Date startTime, @Param("endTime")Date endTime,@Param("defectType") String defectType);

    @Select("select distinct date_format(gmt_create,'%Y-%m-%d') from defect where gmt_create >= #{startTime} and gmt_create <= #{endTime}")
    List<Date> selectTime(@Param("startTime") Date startTime, @Param("endTime")Date endTime);





}
