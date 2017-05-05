package com.mickeyco.android.fooddelivery.fragments;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.RegistrationResponse;
import com.mickeyco.android.fooddelivery.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_email, et_password, et_name, et_surname, et_address, et_number, et_login;
    private TextView tv_login;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        btn_register = (AppCompatButton)view.findViewById(R.id.bottom_btn);
        btn_register.setText("Зарегистрироваться");
        tv_login = (TextView)view.findViewById(R.id.tv_login);
        tv_login.setVisibility(View.VISIBLE);

        et_login = (EditText) view.findViewById(R.id.et_login);
        et_password = (EditText)view.findViewById(R.id.et_password);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_surname = (EditText) view.findViewById(R.id.et_surname);
        et_address = (EditText) view.findViewById(R.id.et_address);
        et_number = (EditText) view.findViewById(R.id.et_number);
        et_email = (EditText)view.findViewById(R.id.et_email);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_login:
                goToLogin();
                break;

            case R.id.bottom_btn:

                String login = et_login.getText().toString();
                String password = et_password.getText().toString();
                String name = et_name.getText().toString();
                String surname = et_surname.getText().toString();
                String address = et_address.getText().toString();
                String number = et_number.getText().toString();
                String email = et_email.getText().toString();


                if(!login.isEmpty() && !password.isEmpty() && !name.isEmpty() && !surname.isEmpty() &&
                        !address.isEmpty() && !number.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    progress.setVisibility(View.VISIBLE);
                    registerProcess(login, password, name, surname, address, number, email);

                } else {
                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;

        }

    }

    private void registerProcess(String login, String password, String name, String surname,
                                 String address, String number, String email){

        RequestInterface requestInterface = ApiFactory.getService();
        final Call<RegistrationResponse> response = requestInterface.registration(login, password,
                name, surname, address, number, email
        );


        response.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, retrofit2.Response<RegistrationResponse> response) {

                RegistrationResponse resp = response.body();
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().
                        putInt(Constants.PREF_ID, resp.getUserId()).
                        apply();
                Snackbar.make(getView(), "Registration successful", Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), "ERROR", Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void goToLogin(){

//        Fragment login = new LoginFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragmentContainer,login);
//        ft.commit();
    }
}
