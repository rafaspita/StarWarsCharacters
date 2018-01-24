package com.rspitaliere.starwarscharacters.net;

import com.rspitaliere.starwarscharacters.dto.CharacterDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rspitaliere on 22/01/18.
 */

public class CallAPICharacter {

    CallServiceInterface callServiceInterface;

    public CallAPICharacter(String url, CallServiceInterface callServiceInterface){
        this.callServiceInterface = callServiceInterface;
        callService(url);
    }

    private void callService(String url){
        SwAPICharacter swAPICharacter = RetrofitInit.getRetrofit().create(SwAPICharacter.class);
        Call<CharacterDTO> call = swAPICharacter.getCharacter(url);
        call.enqueue(new Callback<CharacterDTO>() {
            @Override
            public void onResponse(Call<CharacterDTO> call, Response<CharacterDTO> response) {
                if (response.isSuccessful()){
                    callServiceInterface.succes(response.body());
                }
                callServiceInterface.fail();
            }

            @Override
            public void onFailure(Call<CharacterDTO> call, Throwable t) {
                callServiceInterface.fail();
            }
        });
    }
}
