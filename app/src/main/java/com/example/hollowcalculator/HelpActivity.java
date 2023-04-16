package com.example.hollowcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.hollowcalculator.adapters.HelpAdapter;
import com.example.hollowcalculator.objects.HelpItem;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

    ListView helpList = (ListView) findViewById(R.id.helpListView);
    ArrayList<HelpItem> helpItemList = new ArrayList<HelpItem>();
        helpItemList.add(new HelpItem("sin","sin(x)","Trigonometric sine – Unary function"));
        helpItemList.add(new HelpItem("cos","cos(x)","Trigonometric cosine – Unary function"));
        helpItemList.add(new HelpItem("tan","tan(x)","Trigonometric tangent – Unary function"));
        helpItemList.add(new HelpItem("ln","ln(x)","Natural logarithm (base e) – Unary function"));
        helpItemList.add(new HelpItem("log","lg(x)","Common logarithm (base 10) – Unary function"));
        helpItemList.add(new HelpItem("^","a^b","Exponentiation – Operator"));
        helpItemList.add(new HelpItem("!","n!","Factorial – Operator"));
        helpItemList.add(new HelpItem("√","√x","Square root – Operator – Unicode math symbol"));
        helpItemList.add(new HelpItem("π","π","Pi, Archimedes’ or Ludolph’s number – Mathematical constant π – Constant value – Unicode math symbol"));
        helpItemList.add(new HelpItem("%","n%","Percentage – Operator"));

        HelpAdapter ha = new HelpAdapter(this,helpItemList);
    helpList.setAdapter(ha);


    }
}
