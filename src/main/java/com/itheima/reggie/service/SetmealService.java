package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    // 新增套餐 同时保存套餐和菜品关系到关联关系表中
    void saveWithDish(SetmealDto setmealDto);

    // 删除套餐 同时删除套餐菜品关联关系表
    void removeWithDish(List<Long> ids);
}
