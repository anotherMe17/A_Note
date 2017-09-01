package com.anotherme17.anothernote.service;

import com.anotherme17.anothernote.entity.StrokeEntity;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface StrokeService {

    void insertStrokeAutoKey(StrokeEntity stroke);

    void deleteStroke(String id);

    void updateStroke(StrokeEntity stroke);

    StrokeEntity getStrokeByID(String id);

    List<StrokeEntity> getStrokeInDate(Date left, Date right);
}
