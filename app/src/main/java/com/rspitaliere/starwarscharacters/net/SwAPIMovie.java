package com.rspitaliere.starwarscharacters.net;

import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by rspitaliere on 22/01/18.
 */

public interface SwAPIMovie {
    @GET
    Call<MovieDTO> getMovie(@Url String url);
}
