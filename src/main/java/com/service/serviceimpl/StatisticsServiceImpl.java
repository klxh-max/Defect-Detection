package com.service.serviceimpl;

import com.Vo.DetectionVo;
import com.Vo.EverydayVo;
import com.Vo.SingleVo;
import com.entity.Defect;
import com.entity.Statistics;
import com.mapper.StatisticsMapper;
import com.service.StatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;


    @Override
    public List<DetectionVo> history(Date startTime, Date endTime, Integer mainboardId, String mainboardModel, Integer defectId, Integer directorId, String detectionType, String eligibleStatus) {
        if (endTime!=null){
            endTime = getNewTime(endTime, 1);
        }
        System.out.println(endTime);
        List<Defect> defects = statisticsMapper.selectDefect(startTime, endTime, mainboardId, mainboardModel, defectId, directorId, detectionType, eligibleStatus);
        List<DetectionVo> detectionVos = new ArrayList<>();
        for (Defect defect : defects) {
            DetectionVo vo = new DetectionVo();
            BeanUtils.copyProperties(defect, vo);
            vo.setPicture("待定");
            detectionVos.add(vo);
        }
        return detectionVos;
    }

    @Override
    public SingleVo single(Date startTime, Date endTime) {
        SingleVo singleVo = new SingleVo();
        endTime = getNewTime(endTime, 1);
        singleVo.setMainboardTotal(statisticsMapper.selectMainboardTotal(startTime, endTime));
        singleVo.setDefectTotal(statisticsMapper.selectDefectOrNoDefectTotal(startTime, endTime, "不合格"));
        singleVo.setAppearance(statisticsMapper.selectOneDefectTotal(startTime, endTime, "主板外观"));
        singleVo.setScrew(statisticsMapper.selectOneDefectTotal(startTime, endTime, "主板固定螺丝"));
        singleVo.setCPUAppearance(statisticsMapper.selectOneDefectTotal(startTime, endTime, "CPU风扇外观"));
        singleVo.setCPUScrew(statisticsMapper.selectOneDefectTotal(startTime, endTime, "CPU固定螺丝"));
        singleVo.setWiring(statisticsMapper.selectOneDefectTotal(startTime, endTime, "CPU风扇接线"));
        return singleVo;
    }

    @Override
    public Map<String, Object> several(Date startTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        List<EverydayVo> list = new ArrayList<>();
        SingleVo singleVo = single(startTime, getNewTime(endTime, 1));
        List<Date> dates = statisticsMapper.selectTime(startTime, getNewTime(endTime, 1));
        for (Date gmt_create : dates) {
            SingleVo singleVo1 = single(gmt_create, getNewTime(gmt_create, 1));
            EverydayVo everydayVo = new EverydayVo();
            everydayVo.setTime(gmt_create);
            BeanUtils.copyProperties(singleVo1, everydayVo);
            Double pass = 1-(singleVo1.getDefectTotal().doubleValue() / singleVo1.getMainboardTotal().doubleValue());
            everydayVo.setPass(pass);
            System.out.println(everydayVo.getTime());
            list.add(everydayVo);
        }
        if (singleVo != null && list != null) {
            map.put("singleVo", singleVo);
            map.put("everyday", list);
        }
        return map;
    }

    //用于计算日期，以天为单位
    private Date getNewTime(Date time, int day) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(time);
        rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
        Date newTime = rightNow.getTime();
        return newTime;
    }

}
