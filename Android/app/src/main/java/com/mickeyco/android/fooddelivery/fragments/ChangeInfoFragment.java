package com.mickeyco.android.fooddelivery.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mickeyco.android.fooddelivery.R;
import com.mickeyco.android.fooddelivery.api.ApiFactory;
import com.mickeyco.android.fooddelivery.api.RequestInterface;
import com.mickeyco.android.fooddelivery.api.models.UserInfo;
import com.mickeyco.android.fooddelivery.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Softteco on 03.05.2017.
 */

public class ChangeInfoFragment extends Fragment {

    private AppCompatButton btn_update;
    private EditText et_email, et_password, et_name, et_surname, et_address, et_number, et_login;
    private TextView tv_login;
    private ProgressBar progress;
    private UserInfo mUserInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        btn_update = (AppCompatButton)view.findViewById(R.id.bottom_btn);
        btn_update.setText("Обновить данные");

        et_login = (EditText) view.findViewById(R.id.et_login);
        et_password = (EditText)view.findViewById(R.id.et_password);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_surname = (EditText) view.findViewById(R.id.et_surname);
        et_address = (EditText) view.findViewById(R.id.et_address);
        et_number = (EditText) view.findViewById(R.id.et_number);
        et_email = (EditText)view.findViewById(R.id.et_email);

        progress = (ProgressBar)view.findViewById(R.id.register_progress_bar);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
            }
        });
        getUserInfo(PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt(Constants.PREF_ID, -1));
    }

    private void updateViews() {
        et_login.setText(mUserInfo.getUser().getLogin());
        et_password.setText(mUserInfo.getUser().getPass());
        et_name.setText(mUserInfo.getName());
        et_surname.setText(mUserInfo.getSurname());
        et_address.setText(mUserInfo.getAddress());
        et_number.setText(mUserInfo.getNumber());
        et_email.setText(mUserInfo.getEmail());
    }

    private void updateInfo() {

    }

    private void getUserInfo(int id) {
        if (id == -1) {
            progress.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "User not found!", Toast.LENGTH_LONG).show();

            return;
        }
        RequestInterface requestInterface = ApiFactory.getService();
        final Call<UserInfo> response = requestInterface.getUserInfo(id);
        response.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, retrofit2.Response<UserInfo> response) {
                mUserInfo = response.body();
                progress.setVisibility(View.INVISIBLE);
                updateViews();
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Snackbar.make(getView(), "ERROR", Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "User not found!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
