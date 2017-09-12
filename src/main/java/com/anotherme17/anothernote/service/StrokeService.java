package com.anotherme17.anothernote.service;

import com.anotherme17.anothernote.config.code.ResultCode;
import com.anotherme17.anothernote.entity.StrokeEntity;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface StrokeService {

    void insertStrokeAutoKey(StrokeEntity stroke);

    /**
     * 删除行程
     */
    ResultCode deleteStroke(String id);

    ResultCode updateStroke(StrokeEntity stroke);

    StrokeEntity getStrokeByID(String id);

    List<StrokeEntity> getStrokeInDate(Date left, Date right);
}
