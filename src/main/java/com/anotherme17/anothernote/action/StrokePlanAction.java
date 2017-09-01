package com.anotherme17.anothernote.action;

import com.anotherme17.anothernote.entity.StrokePlanEntity;
import com.anotherme17.anothernote.entity.UserEntity;
import com.anotherme17.anothernote.result.BaseResult;
import com.anotherme17.anothernote.service.StrokePlanService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * StrokePlanAction
 */
@Api(value = "/strokePlans", description = "行程计划接口")
@Controller
@RequestMapping(value = "v1/strokePlans")
public class StrokePlanAction {

    @Autowired
    private StrokePlanService mStrokePlanService;

    @ApiOperation(
            value = "创建行程计划", notes = "创建行程计划", httpMethod = "POST",
            produces = "application/json", tags = {"行程计划"})
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    //@RequiresRoles("admin")
    @RequiresPermissions(value = {"strokePlan:create"}, logical = Logical.OR)
    public BaseResult<StrokePlanEntity> postStrokePlan(
            @ModelAttribute StrokePlanEntity strokePlan,
            HttpServletResponse response
    ) {
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();

        strokePlan.setId(null);
        strokePlan.setCreateTime(new Date());
        strokePlan.setForUserID(user.getId());
        mStrokePlanService.insertStrokePlan(strokePlan);
        return new BaseResult<>(1, "ok", strokePlan);
    }
}
