package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    // 扩展方法 同时插入菜品和菜品口味表
    void saveWithFlavor(DishDto dishDto);

    // 根据id查询菜品信息和口味信息用于页面回显展示数据
    DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息和口味信息
    void updateWithFlavor(DishDto dishDto);
}
