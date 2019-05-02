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
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
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

    public void loadBarChart(ArrayList NoOfEmp, List<Deal> deals){
        int i = 0;
        for(Deal d : deals){
            NoOfEmp.add(new BarEntry(i, d.getValue()));
            i++;
        }
        List<String> labels = new ArrayList<String>();
        for(Deal d : deals){
            labels.add(d.getCreatedDate());
        }
        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Giao dịch");
        barChart.animateY(5000);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        Description desc = new Description();
        desc.setText("Giao dịch");
        barChart.setDescription(desc);
        barChart.setFitBars(true);
        barChart.setData(data);
    }

}
