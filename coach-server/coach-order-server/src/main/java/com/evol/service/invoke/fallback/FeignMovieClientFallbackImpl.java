package com.evol.service.invoke.fallback;

import com.evol.base.client.UserDTO;
import com.evol.domain.dto.MovieDetailDTO;
import com.evol.enums.ApiResponseEnum;
import com.evol.service.invoke.FeignMovieClient;
import com.evol.web.ApiResponse;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class FeignMovieClientFallbackImpl implements FeignMovieClient {
    @Override
    public ApiResponse<MovieDetailDTO> getMovie(Integer movieId) {
        return ApiResponse.fail(ApiResponseEnum.FEIGN_INVOKE_ERROR);
    }

    @Override
    public ApiResponse<MovieDetailDTO> getMovie2() {
        return ApiResponse.fail(ApiResponseEnum.FEIGN_INVOKE_ERROR);
    }

    @Override
    public UserDTO update(Long userId, UserDTO user) {
        return null;
    }


}
