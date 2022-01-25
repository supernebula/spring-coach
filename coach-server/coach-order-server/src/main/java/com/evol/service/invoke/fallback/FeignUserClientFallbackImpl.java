package com.evol.service.invoke.fallback;

import com.evol.base.client.UserDTO;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.invoke.FeignUserClient;
import com.evol.web.ApiResponse;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class FeignUserClientFallbackImpl implements FeignUserClient {
    @Override
    public ApiResponse<UserDTO> getUserById(Integer userId) {
        return ApiResponse.fail(ApiResponseEnum.FEIGN_INVOKE_ERROR);
    }

    @Override
    public ApiResponse<Boolean> updateUserBalanceSync(UpdateUserBalanceParam updateBalanceParam) {
        return ApiResponse.fail(ApiResponseEnum.FEIGN_INVOKE_ERROR);
    }
}
