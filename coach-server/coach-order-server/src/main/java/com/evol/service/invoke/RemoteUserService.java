package com.evol.service.invoke;

import com.evol.domain.dto.UserBalanceDTO;
import com.evol.service.invoke.fallback.RemoteDeviceServiceFallback;
import com.evol.web.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="${userServer}", fallback = RemoteDeviceServiceFallback.class)
public interface RemoteUserService {


    /**
     * 批量查询租户设备数量
     * @param tenantIdList
     * @return
     */
    @GetMapping("/device/inner/batchQuantity")
    ApiResponse<List<UserBalanceDTO>> batchQuantity(@RequestParam("tenantIdList") List<Integer> tenantIdList);

}
