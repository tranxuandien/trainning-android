package com.example.gl.study1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ListCityAdapter extends ArrayAdapter<String> {

    public ListCityAdapter(Context context, int resource,List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if (v==null)
        {
            LayoutInflater vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.list_city_layout,null);
        }
        String p = getItem(position);
        if (p != null) {
            TextView city=(TextView)v.findViewById(R.id.txtv_city);
            if (city!=null)
            {
                city.setText(p+"");
            }
        }
        return v;
    }
}
