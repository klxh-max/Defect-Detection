package com.util;

import com.entity.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用于动态拼接SQL，用于历史数据查询
 */
public class StatisticsDynamic {
    //方法名（一个方法时）默认为provideSql(注解找时可省略)，自己取的要做特殊告知
    public String provideSql(
            @Param("startTime") Date startTime, @Param("endTime")Date endTime,
            @Param("mainboardId") Integer mainboardId, @Param("mainboardModel") String mainboardModel,
            @Param("defectId") Integer defectId, @Param("directorId") Integer directorId,
            @Param("detectionType") String detectionType, @Param("eligibleStatus") String eligibleStatus
    ){
        StringBuilder sb=new StringBuilder();
        sb.append("select * from defect where 1 = 1");
        if(startTime!=null){
            sb.append(" and gmt_create >= #{startTime}");
        }
        if(endTime!=null){
            sb.append(" and gmt_create <= #{endTime}");
        }
        if(mainboardId!=null){
            sb.append(" and mainboard_id = #{mainboardId}");
        }
        if(mainboardModel!=null&&!"".equals(mainboardModel)){
            sb.append(" and mainboard_model = #{mainboardModel}");
        }
        if(defectId!=null){
            sb.append(" and defect_id = #{defectId}");
        }
        if(directorId!=null){
            sb.append(" and director_id = #{directorId}");
        }
        if(detectionType!=null&&!"".equals(detectionType)){
            sb.append(" and defect_type = #{detectionType}");
        }
        if(eligibleStatus!=null&&!"".equals(eligibleStatus)){
            sb.append(" and eligible_status = #{eligibleStatus}");
        }
        System.out.println(sb.toString());
        return new String(sb);
    }

//    public String provideSql1(@Param("startTime") Date startTime, @Param("endTime")Date endTime){
//        StringBuilder sb=new StringBuilder();
//        sb.append("select * from defect where 1 = 1");
//        if(startTime!=null){
//            sb.append(" and gmt_create >= #{startTime}");
//        }
//        if(endTime!=null){
//            sb.append(" and gmt_create <= #{endTime}");
//        }
//        if(mainboardId!=null){
//            sb.append(" and mainboard_id = #{mainboardId}");
//        }
//        if(mainboardModel!=null&&!"".equals(mainboardModel)){
//            sb.append(" and mainboard_model = #{mainboardModel}");
//        }
//        if(defectId!=null){
//            sb.append(" and defect_id = #{defectId}");
//        }
//        if(directorId!=null){
//            sb.append(" and director_id = #{directorId}");
//        }
//        if(detectionType!=null&&!"".equals(detectionType)){
//            sb.append(" and defect_type = #{detectionType}");
//        }
//        if(eligibleStatus!=null&&!"".equals(eligibleStatus)){
//            sb.append(" and eligible_status = #{eligibleStatus}");
//        }
//        System.out.println(sb.toString());
//        return new String(sb);
//    }

}
