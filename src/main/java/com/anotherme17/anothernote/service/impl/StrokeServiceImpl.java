package com.anotherme17.anothernote.service.impl;

import com.anotherme17.anothernote.config.code.ResultCode;
import com.anotherme17.anothernote.entity.StrokeEntity;
import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.mapper.StrokeMapper;
import com.anotherme17.anothernote.service.StrokeService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.anotherme17.anothernote.config.code.ResultCode.DELETE_REFUSE;
import static com.anotherme17.anothernote.config.code.ResultCode.OK;
import static com.anotherme17.anothernote.config.code.ResultCode.UNKNOWN_RECORD;

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
    public ResultCode deleteStroke(String id) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        StrokeEntity stroke = mStrokeMapper.getStrokeByID(id);
        if (stroke == null)
            return UNKNOWN_RECORD;

        if (!user.getId().equals(stroke.getForUserID()))
            return DELETE_REFUSE;

        mStrokeMapper.deleteStroke(id);
        return OK;
    }

    @Override
    public ResultCode updateStroke(StrokeEntity stroke) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        StrokeEntity s = mStrokeMapper.getStrokeByID(stroke.getId());
        if (s == null)
            return UNKNOWN_RECORD;

        if (!user.getId().equals(s.getForUserID()))
            return DELETE_REFUSE;
        mStrokeMapper.updateStroke(stroke);
        return OK;
    }

    @Override
    public StrokeEntity getStrokeByID(String id) {
        StrokeEntity stroke = mStrokeMapper.getStrokeByID(id);

        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if (stroke == null || !user.getId().equals(stroke.getForUserID())) return null;

        return stroke;
    }

    @Override
    public List<StrokeEntity> getStrokeInDate(Date left, Date right) {
        return mStrokeMapper.getStrokeInDate(left, right);
    }
}
