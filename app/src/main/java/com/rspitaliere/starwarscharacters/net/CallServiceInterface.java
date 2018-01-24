package com.rspitaliere.starwarscharacters.net;

import android.view.View;

import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;

public interface CallServiceInterface {
    void succes(CharacterDTO characterDTO);
    void succes(MovieDTO movieDTO);
    void fail();
}