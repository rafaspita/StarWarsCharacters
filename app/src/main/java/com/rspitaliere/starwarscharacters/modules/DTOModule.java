package com.rspitaliere.starwarscharacters.modules;

import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;
import com.rspitaliere.starwarscharacters.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rspitaliere on 24/01/18.
 */

@Module
public class DTOModule {

    @Provides
    @PerActivity
    public CharacterDTO provideCharacterDTO(){
        return new CharacterDTO();
    }

    @Provides
    @PerActivity
    public MovieDTO provideMovieDTO(){
        return new MovieDTO();
    }
}
