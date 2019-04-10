package com.example.lovermoneyptit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddDealFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddDealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDealFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Views
    private TextView txtDealCreatedDate, txtDesc, txtDealValue, txtGroup;

    private TextView txtSelectWallet;

    private OnFragmentInteractionListener mListener;

    private LinearLayout selectWalletLayout, datePickerlayout, selectGrouplayout;

    public AddDealFragment() {
        // Required empty public constructor
    }

    public static AddDealFragment newInstance(String param1, String param2) {
        AddDealFragment fragment = new AddDealFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            txtSelectWallet.setText(this.getArguments().getString("walletName"));
            Toast.makeText(getActivity(), "wallet selected: " + this.getArguments().getString("walletName"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_deal, container, false);

        txtSelectWallet = view.findViewById(R.id.txtSelectWallet);
        txtDealCreatedDate = view.findViewById(R.id.txtDealCreatedDate);
        txtGroup = view.findViewById(R.id.txtGroup);
        txtDesc = view.findViewById(R.id.txtDesc);
        txtDealValue = view.findViewById(R.id.txtDealValue);

        selectWalletLayout = view.findViewById(R.id.selectWalletLayout);
        selectWalletLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                SelectWalletFragment selectWalletFragment = SelectWalletFragment.newInstance();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, selectWalletFragment, null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        datePickerlayout = view.findViewById(R.id.datePickerLayout);
        datePickerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerActivity();
                newFragment.show(getFragmentManager(), "date picker");
            }
        });

        selectGrouplayout = view.findViewById(R.id.selectGroupLayout);
        selectGrouplayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new SelectGroupFragment(), null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

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

    @Override
    public void onResume() {
        super.onResume();
        // get wallet selected from selectWalletFragment
        SelectWalletFragment selectWalletFragment = SelectWalletFragment.newInstance();
        Bundle selectWalletBundle = selectWalletFragment.getArguments();
        if(selectWalletBundle != null){
            txtSelectWallet.setText(selectWalletBundle.getString("walletName"));
        }

        borrowLoanFragment loanFragment = borrowLoanFragment.newInstance();
        Bundle selectGroupLoanBundle = loanFragment.getArguments();
        if(selectGroupLoanBundle != null){
            txtGroup.setText(selectGroupLoanBundle.getString("groupName"));
        }

    }
}
