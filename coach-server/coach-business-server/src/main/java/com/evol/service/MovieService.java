package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.model.Movie;
import com.evol.domain.request.MovieQueryRequest;

public interface MovieService {

    PageBase<Movie> queryPage(MovieQueryRequest movieQueryRequest);

}
