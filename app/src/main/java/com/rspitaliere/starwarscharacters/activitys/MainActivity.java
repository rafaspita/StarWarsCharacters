package com.rspitaliere.starwarscharacters.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rspitaliere.starwarscharacters.application.MainApplication;
import com.rspitaliere.starwarscharacters.dao.SharedKey;
import com.rspitaliere.starwarscharacters.dao.SharedLocalDAO;
import com.rspitaliere.starwarscharacters.fragments.InfoCharactersFragment;
import com.rspitaliere.starwarscharacters.R;
import com.rspitaliere.starwarscharacters.adapters.characters.CharacterAdapter;
import com.rspitaliere.starwarscharacters.adapters.characters.CharacterAdapterClickListener;
import com.rspitaliere.starwarscharacters.adapters.characters.CharacterClassAdapter;
import com.rspitaliere.starwarscharacters.dao.CharMovieDAO;
import com.rspitaliere.starwarscharacters.dao.CharacterDAO;
import com.rspitaliere.starwarscharacters.dao.MoviesDAO;
import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;
import com.rspitaliere.starwarscharacters.net.CallAPICharacter;
import com.rspitaliere.starwarscharacters.net.CallAPIDBMovie;
import com.rspitaliere.starwarscharacters.net.CallAPIMovie;
import com.rspitaliere.starwarscharacters.net.CallServiceInterface;
import com.rspitaliere.starwarscharacters.permissions.Permissions;
import com.rspitaliere.starwarscharacters.permissions.PermissionsName;
import com.rspitaliere.starwarscharacters.utils.Geolocation;
import com.rspitaliere.starwarscharacters.utils.PortraitScan;
import com.rspitaliere.starwarscharacters.utils.QrCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_characters) RecyclerView recyclerView;
    @BindView(R.id.frame_recycler) ConstraintLayout frameRecycler;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Inject CharacterDAO characterDAO;
    @Inject CharMovieDAO charMovieDAO;
    @Inject MoviesDAO movieDAO;
    @Inject SharedLocalDAO sharedLocalDAO;

    private Permissions permissions;

    private LinearLayoutManager linearLayoutManager;
    private List<CharacterDTO> characterDTOList;
    @Inject CharacterDTO characterDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainApplication.getMyComponent().inject(this);

        permissions = new Permissions(this, this);

        setAdapter();
    }

    @OnClick(R.id.floatingActionButton)
    public void initScan() {
        IntentIntegrator qrScan;
        qrScan = new IntentIntegrator(this);
        qrScan.setBarcodeImageEnabled(true);
        qrScan.setPrompt("Posicionar o QRCode no quadrado acima para adicionar um novo personagem");
        qrScan.setCaptureActivity(PortraitScan.class);
        qrScan.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String url = QrCode.getUrlFromQrCode(this,result);
            if ( url != null){
                createCallAPICharacter(url);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for( int i = 0; i < permissions.length; i++ ) {
            if (permissions[i].equalsIgnoreCase(Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                getGeolocation();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //Save
    private void saveData(CharacterDTO characterDTO){
        this.characterDTO = characterDTO;
        long row = characterDAO.insert(characterDTO);
        getGeolocation();
        for (String url: characterDTO.getFilms()){
            charMovieDAO.insert(url, row);
            if (!movieDAO.verifyMovieInTable(url)){
                System.err.println("movie is in table: " + movieDAO.verifyMovieInTable(url));
                createCallAPIMovie(url);
            }
        }
    }

    private void getGeolocation(){
        if (permissions.checkAndRequestPermissions(Arrays.asList(PermissionsName.LOCAL))){
            new Geolocation(this, this.characterDTO.getName());
        }
    }

    private void saveMovieInfo(MovieDTO movieDTO){
        movieDAO.insert(movieDTO);
        if (!movieDAO.verifyImageinTable(movieDTO.getUrl())){
            new CallAPIDBMovie(movieDTO.getTitle());
        }

    }
    //-----
    private void createCallAPIMovie(String url){
        new CallAPIMovie(url, new CallServiceInterface() {
            @Override
            public void succes(CharacterDTO characterDTO) {

            }

            @Override
            public void succes(MovieDTO movieDTO) {
                saveMovieInfo(movieDTO);
            }

            @Override
            public void fail() {

            }
        });
    }

    private void createCallAPICharacter(String url){
        progressBar.setVisibility(View.VISIBLE);
        new CallAPICharacter(url, new CallServiceInterface() {
            @Override
            public void succes(CharacterDTO characterDTO) {
                saveData(characterDTO);
            }

            @Override
            public void succes(MovieDTO movieDTO) {

            }

            @Override
            public void fail() {
                setAdapter();
            }
        });
    }
    //----
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        frameRecycler.setVisibility(View.VISIBLE);
        setActionBarTitle("Star Wars Characters");
    }

    //setAdapterRecyclerView
    private void setAdapter() {
        final List<CharacterClassAdapter> list = populeList();
        CharacterAdapter characterAdapter = new CharacterAdapter(this, list, new CharacterAdapterClickListener() {
            @Override
            public void buttonMoreInformation(View v, int position) {
                if (characterDTOList.size() != 0) {
                    goToInfo(characterDTOList.get(position));
                }
            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(characterAdapter);
        progressBar.setVisibility(View.GONE);
    }

    private List<CharacterClassAdapter> populeList(){
        String user = sharedLocalDAO.getString(SharedKey.USER_NAME_KEY);
        characterDTOList = characterDAO.selectCharacters();

        List<CharacterClassAdapter> listAdapter = new ArrayList<>();
        for (CharacterDTO characterDTO: characterDTOList){
            CharacterClassAdapter characterClassAdapter = new CharacterClassAdapter(characterDTO.getName(), user, characterDTO.getUrl());
            listAdapter.add(characterClassAdapter);
        }

        if (characterDTOList.size() == 0){
            CharacterClassAdapter characterClassAdapter = new CharacterClassAdapter(user + ", \n Você não encontrou nenhum personagem",
                    "Clique no botão para capturar um!", null);
            listAdapter.add(characterClassAdapter);
        }

        return listAdapter;
    }
    //-------

    //init Info
    private void goToInfo(CharacterDTO characterDTO){
        frameRecycler.setVisibility(View.GONE);
        InfoCharactersFragment infoCharactersFragment = new InfoCharactersFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        infoCharactersFragment.setCharacterDTO(characterDTO);
        fragmentManager.beginTransaction().replace(R.id.main_frame, infoCharactersFragment).addToBackStack(null).commit();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
