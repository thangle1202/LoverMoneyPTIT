package com.example.lovermoneyptit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lovermoneyptit.adapter.SelectGroupCashInAdapter;
import com.example.lovermoneyptit.api.APIUtils;
import com.example.lovermoneyptit.api.MoneyService;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.example.lovermoneyptit.utils.GroupType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CashInFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CashInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CashInFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private WalletRepo walletRepo;
    private SelectGroupCashInAdapter selectGroupCashInAdapter;
    private RecyclerView rcvGroupCashIn;
    private FloatingActionButton btnAddgroup;
    private List<Group> groups;

    private MoneyService moneyService;

    private static Group thisItem = new Group();

    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int pos = viewHolder.getAdapterPosition();
            thisItem = groups.get(pos);

            Intent intent = new Intent(getActivity(), AddDealActivity.class);
            intent.putExtra("groupName", thisItem.getGroupName());
            // put object to addDealActivity
            intent.putExtra("group", thisItem);
            getActivity().setResult(AddDealActivity.REQUEST_CODE_SELECT_GROUP, intent);
            getActivity().finish();
        }
    };

    private OnFragmentInteractionListener mListener;

    public CashInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CashInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CashInFragment newInstance(String param1, String param2) {
        CashInFragment fragment = new CashInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cash_in, container, false);

        // bind view
        rcvGroupCashIn = view.findViewById(R.id.rcvGroupCashIn);
        btnAddgroup = view.findViewById(R.id.btnAddGroup);
        btnAddgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddGroupActivity.class);
                startActivity(intent);
            }
        });

        walletRepo = new WalletRepo(getActivity());
        groups = walletRepo.getGroupByType(GroupType.CASH_IN);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rcvGroupCashIn.setLayoutManager(layoutManager);
        selectGroupCashInAdapter = new SelectGroupCashInAdapter(groups, this.getContext());
        selectGroupCashInAdapter.setmOnClickListener(mOnItemClickListener);
        rcvGroupCashIn.setAdapter(selectGroupCashInAdapter);

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
