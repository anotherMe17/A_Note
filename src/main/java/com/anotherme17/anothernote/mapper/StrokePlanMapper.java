package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.StrokePlanEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * StrokePlanMapper
 */
@Mapper
@Repository
public interface StrokePlanMapper {

    void insertStrokePlanAutoKey(StrokePlanEntity entity);

    StrokePlanEntity getStrokePlainByID(String id);
}
