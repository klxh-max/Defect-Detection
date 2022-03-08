package com.controller;

import com.Vo.DetectionVo;
import com.Vo.SingleVo;
import com.entity.Defect;
import com.entity.Statistics;
import com.entity.User;
import com.service.StatisticsService;
import com.service.UserService;
import com.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@Api(tags = "统计分析相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StatisticsController {

    @Autowired
    private StatisticsService service;
    private JwtUtils jwtUitls = new JwtUtils();
    private int id;

    @GetMapping("/history")
    @ApiOperation(value = "历史数据获取接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "mainboardId", value = "主板编号"),
                    @ApiImplicitParam(name = "startTime", value = "开始时间"),
                    @ApiImplicitParam(name = "endTime", value = "结束时间"),
                    @ApiImplicitParam(name = "mainboardModel", value = "主板型号"),
                    @ApiImplicitParam(name = "defectId", value = "检测编号（缺陷数据编号）"),
                    @ApiImplicitParam(name = "directorId", value = "检测人工号"),
                    @ApiImplicitParam(name = "detectionType", value = "检测方式"),
                    @ApiImplicitParam(name = "eligibleStatus", value = "检测结果（合格情况）")})
    public Map<String, Object> history(
            @Param("startTime") String startTime, @Param("endTime") String endTime,
            @Param("mainboardId") Integer mainboardId, @Param("mainboardModel") String mainboardModel,
            @Param("defectId") Integer defectId, @Param("directorId") Integer directorId,
            @Param("detectionType") String detectionType, @Param("eligibleStatus") String eligibleStatus) {
        Map<String, Object> map = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime1 = null, endTime1 = null;
            if (startTime!=null&&!"".equals(startTime)) {
                startTime1 = format.parse(startTime);
            }
            if (endTime!=null&&!"".equals(endTime)) {
                endTime1 = format.parse(endTime);
            }
            List<DetectionVo> detectionVos = service.history(startTime1, endTime1, mainboardId, mainboardModel, defectId, directorId, detectionType, eligibleStatus);
            if (detectionVos == null) {
                map.put("code", 406);
                map.put("msg", "未找到有效记录");
                map.put("data", null);
            } else {
                map.put("code", 0);
                map.put("msg", "找到有效记录");
                map.put("data", detectionVos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    @GetMapping("/single")
    @ApiOperation(value = "单日数据获取接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "endTime", value = "结束时间"),
                    @ApiImplicitParam(name = "startTime", value = "开始时间")})
    public Map<String, Object> single(@Param("startTime") String startTime, @Param("endTime") String endTime){
        Map<String, Object> map = new HashMap();
        System.out.println(startTime);
        System.out.println(endTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime1 = null, endTime1 = null;
            if (startTime!=null&&!"".equals(startTime)) {
                startTime1 = format.parse(startTime);
            }
            if (endTime!=null&&!"".equals(endTime)) {
                endTime1 = format.parse(endTime);
            }
            SingleVo singleVo= service.single(startTime1,endTime1);
            if (singleVo == null) {
                map.put("code", 406);
                map.put("msg", "未找到有效记录");
                map.put("data", null);
            } else {
                map.put("code", 0);
                map.put("msg", "找到有效记录");
                map.put("data", singleVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/several")
    @ApiOperation(value = "多日数据获取接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "endTime", value = "结束时间"),
                    @ApiImplicitParam(name = "startTime", value = "开始时间")})
    public Map<String, Object> several(@Param("startTime") String startTime, @Param("endTime") String endTime){
        Map<String, Object> map = new HashMap();
        System.out.println(startTime);
        System.out.println(endTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime1 = null, endTime1 = null;
            if (startTime!=null&&!"".equals(startTime)) {
                startTime1 = format.parse(startTime);
            }
            if (endTime!=null&&!"".equals(endTime)) {
                endTime1 = format.parse(endTime);
            }
            Map<String, Object> objectMap= service.several(startTime1,endTime1);
            if (objectMap == null) {
                map.put("code", 406);
                map.put("msg", "未找到有效记录");
                map.put("data", null);
            } else {
                map.put("code", 0);
                map.put("msg", "找到有效记录");
                map.put("data", objectMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
