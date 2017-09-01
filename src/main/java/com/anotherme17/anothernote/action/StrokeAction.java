package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.entity.StrokeEntity;
import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.result.BaseResult;
import com.anotherme17.anothernote.service.StrokeService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    @RequiresPermissions(value = {"stroke:common:create"}, logical = Logical.OR)
    public BaseResult<StrokeEntity> postStroke(
            @ModelAttribute StrokeEntity stroke,
            HttpServletResponse response
    ) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        stroke.setId(null);
        stroke.setCreateTime(new Date());
        stroke.setForUserID(user.getId());
        mStrokeService.insertStrokeAutoKey(stroke);
        return new BaseResult<>(1, "ok", stroke);
    }

    @ApiOperation(
            value = "删除行程", notes = "删除行程", httpMethod = "DELETE",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @RequiresPermissions(value = {"stroke:common:del"}, logical = Logical.OR)
    public BaseResult<String> deleteStroke(@PathVariable String id) {
        switch (mStrokeService.deleteStroke(id)) {
            case 0:
                return new BaseResult<>(0, "该行程不存在");
            case 1:
                return new BaseResult<>(1, "删除成功");
            case 2:
                return new BaseResult<>(0, "不能删除他人的行程");
            default:
                return new BaseResult<>(0, "未知错误");
        }
    }

    @ApiOperation(
            value = "更新行程", notes = "更新行程", httpMethod = "PUT",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @RequiresPermissions(value = {"stroke:common:update"}, logical = Logical.OR)
    public BaseResult<String> putStroke(
            @PathVariable String id,
            @ModelAttribute StrokeEntity stroke
    ) {
        stroke.setId(id);
        switch (mStrokeService.updateStroke(stroke)) {
            case 0:
                return new BaseResult<>(0, "该行程不存在");
            case 1:
                return new BaseResult<>(1, "修改成功");
            case 2:
                return new BaseResult<>(0, "不能修改他人的行程");
            default:
                return new BaseResult<>(0, "未知错误");
        }
    }

    @ApiOperation(
            value = "获取行程详情", notes = "获取行程详情", httpMethod = "GET",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @RequiresPermissions(value = {"stroke:common:get"}, logical = Logical.OR)
    public BaseResult<StrokeEntity> getStroke(@PathVariable String id) {
        return new BaseResult<>(1, "ok", mStrokeService.getStrokeByID(id));
    }

    @ApiOperation(
            value = "获取行程列表", notes = "获取行程列表", httpMethod = "GET",
            produces = "application/json", tags = {"行程"})
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @RequiresPermissions(value = {"stroke:common:list"}, logical = Logical.OR)
    public BaseResult<StrokeEntity> getStrokeList(
            @ApiParam(value = "第几页", required = true) @RequestParam(value = "page") int page,
            @ApiParam(value = "每页多少行", required = true) @RequestParam(value = "rows") int rows) {
        return new BaseResult<>(1, "ok");
    }
}
