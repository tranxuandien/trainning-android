package com.example.gl.study1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.gl.study1.Model.FriendFragmentElement;
import com.example.gl.study1.Model.Friends;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {
    private Context context;
    private List<Friends> Listfriends=new ArrayList<>();

    public FriendsListAdapter(Context context, List<FriendFragmentElement> listfriends) {
        this.context = context;
        Listfriends.add(new Friends("Frie",12));
        Listfriends.add(new Friends("Frind 1",12));
        Listfriends.add(new Friends("Friend ",12));
        Listfriends.add(new Friends("Frnd 1",12));
        Listfriends.add(new Friends("nd 1",12));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Friends friend=Listfriends.get(position);
        holder.sub_list_item.setText(friend.getName());
        holder.sub_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_user_profile, null);
                final PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

                //set data for popup
                TextView txtv_userName=(TextView) layout.findViewById(R.id.userName);
                txtv_userName.setText(friend.getName());

                ImageButton btn_cancel=(ImageButton) layout.findViewById(R.id.btnCancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.Listfriends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sub_list_item;
        public ViewHolder(View itemView) {
            super(itemView);
            sub_list_item=(TextView) itemView.findViewById(R.id.sub_list_item);
        }
    }
}
git add