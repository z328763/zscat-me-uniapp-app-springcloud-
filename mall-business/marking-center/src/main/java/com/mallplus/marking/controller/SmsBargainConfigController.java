package com.mallplus.marking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mallplus.common.annotation.SysLog;
import com.mallplus.common.entity.sms.SmsBargainConfig;
import com.mallplus.marking.service.ISmsBargainConfigService;
import com.mallplus.common.utils.CommonResult;
import com.mallplus.common.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 砍价免单设置表 前端控制器
 * </p>
 *
 * @author zscat
 * @since 2019-10-17
 */
@Slf4j
@RestController
@RequestMapping("/sms/smsBargainConfig")
public class SmsBargainConfigController {
    @Resource
    private ISmsBargainConfigService ISmsCouponService;

    @SysLog(MODULE = "sms", REMARK = "根据条件查询所有砍价免单设置表列表")
    @ApiOperation("根据条件查询所有砍价免单设置表列表")
    @GetMapping(value = "/list")
    public Object getSmsCouponByPage(SmsBargainConfig entity,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsCouponService.page(new Page<SmsBargainConfig>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有砍价免单设置表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "保存砍价免单设置表")
    @ApiOperation("保存砍价免单设置表")
    @PostMapping(value = "/create")
    public Object saveSmsCoupon(@RequestBody SmsBargainConfig entity) {
        try {
            if (ISmsCouponService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存砍价免单设置表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "更新砍价免单设置表")
    @ApiOperation("更新砍价免单设置表")
    @PostMapping(value = "/update/{id}")
    public Object updateSmsCoupon(@RequestBody SmsBargainConfig entity) {
        try {
            if (ISmsCouponService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新砍价免单设置表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "删除砍价免单设置表")
    @ApiOperation("删除砍价免单设置表")
    @GetMapping(value = "/delete/{id}")
    public Object deleteSmsCoupon(@ApiParam("砍价免单设置表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("砍价免单设置表id");
            }
            if (ISmsCouponService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除砍价免单设置表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "给砍价免单设置表分配砍价免单设置表")
    @ApiOperation("查询砍价免单设置表明细")
    @GetMapping(value = "/{id}")
    public Object getSmsCouponById(@ApiParam("砍价免单设置表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("砍价免单设置表id");
            }
            SmsBargainConfig coupon = ISmsCouponService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询砍价免单设置表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }
}

