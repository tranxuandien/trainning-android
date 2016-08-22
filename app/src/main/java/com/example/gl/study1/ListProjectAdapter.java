package com.example.gl.study1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by truongdat277 on 8/22/2016.
 */
public class ListProjectAdapter extends ArrayAdapter<Project> {
    public ListProjectAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ListProjectAdapter(Context context, int resource, List<Project> projects) {
        super(context, resource, projects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if (v==null)
        {
            LayoutInflater vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.list_project_layout,null);
        }
        Project p = getItem(position);
        if (p != null) {
            TextView col1=(TextView)v.findViewById(R.id.tv_id);
            TextView col2=(TextView)v.findViewById(R.id.tv_name);
            TextView col3=(TextView)v.findViewById(R.id.tv_progress);
            if (col1!=null)
            {
                col1.setText(p.getId());
            }
            if (col2!=null)
            {
                col2.setText(p.getName());
            }
            if (col3!=null)
            {
                col3.setText(p.getProgress()+"");
            }
        }
        return v;
    }
}
