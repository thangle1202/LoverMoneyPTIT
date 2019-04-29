package com.example.lovermoneyptit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.DealStatis;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private List<DealStatis> deals = new ArrayList<DealStatis>();
    private List<Deal> dealList = new ArrayList<Deal>();
    private WalletRepo walletRepo;
    private Spinner spinnerChart;
    private List<String> list;
    private static int groupType = 0;
    private PieChart pieChart;
    private BarChart barChart;

    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        spinnerChart = view.findViewById(R.id.spinnerChart);
        pieChart = view.findViewById(R.id.piechart);
        barChart = view.findViewById(R.id.barChart);

        walletRepo = new WalletRepo(getActivity());
        list = new ArrayList<>();
        list.add("Khoản chi");
        list.add("Khoản thu");

        // spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this.getActivity(), R.layout.spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerChart.setAdapter(adapter);

        spinnerChart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ("Khoản chi".equals(spinnerChart.getSelectedItem().toString())) {
                    Toast.makeText(getActivity(), spinnerChart.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    groupType = 3;
                    deals = walletRepo.getDealByGroup(groupType);
                    loadPieChart(new ArrayList(), deals);
                } else {
                    Toast.makeText(getActivity(), spinnerChart.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    groupType = 2;
                    deals = walletRepo.getDealByGroup(groupType);
                    loadPieChart(new ArrayList(), deals);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        try {
            dealList = walletRepo.getAllDeal();
            loadBarChart(new ArrayList(), dealList);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return view;
    }

    public void loadPieChart(ArrayList NoOfEmp, List<DealStatis> listDealStatis) {
        for (DealStatis dealStatis : listDealStatis) {
            NoOfEmp.add(new PieEntry(dealStatis.getDealValue(), dealStatis.getGroupName()));
        }
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Nhóm giao dịch");

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);
    }

    public void loadBarChart(ArrayList NoOfEmp, List<Deal> deals) {
        NoOfEmp.add(new BarEntry(945f, 0));
        NoOfEmp.add(new BarEntry(1040f, 1));
        NoOfEmp.add(new BarEntry(1133f, 2));
        NoOfEmp.add(new BarEntry(1240f, 3));
        NoOfEmp.add(new BarEntry(1369f, 4));
        NoOfEmp.add(new BarEntry(1487f, 5));
        NoOfEmp.add(new BarEntry(1501f, 6));
        NoOfEmp.add(new BarEntry(1645f, 7));
        NoOfEmp.add(new BarEntry(1578f, 8));
        NoOfEmp.add(new BarEntry(1695f, 9));

        ArrayList year = new ArrayList();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Giao dịch");
        barChart.animateY(5000);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
    }

}
