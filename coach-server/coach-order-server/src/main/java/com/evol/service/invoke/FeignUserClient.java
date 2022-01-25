package com.evol.service.invoke;

import com.evol.base.client.UserDTO;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.service.invoke.fallback.FeignUserClientFallbackImpl;
import com.evol.web.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="coach-user-server", fallback = FeignUserClientFallbackImpl.class)
public interface FeignUserClient {


    @GetMapping(value = "inner/user/get")
    ApiResponse<UserDTO>  getUserById(@RequestParam(value = "userId") Integer userId);

    @PostMapping(value = "inner/user/updateUserBalanceSync")
    ApiResponse<Boolean>  updateUserBalanceSync(UpdateUserBalanceParam updateBalanceParam);

}
