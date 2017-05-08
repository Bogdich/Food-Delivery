package com.mickeyco.android.fooddelivery.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.ServerRequest;
import com.mickeyco.android.fooddelivery.api.models.ServerResponse;
import com.mickeyco.android.fooddelivery.api.models.User;
import com.mickeyco.android.fooddelivery.utils.Constants;
import com.mickeyco.android.fooddelivery.R;

import retrofit2.Call;
import retrofit2.Callback;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;
    private CheckBox chck_doctor;
    private TextView tv_register;
    private ProgressBar progress;
    private SharedPreferences pref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        pref = getActivity().getSharedPreferences(Constants.PREFS_NAME, 0);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);
        chck_doctor = (CheckBox)view.findViewById(R.id.checkbox_doctor);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_register:
                goToRegister();
                break;

            case R.id.btn_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                Boolean isDoctor = chck_doctor.isChecked();

                if(!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email,password, isDoctor);

                } else {

                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }
    private void loginProcess(String email, String password, final Boolean isDoctor){

//        RequestInterface requestInterface = ApiFactory.getService();
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setDoctor(isDoctor);
//        ServerRequest request = new ServerRequest();
//        request.setOperation(Constants.LOGIN_OPERATION);
//        request.setUser(user);
//        Call<ServerResponse> response = requestInterface.registration(request);
//
//        response.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
//
//                ServerResponse resp = response.body();
//                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
//
//                if(resp.getResult().equals(Constants.SUCCESS)){
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
//                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
//                    editor.putString(Constants.NAME,resp.getUser().getName());
//                    editor.putBoolean(Constants.IS_DOCTOR, isDoctor);
//
//                    editor.apply();
//                    goToProfile();
//
//                }
//                progress.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//
//                progress.setVisibility(View.INVISIBLE);
//                Log.d(Constants.TAG,"failed");
//                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
//
//            }
//        });
    }

    private void goToRegister(){

//        Fragment register = new RegisterFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragmentContainer,register);
//        ft.commit();
    }

    private void goToProfile(){
//        Fragment profile;
//        if(pref.getBoolean(Constants.IS_DOCTOR, false))
//            profile = new DoctorFragment();
//        else
//            profile = new UserFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragmentContainer,profile);
//        ft.commit();
    }
}