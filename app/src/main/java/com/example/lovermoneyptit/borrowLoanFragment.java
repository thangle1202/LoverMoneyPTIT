package com.example.lovermoneyptit;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.SelectGroupLoanAdapter;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.example.lovermoneyptit.utils.GroupType;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link borrowLoanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link borrowLoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class borrowLoanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Group> groups;
    private SelectGroupLoanAdapter selectGroupLoanAdapter;
    private RecyclerView rcvGroupLoan;
    private ImageButton btnAddgroup;
    private WalletRepo walletRepo;

    private static Group thisItem = new Group();

    // onItemClickListener
    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = groups.get(pos);

            Intent intent = new Intent(getActivity(), AddDealActivity.class);
            intent.putExtra("groupName", thisItem.getGroupName());
            startActivity(intent);
        }
    };

    private OnFragmentInteractionListener mListener;

    public borrowLoanFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static borrowLoanFragment newInstance() {
        borrowLoanFragment fragment = new borrowLoanFragment();
        Bundle args = new Bundle();
        args.putString("groupName", thisItem.getGroupName());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_borrow_loan, container, false);
        walletRepo = new WalletRepo(getActivity());
        //bind view
        rcvGroupLoan = view.findViewById(R.id.rcvGroupLoan);
        btnAddgroup = view.findViewById(R.id.btnAddGroup);

        btnAddgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddGroupActivity.class);
                startActivity(intent);
            }
        });

        // get data by LOAN group
        groups = walletRepo.getGroupByType(GroupType.LOAN);
        Toast.makeText(getActivity(), "size:" + groups.size(), Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        if(groups.size() >= 1){
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            rcvGroupLoan.setLayoutManager(layoutManager);
            selectGroupLoanAdapter = new SelectGroupLoanAdapter(groups, this.getContext());
            selectGroupLoanAdapter.setmOnClickListener(mOnItemClickListener);
            rcvGroupLoan.setAdapter(selectGroupLoanAdapter);
        }
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
