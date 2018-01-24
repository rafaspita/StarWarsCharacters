package com.rspitaliere.starwarscharacters.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rspitaliere on 21/01/18.
 */

public class FontUtils {
    private static final String FONT = "Starjedi.ttf";

    public static Typeface fontRegular(Context context){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), FONT);
        return tf;
    }

    public static final Pattern bound = Pattern.compile("\\b(\\w)");

    public static String titleize(String in) {
        String input = in.toLowerCase();
        StringBuffer sb = new StringBuffer(input.length());
        Matcher mat = bound.matcher(input);
        while (mat.find()) {
            mat.appendReplacement(sb, mat.group().toUpperCase());
        }
        mat.appendTail(sb);
        return sb.toString();
    }
}
