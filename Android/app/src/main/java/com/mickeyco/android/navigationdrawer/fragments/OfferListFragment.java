package com.mickeyco.android.navigationdrawer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mickeyco.android.navigationdrawer.R;
import com.mickeyco.android.navigationdrawer.database.realm.offer.OfferHelper;
import com.mickeyco.android.navigationdrawer.food_menu.realm.Offer;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

public class OfferListFragment extends Fragment {

    private static final String ARG_CATEGORY_ID = "category_id";
    private Callbacks mCallbacks;
    private RecyclerView mRecyclerView;

    public interface Callbacks{
        void onOfferSelected(int offerId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer_list, container, false);
        int categoryId = getArguments().getInt(ARG_CATEGORY_ID);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_offer_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(new OfferAdapter(OfferHelper.getByCategory(Realm.getDefaultInstance(), categoryId)));
        return view;
    }

    public static OfferListFragment newInstance(int categoryId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CATEGORY_ID, categoryId);

        OfferListFragment fragment = new OfferListFragment();
        fragment.setArguments(args);
        return fragment;
    }



    private class OfferHolder extends RecyclerView.ViewHolder{

        private ImageButton mImageButton;
        private TextView mOfferName;
        private TextView mOfferWeight;
        private TextView mOfferPrice;
        private Offer mOffer;

        public OfferHolder(View itemView) {
            super(itemView);
            mImageButton = (ImageButton) itemView.findViewById(R.id.list_item_offer_button);
            mImageButton.setClickable(true);
            mOfferName = (TextView) itemView.findViewById(R.id.list_item_offer_name);
            mOfferWeight = (TextView) itemView.findViewById(R.id.list_item_offer_weight);
            mOfferPrice = (TextView) itemView.findViewById(R.id.list_item_offer_price);
        }

        public void bindOffer(Offer Offer){
            mOffer = Offer;
            Picasso.with(getContext()).load(mOffer.getPictureUrl()).into(mImageButton);
            mOfferName.setText(mOffer.getName());
            mOfferWeight.setText("Вес: " + mOffer.getWeight());
            mOfferPrice.setText("Цена: " + mOffer.getPrice() + " р.");
        }
    }

    private class OfferAdapter extends RecyclerView.Adapter<OfferHolder>{

        private List<Offer> mOffers;

        public final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = mRecyclerView.getChildAdapterPosition(view);
                Offer offer = mOffers.get(itemPosition);
                mCallbacks.onOfferSelected(offer.getOfferId());
            }
        };

        public OfferAdapter(List<Offer> Offers){
            mOffers = Offers;
        }

        @Override
        public OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.offer_list_item, parent, false);
            view.setOnClickListener(mOnClickListener);
            return new OfferHolder(view);
        }

        @Override
        public void onBindViewHolder(OfferHolder holder, int position) {
            Offer Offer = mOffers.get(position);
            holder.bindOffer(Offer);
        }

        @Override
        public int getItemCount() {
            return mOffers.size();
        }
    }




}
