package com.usuario.service.feignclients;

import com.usuario.service.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="login-service",url="http://localhost:8081")
public interface UserFeignLogin {
    @PostMapping("/login")
    public String loginUser(@RequestBody User user);
}
