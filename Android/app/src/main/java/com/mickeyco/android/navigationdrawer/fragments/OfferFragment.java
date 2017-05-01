package com.mickeyco.android.navigationdrawer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mickeyco.android.navigationdrawer.R;
import com.mickeyco.android.navigationdrawer.database.realm.offer.OfferHelper;
import com.mickeyco.android.navigationdrawer.food_menu.realm.Offer;
import com.squareup.picasso.Picasso;

import java.util.Map;

import io.realm.Realm;

public class OfferFragment extends Fragment {
    private static final String ARG_OFFER_ID = "offer_id";

    public static OfferFragment newInstance(int offerId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_OFFER_ID, offerId);

        OfferFragment fragment = new OfferFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer, container, false);
        int offerId = getArguments().getInt(ARG_OFFER_ID);
        Offer offer = OfferHelper.get(Realm.getDefaultInstance(), offerId);

        ImageView imageView = (ImageView) view.findViewById(R.id.offer_image_view);
        TextView nameTextView = (TextView) view.findViewById(R.id.offer_name_text_view);
        TextView priceTextView = (TextView) view.findViewById(R.id.offer_price_text_view);
        TextView weightTextView = (TextView) view.findViewById(R.id.offer_weight_text_view);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.offer_description_text_view);

        Picasso.with(getContext()).load(offer.getPictureUrl()).into(imageView);
        nameTextView.setText(offer.getName());
        priceTextView.setText(String.valueOf(offer.getPrice()) + " р");
        weightTextView.setText(String.valueOf(offer.getWeight()));
        StringBuilder descriptionBuilder = new StringBuilder();
        if(!offer.getDescription().isEmpty())
            descriptionBuilder.append(offer.getDescription()).append("\n");
        for(Map.Entry<String, String> entry : offer.getParams().entrySet()) {
            descriptionBuilder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        descriptionTextView.setText(descriptionBuilder.toString());

        return view;

    }
}
