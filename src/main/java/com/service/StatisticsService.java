package com.service;

import com.Vo.DetectionVo;
import com.Vo.SingleVo;
import com.entity.Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<DetectionVo> history(Date startTime, Date endTime, Integer mainboardId,
                                     String mainboardModel, Integer defectId, Integer directorId,
                                     String detectionType, String eligibleStatus);

    SingleVo single(Date startTime, Date endTime);
    Map<String,Object> several(Date startTime, Date endTime);

}
