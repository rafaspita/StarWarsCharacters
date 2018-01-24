package com.rspitaliere.starwarscharacters.net;

import com.rspitaliere.starwarscharacters.dto.MovieDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rspitaliere on 22/01/18.
 */

public class CallAPIMovie {

    CallServiceInterface callServiceInterface;

    public CallAPIMovie(String url, CallServiceInterface callServiceInterface){
        this.callServiceInterface = callServiceInterface;
        callService(url);
    }

    private void callService(String url){
        SwAPIMovie swAPIMovie = RetrofitInit.getRetrofit().create(SwAPIMovie.class);
        Call<MovieDTO> call = swAPIMovie.getMovie(url);
        call.enqueue(new Callback<MovieDTO>() {
            @Override
            public void onResponse(Call<MovieDTO> call, Response<MovieDTO> response) {
                if (response.isSuccessful()){
                    callServiceInterface.succes(response.body());
                }
                callServiceInterface.fail();
            }

            @Override
            public void onFailure(Call<MovieDTO> call, Throwable t) {
                callServiceInterface.fail();
            }
        });

    }
}
