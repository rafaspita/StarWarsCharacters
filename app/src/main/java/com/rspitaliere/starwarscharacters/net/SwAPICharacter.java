package com.rspitaliere.starwarscharacters.net;

import com.rspitaliere.starwarscharacters.dto.CharacterDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by rspitaliere on 05/10/17.
 */

public interface SwAPICharacter {
    @GET
    Call<CharacterDTO> getCharacter(@Url String url);
}
