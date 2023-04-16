package com.example.hollowcalculator.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.hollowcalculator.R;
import com.example.hollowcalculator.objects.HelpItem;

import java.util.ArrayList;
public class HelpAdapter extends ArrayAdapter<HelpItem> {

    public HelpAdapter(Context context, ArrayList<HelpItem> helpItems) {
        super(context, 0, helpItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HelpItem helpItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_help_items, parent, false);
        }
        // Lookup view for data population
        TextView tvKeyword = (TextView) convertView.findViewById(R.id.tvKeyword);
        TextView tvSyntax = (TextView) convertView.findViewById(R.id.tvSyntax);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);

        // Populate the data into the template view using the data object
        tvKeyword.setText(helpItem.getKeyword());
        tvSyntax.setText(helpItem.getSyntax());
        tvDesc.setText(helpItem.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }
}

