package com.mickeyco.android.fooddelivery.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mickeyco.android.fooddelivery.activities.CategoryActivity;
import com.mickeyco.android.fooddelivery.activities.LoginActivity;
import com.mickeyco.android.fooddelivery.activities.RegisterActivity;
import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.Category;
import com.mickeyco.android.fooddelivery.api.models.LoginResponse;
import com.mickeyco.android.fooddelivery.api.models.ServerRequest;
import com.mickeyco.android.fooddelivery.api.models.ServerResponse;
import com.mickeyco.android.fooddelivery.api.models.User;
import com.mickeyco.android.fooddelivery.utils.BlurBuilder;
import com.mickeyco.android.fooddelivery.utils.Constants;
import com.mickeyco.android.fooddelivery.R;

import jp.wasabeef.blurry.Blurry;
import retrofit2.Call;
import retrofit2.Callback;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login, btn_register;
    private EditText et_email,et_password;
    private ProgressBar progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(getActivity()).getBoolean(Constants.IS_LOGGED_IN, false)) {
            startActivity(new Intent(getActivity(), CategoryActivity.class));
            getActivity().finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        Drawable drawable = new BitmapDrawable(getResources(), BlurBuilder.blur(getActivity(),
                BitmapFactory.decodeResource(getResources(), R.drawable.login_bg)));
        view.findViewById(R.id.background_LL).setBackground(drawable);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        btn_register = (AppCompatButton) view.findViewById(R.id.btn_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Blurry.with(getActivity())
                .radius(100)
                .sampling(80)
                .color(Color.argb(66, 255, 255, 0))
                .async()
                .animate(500)
                .onto((LinearLayout) view.findViewById(R.id.background_LL));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_register:
                goToRegister();
                break;

            case R.id.btn_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {
                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email,password);
                    Snackbar.make(getView(), "Авторизация успешна!", Snackbar.LENGTH_LONG).show();

                } else {

                    Snackbar.make(getView(), "Поля не заполнены!", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }
    private void loginProcess(String login, String password){

        RequestInterface requestInterface = ApiFactory.getService();
        Call<LoginResponse> response = requestInterface.login(login, password);
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                LoginResponse resp = response.body();
//                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
                if(resp.getMessage().equals("OK")){
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                            .putInt(Constants.PREF_ID, resp.getUserId())
                            .putBoolean(Constants.IS_LOGGED_IN, true)
                            .apply();
                    goToMenu();

                } else {
                    Toast.makeText(getActivity(), "Неверные данные", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void goToRegister(){
       startActivity(new Intent(getActivity(), RegisterActivity.class));
        getActivity().finish();
    }

    private void goToMenu(){
        startActivity(new Intent(getActivity(), CategoryActivity.class));
        getActivity().finish();
    }
}
