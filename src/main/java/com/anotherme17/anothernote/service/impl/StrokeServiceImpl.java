package com.anotherme17.anothernote.service.impl;

import com.anotherme17.anothernote.entity.StrokeEntity;
import com.anotherme17.anothernote.mapper.StrokeMapper;
import com.anotherme17.anothernote.service.StrokeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class StrokeServiceImpl implements StrokeService {

    @Autowired
    private StrokeMapper mStrokeMapper;

    @Override
    public void insertStrokeAutoKey(StrokeEntity stroke) {
        mStrokeMapper.insertStrokeAutoKey(stroke);
    }

    @Override
    public void deleteStroke(String id) {
        mStrokeMapper.deleteStroke(id);
    }

    @Override
    public void updateStroke(StrokeEntity stroke) {
        mStrokeMapper.updateStroke(stroke);
    }

    @Override
    public StrokeEntity getStrokeByID(String id) {
        return mStrokeMapper.getStrokeByID(id);
    }

    @Override
    public List<StrokeEntity> getStrokeInDate(Date left, Date right) {
        return mStrokeMapper.getStrokeInDate(left, right);
    }
}
