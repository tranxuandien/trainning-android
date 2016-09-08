package com.example.gl.study1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gl.study1.Model.Friends;

import java.util.List;

public class ListResultAdapter extends RecyclerView.Adapter<ListResultAdapter.ResultViewHolder> {
    private Context context;
    private List<Friends> listFriend;

    public ListResultAdapter(Context context, List<Friends> listFriend) {
        this.context = context;
        this.listFriend = listFriend;
    }
    public void setData(List<Friends>  data){
        this.listFriend.clear();
        this.listFriend.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        return new ResultViewHolder(inflater.inflate(R.layout.list_friend_result_tab,parent,false));
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        Friends f=listFriend.get(position);
        holder.name.setText(f.getName());
    }

    @Override
    public int getItemCount() {
        return this.listFriend.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ResultViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.txtv_itemFriend);
        }
    }
}
