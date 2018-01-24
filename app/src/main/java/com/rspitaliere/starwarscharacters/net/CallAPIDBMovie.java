package com.rspitaliere.starwarscharacters.net;

import com.rspitaliere.starwarscharacters.dao.MoviesDAO;
import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.DBMovieDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rspitaliere on 22/01/18.
 */

public class CallAPIDBMovie {

    MoviesDAO moviesDAO;

    public CallAPIDBMovie(String movieName){
        moviesDAO = new MoviesDAO();
        callService(movieName);
    }

    private void callService(final String movieName){
        String url = "http://api.themoviedb.org/3/search/movie?api_key=2291ca1b9fa86bdc2c5b257c55a71e92&";
        String sw = "star wars " + movieName;
        MovieDBAPI movieDBAPI = RetrofitInit.getRetrofit().create(MovieDBAPI.class);
        Call<DBMovieDTO> call = movieDBAPI.getMovieInfo(url, sw);
        call.enqueue(new Callback<DBMovieDTO>() {
            @Override
            public void onResponse(Call<DBMovieDTO> call, Response<DBMovieDTO> response) {
                if (response.isSuccessful()){
                    moviesDAO.update(response.body().getResults().get(0), movieName);
                }
            }

            @Override
            public void onFailure(Call<DBMovieDTO> call, Throwable t) {

            }
        });
    }
}
