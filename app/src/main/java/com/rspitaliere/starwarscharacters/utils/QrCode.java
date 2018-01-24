package com.rspitaliere.starwarscharacters.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;

/**
 * Created by rspitaliere on 23/01/18.
 */

public class QrCode {

    public static String getUrlFromQrCode(Context context, IntentResult result) {
        if (result.getContents() == null) {
            Toast.makeText(context, "QRCode Inválido!", Toast.LENGTH_LONG).show();
            return null;
        } else {
            try {
                String url = result.getContents();
                if (url.contains("https://swapi.co/api/people/")){
                    //createCallAPICharacter(url);
                    return url;
                } else {
                    Toast.makeText(context, "QRCode Inválido!", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Erro: " + result.getContents(), Toast.LENGTH_LONG).show();
                return null;
            }
        }
        return null;
    }
}
