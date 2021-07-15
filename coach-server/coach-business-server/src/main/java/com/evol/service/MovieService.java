package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.dto.MoiveUpsertDto;
import com.evol.domain.model.Movie;
import com.evol.domain.request.MovieQueryRequest;

public interface MovieService {

    Integer createMovie(MoiveUpsertDto dto);

    Integer updateMovie(Integer movieId, MoiveUpsertDto dto);

    Integer deleteMoive(Integer movieId);

    PageBase<Movie> queryPage(MovieQueryRequest movieQueryRequest);

    Movie getMovie(Integer movieId);

}
