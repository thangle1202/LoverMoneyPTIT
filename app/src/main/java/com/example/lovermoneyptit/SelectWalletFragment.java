package com.example.lovermoneyptit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.SelectWalletAdapter;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectWalletFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectWalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectWalletFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters
    private RecyclerView rcvSelectWallet;
    private SelectWalletAdapter selectWalletAdapter;
    private List<Wallet> wallets;
    private OnFragmentInteractionListener mListener;
    private WalletRepo walletRepo;
    private String data = "";
    static Wallet thisItem = new Wallet();

    //  click to 1 wallet
    private View.OnClickListener mOnItemClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = wallets.get(pos);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, new AddDealFragment(), null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    };

    public SelectWalletFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SelectWalletFragment newInstance() {
        SelectWalletFragment fragment = new SelectWalletFragment();
        // get data from item clicked
        Bundle args = new Bundle();
        args.putString("walletName", thisItem.getWalletName());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString("walletName");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_wallet, container, false);
        rcvSelectWallet = view.findViewById(R.id.rcvSelectWallet);
        walletRepo = new WalletRepo(getActivity());

        // get all wallet from table wallet
        wallets = walletRepo.getAllWallets();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rcvSelectWallet.setLayoutManager(layoutManager);
        selectWalletAdapter = new SelectWalletAdapter(wallets, this.getContext());
        selectWalletAdapter.setmOnItemClickListener(mOnItemClickListener);
        rcvSelectWallet.setAdapter(selectWalletAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
