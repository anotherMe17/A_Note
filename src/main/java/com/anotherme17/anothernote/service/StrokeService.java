package com.anotherme17.anothernote.service;

import com.anotherme17.anothernote.entity.StrokeEntity;

/**
 *
 */
public interface StrokeService {

    void insertStrokeAutoKey(StrokeEntity stroke);

    void deleteStroke(String id);

    void updateStroke(StrokeEntity stroke);

    StrokeEntity getStrokeByID(String id);
}
