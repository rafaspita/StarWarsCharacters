package com.rspitaliere.starwarscharacters.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rspitaliere.starwarscharacters.R;
import com.rspitaliere.starwarscharacters.activitys.MainActivity;
import com.rspitaliere.starwarscharacters.application.MainApplication;
import com.rspitaliere.starwarscharacters.adapters.movies.MovieAdapter;
import com.rspitaliere.starwarscharacters.adapters.movies.MovieAdapterClickListener;
import com.rspitaliere.starwarscharacters.dao.CharMovieDAO;
import com.rspitaliere.starwarscharacters.dao.CharacterDAO;
import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;
import com.rspitaliere.starwarscharacters.utils.FontUtils;
import com.rspitaliere.starwarscharacters.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoCharactersFragment extends Fragment {

    @BindView(R.id.info_height) TextView height;
    @BindView(R.id.info_mass) TextView mass;
    @BindView(R.id.info_hair) TextView hair;
    @BindView(R.id.info_skin) TextView skin;
    @BindView(R.id.info_eye) TextView eye;
    @BindView(R.id.info_birth) TextView birth;
    @BindView(R.id.info_gender) TextView gender;
    @BindView(R.id.info_height_text) TextView heightT;
    @BindView(R.id.info_mass_text) TextView massT;
    @BindView(R.id.info_hair_text) TextView hairT;
    @BindView(R.id.info_skin_text) TextView skinT;
    @BindView(R.id.info_eye_text) TextView eyeT;
    @BindView(R.id.info_birth_text) TextView birthT;
    @BindView(R.id.info_gender_text) TextView genderT;
    @BindView(R.id.info_title) TextView title;
    @BindView(R.id.info_time) TextView time;
    @BindView(R.id.info_time_text) TextView timeT;
    @BindView(R.id.info_movies) TextView moviesT;
    @BindView(R.id.info_lat_long_text) TextView latlongT;
    @BindView(R.id.info_lat_long) TextView latlong;
    @BindView(R.id.info_recycler_view_movies) RecyclerView recyclerView;

    private CharacterDTO characterDTO;
    @Inject CharMovieDAO charMovieDAO;
    @Inject CharacterDAO characterDAO;
    List<MovieDTO> movies;

    public InfoCharactersFragment() {
        // Required empty public constructor
    }

    public void setCharacterDTO(CharacterDTO characterDTO){
        this.characterDTO = characterDTO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_characters, container, false);

        ButterKnife.bind(this,view);
        MainApplication.getMyComponent().inject(this);
        ((MainActivity) getActivity()).setActionBarTitle(characterDTO.getName());

        movies = charMovieDAO.select(characterDTO.getId());
        characterDTO = characterDAO.selectChar(characterDTO.getId());

        setTextsInfo();
        setFont();
        setAdapter();
        return view;
    }

    public void setAdapter() {
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movies, new MovieAdapterClickListener() {
            @Override
            public void buttonMoreInformation(View v, int position) {
                goToWeb(position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(movieAdapter);
    }

    private void goToWeb(int position) {
        String url = "http://image.tmdb.org/t/p/w500" + movies.get(position).getImageUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void setTextsInfo(){
        height.setText(characterDTO.getHeight() + "CM");
        mass.setText(characterDTO.getMass() + "KG");
        hair.setText(characterDTO.getHairColor());
        skin.setText(characterDTO.getSkinColor());
        eye.setText(characterDTO.getEyeColor());
        birth.setText(characterDTO.getBirthYear());
        gender.setText(characterDTO.getGender());
        latlong.setText("(" + characterDTO.getLatitude() + "," + characterDTO.getLongitude() + ")");
        time.setText(Utils.formatDate(characterDTO.getTime()));
    }

    private void setFont(){
        Context c = getContext();
        height.setTypeface(FontUtils.fontRegular(c));
        mass.setTypeface(FontUtils.fontRegular(c));
        hair.setTypeface(FontUtils.fontRegular(c));
        skin.setTypeface(FontUtils.fontRegular(c));
        eye.setTypeface(FontUtils.fontRegular(c));
        birth.setTypeface(FontUtils.fontRegular(c));
        gender.setTypeface(FontUtils.fontRegular(c));
        heightT.setTypeface(FontUtils.fontRegular(c));
        massT.setTypeface(FontUtils.fontRegular(c));
        hairT.setTypeface(FontUtils.fontRegular(c));
        skinT.setTypeface(FontUtils.fontRegular(c));
        eyeT.setTypeface(FontUtils.fontRegular(c));
        birthT.setTypeface(FontUtils.fontRegular(c));
        genderT.setTypeface(FontUtils.fontRegular(c));
        title.setTypeface(FontUtils.fontRegular(c));
        timeT.setTypeface(FontUtils.fontRegular(c));
        time.setTypeface(FontUtils.fontRegular(c));
        moviesT.setTypeface(FontUtils.fontRegular(c));
        latlongT.setTypeface(FontUtils.fontRegular(c));
        latlong.setTypeface(FontUtils.fontRegular(c));
    }


}
