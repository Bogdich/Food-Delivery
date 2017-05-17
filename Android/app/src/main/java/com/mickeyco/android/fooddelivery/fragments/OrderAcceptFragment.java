package com.mickeyco.android.fooddelivery.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created by Softteco on 08.05.2017.
 */

public class OrderAcceptFragment extends Fragment {

    private Button createOrderBtn;
    private EditText et_email, et_name, et_surname, et_address, et_number;
    private TextView tv_login;
    private ProgressBar progress;
    private UserInfo mUserInfo;
    private static String ARG_TOTAL_PRICE = "ARG_TOTAL_PRICE";
    private int mTotalPrice;

    public static OrderAcceptFragment newInstance(int totalPrice) {
        Bundle args = new Bundle();
        args.putInt(ARG_TOTAL_PRICE, totalPrice);
        OrderAcceptFragment fragment = new OrderAcceptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTotalPrice = getArguments().getInt(ARG_TOTAL_PRICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accept_order,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        createOrderBtn = (Button) view.findViewById(R.id.create_order_btn);
        createOrderBtn.setText("Итого: " + mTotalPrice + " BYN\nЗаказать сейчас");
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_surname = (EditText) view.findViewById(R.id.et_surname);
        et_address = (EditText) view.findViewById(R.id.et_address);
        et_number = (EditText) view.findViewById(R.id.et_number);
        et_email = (EditText)view.findViewById(R.id.et_email);

        progress = (ProgressBar)view.findViewById(R.id.accept_progress_bar);

        createOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrder();
            }
        });
        getUserInfo(PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt(Constants.PREF_ID, -1));
    }

    private void updateViews() {
        et_name.setText(mUserInfo.getName());
        et_surname.setText(mUserInfo.getSurname());
        et_address.setText(mUserInfo.getAddress());
        et_number.setText(mUserInfo.getNumber());
        et_email.setText(mUserInfo.getEmail());
    }

    private void createOrder() {
        Toast.makeText(getActivity(), "Заказ оформлен!\nЖдите звонка оператора!", Toast.LENGTH_LONG).show();
    }

    private void getUserInfo(int id) {
        if (id == -1) {
            progress.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Пользователь не найден", Toast.LENGTH_LONG).show();

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
                Toast.makeText(getActivity(), "Пользователь не найден", Toast.LENGTH_LONG).show();
            }
        });
    }
}
