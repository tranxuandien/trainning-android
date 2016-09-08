package com.example.gl.study1;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.gl.study1.Model.FriendFragmentElement;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private List<FriendFragmentElement> friendFragmentElements;
    private Context context;
    private FriendsFragment.OnRowClickListener onClick;

    public FriendAdapter(Context context, List<FriendFragmentElement> friendFragmentElements) {
        this.context = context;
        this.friendFragmentElements = friendFragmentElements;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtv1, txtv2;
        public RecyclerView recyclerView;
        public LinearLayout expand;

        public ViewHolder(View itemView) {
            super(itemView);
            txtv1 = (TextView) itemView.findViewById(R.id.friend_name);
            txtv2 = (TextView) itemView.findViewById(R.id.friend_age);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.sub_list);
            expand = (LinearLayout) itemView.findViewById(R.id.imgExpandable);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View FriendView = inflater.inflate(R.layout.item_friends, parent, false);
        ViewHolder viewHolder = new ViewHolder(FriendView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FriendFragmentElement element = friendFragmentElements.get(position);
        TextView textViewName = holder.txtv1;
        TextView textViewAge = holder.txtv2;
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setHasFixedSize(true);
        // case Friend List or Favorite Friend List
        if (element.getName().equals("Friends")) {
            FriendsListAdapter friendsListAdapter = new FriendsListAdapter(context, friendFragmentElements);
            holder.recyclerView.setAdapter(friendsListAdapter);
            friendsListAdapter.notifyDataSetChanged();
        } else {
        }
        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (element.isExpandable()) {
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    holder.expand.setActivated(true);
                    element.setExpandable(false);
                } else {
                    holder.recyclerView.setVisibility(View.GONE);
                    holder.expand.setActivated(false);
                    element.setExpandable(true);
                }
            }
        });
        if (element.isExpandable()) {
            holder.recyclerView.setVisibility(View.GONE);
            holder.expand.setActivated(false);
            element.setExpandable(true);
        } else {
            holder.recyclerView.setVisibility(View.VISIBLE);
            holder.expand.setActivated(true);
            element.setExpandable(false);
        }
        holder.txtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                holder.txtv1.setText(element.getName() + "(" + friendFragmentElements.size() + ")");
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View layout = inflater.inflate(R.layout.popup_profile, null);
//                final PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
            }
        });
        if (element.getName().equals("Friends")) {
            textViewName.setText(element.getName() + "(" + friendFragmentElements.size() + ")");
        } else {
            textViewName.setText(element.getName());
        }
//        textViewAge.setText(friends.getAge() + "(age)");

    }

    @Override
    public int getItemCount() {
        return this.friendFragmentElements.size();
    }

    public void setOnRowClick(FriendsFragment.OnRowClickListener clickRow) {
        this.onClick = clickRow;
    }
}
