package com.coforge.training.flipzon.adminservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coforge.training.flipzon.adminservice.dto.UserDTO;

@FeignClient(name = "FLIPZON-USER-SERVICE")
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

}