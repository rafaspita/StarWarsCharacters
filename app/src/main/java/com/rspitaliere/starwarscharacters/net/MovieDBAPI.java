package com.rspitaliere.starwarscharacters.net;

import com.rspitaliere.starwarscharacters.dto.DBMovieDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by rspitaliere on 22/01/18.
 */

public interface MovieDBAPI {
    @GET
    Call<DBMovieDTO> getMovieInfo(@Url String url, @Query("query") String key);
}
