package com.rspitaliere.starwarscharacters.component;

import com.rspitaliere.starwarscharacters.activitys.LoginActivity;
import com.rspitaliere.starwarscharacters.activitys.MainActivity;
import com.rspitaliere.starwarscharacters.activitys.SplashScreenActivity;
import com.rspitaliere.starwarscharacters.dao.CharMovieDAO;
import com.rspitaliere.starwarscharacters.dao.CharacterDAO;
import com.rspitaliere.starwarscharacters.dao.MoviesDAO;
import com.rspitaliere.starwarscharacters.dao.SharedLocalDAO;
import com.rspitaliere.starwarscharacters.db.DBHandler;
import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;
import com.rspitaliere.starwarscharacters.fragments.InfoCharactersFragment;
import com.rspitaliere.starwarscharacters.modules.DBModule;
import com.rspitaliere.starwarscharacters.modules.DTOModule;
import com.rspitaliere.starwarscharacters.scope.PerActivity;

/**
 * Created by rspitaliere on 23/01/18.
 */

@PerActivity
@dagger.Component(modules = {DBModule.class, DTOModule.class})
public interface MyComponent {
    void inject(MainActivity activity);
    void inject(SplashScreenActivity splashScreenActivity);
    void inject(LoginActivity loginActivity);
    void inject(InfoCharactersFragment infoCharactersFragment);
    CharacterDAO provideCharacterDAO();
    MoviesDAO provideMoviesDAO();
    CharMovieDAO provideCharMovieDAO();
    SharedLocalDAO provideSharedLocalDAO();
    DBHandler provideDBHandler();
    CharacterDTO provideCharacterDTO();
    MovieDTO provideMovieDTO();
}
