package com.rspitaliere.starwarscharacters.activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rspitaliere.starwarscharacters.R;
import com.rspitaliere.starwarscharacters.application.MainApplication;
import com.rspitaliere.starwarscharacters.dao.SharedKey;
import com.rspitaliere.starwarscharacters.dao.SharedLocalDAO;
import com.rspitaliere.starwarscharacters.utils.FontUtils;
import com.rspitaliere.starwarscharacters.utils.Validator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_button)
    Button button;
    @BindView(R.id.login_name)
    EditText editText;

    @Inject SharedLocalDAO sharedLocalDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MainApplication.getMyComponent().inject(this);
        ButterKnife.bind(this);
        setFont();
    }

    @OnClick(R.id.login_button)
    public void saveUserName(){
        if (Validator.validateEditTextNotNull(editText, "Nome não informado!")){
            sharedLocalDAO.save(SharedKey.USER_NAME_KEY, editText.getText().toString());
            goToMainActivity();
        }
    }

    private void goToMainActivity(){
        Intent it = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(it);
        LoginActivity.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    private void setFont(){
        editText.setTypeface(FontUtils.fontRegular(this));
        button.setTypeface(FontUtils.fontRegular(this));
    }
}
