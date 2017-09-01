package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.StrokePlanEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * StrokePlanMapper
 */
@Mapper
@Repository
public interface StrokePlanMapper {

    void insertStrokePlanAutoKey(StrokePlanEntity entity);

    void updateStrokePlan(StrokePlanEntity entity);

    StrokePlanEntity getStrokePlainByID(String id);

    List<StrokePlanEntity> getStrokePlanBeforeDate(Date date);
}
