package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.User;
import com.itheima.reggie.service.UserService;
import com.itheima.reggie.utils.SMSUtils;
import com.itheima.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 发送手机验证短息
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // 获取手机号
        String phone = user.getPhone();
        log.info("手机号：{}",phone);

        if(StringUtils.isNotEmpty(phone)){
            // 生成随机数验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("验证码：{}",code);

            // 调用阿里云短信服务发送短息
//            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);
            // 保存验证码到session中
//            session.setAttribute(phone,code);

            // 将验证码缓存到redis中，并且设置有效期5分钟
            stringRedisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);

            return R.success("发送成功！");
        }
        return R.success("发送失败！");
    }

    // 移动端用户登录
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());
        // 获取手机号
        String phone = map.get("phone").toString();
        // 获取验证码
        String code = map.get("code").toString();

        // 从session域中获取保存的验证码
//        Object codeInSession = session.getAttribute(phone);

        // 从redis中获取缓存的验证码
        String codeInRedis = stringRedisTemplate.opsForValue().get(phone);

        if(codeInRedis != null && codeInRedis.equals(code)){
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);

            User user = userService.getOne(queryWrapper);
            if(user == null){
                // 用户不存在表中 自动注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            // 登录成功后需要将该用户id保存到session中
            session.setAttribute("user",user.getId());

            // 登录成功 删除缓存的验证码
            stringRedisTemplate.delete(phone);

            return R.success(user);
        }
        return R.error("验证码错误！");
    }
}
