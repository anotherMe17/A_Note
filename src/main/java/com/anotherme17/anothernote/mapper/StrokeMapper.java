package com.anotherme17.anothernote.mapper;

import com.anotherme17.anothernote.entity.StrokeEntity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Mapper
@Repository
public interface StrokeMapper {

    /**
     * 插入行程,自动生成主键
     */
    void insertStrokeAutoKey(StrokeEntity stroke);

    /**
     * 删除行程
     */
    void deleteStroke(String id);

    /**
     * 更新行程
     */
    void updateStroke(StrokeEntity stroke);

    /**
     * 通过ID获取行程
     */
    StrokeEntity getStrokeByID(String id);
}
