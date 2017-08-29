package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.entity.StrokeEntity;
import com.anotherme17.anothernote.result.BaseResult;
import com.anotherme17.anothernote.service.StrokeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 行程接口
 */
@Api(value = "/strokes", description = "行程接口")
@Controller
@RequestMapping(value = "v1/strokes")
public class StrokeAction {

    @Autowired
    private StrokeService mStrokeService;

    @ApiOperation(
            value = "创建行程", notes = "创建行程", httpMethod = "POST",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BaseResult<StrokeEntity> postStroke(
            @ModelAttribute StrokeEntity stroke,
            HttpServletResponse response
    ) {
        stroke.setId(null);
        stroke.setCreateTime(new Date());
        mStrokeService.insertStrokeAutoKey(stroke);
        return new BaseResult<>(1, "ok", stroke);
    }

    @ApiOperation(
            value = "删除行程", notes = "删除行程", httpMethod = "DELETE",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResult<String> deleteStroke(@PathVariable String id) {
        mStrokeService.deleteStroke(id);
        return new BaseResult<>(1, "ok", "删除成功");
    }

    @ApiOperation(
            value = "更新行程", notes = "更新行程", httpMethod = "PUT",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseResult<StrokeEntity> putStroke(
            @PathVariable String id,
            @ModelAttribute StrokeEntity stroke
    ) {
        stroke.setId(id);
        mStrokeService.updateStroke(stroke);
        return new BaseResult<>(1, "ok", stroke);
    }

    @ApiOperation(
            value = "获取行程详情", notes = "获取行程详情", httpMethod = "GET",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResult<StrokeEntity> getStroke(@PathVariable String id) {
        return new BaseResult<>(1, "ok", mStrokeService.getStrokeByID(id));
    }
}
