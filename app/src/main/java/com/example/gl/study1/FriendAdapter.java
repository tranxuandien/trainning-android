package com.example.gl.study1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gl.study1.Model.FriendFragmentElement;
import com.example.gl.study1.Model.Friends;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private List<FriendFragmentElement> friendFragmentElements;
    private List<Friends> friendArray = new ArrayList<>();
    private List<Friends> FavoriteFriendArray = new ArrayList<>();
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
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = ProjectParams.getFriendUrl;
//            Get access token from login
            SharedPreferences sharedpreferences = context.getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            final String access_token = "Bearer " + sharedpreferences.getString("access_token", null);
//            Create request to get all friend of user
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    JSONArray jsonArray = response.optJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject jsonObjecFriends = jsonArray.getJSONObject(i);
                            JSONObject friend = jsonObjecFriends.getJSONObject("profile").optJSONObject("data");
                            Integer friendId= friend.getInt("id");
                            String ten = friend.getString("display_name");
                            Integer id = friend.getInt("user_id");
                            // add this friend to DB
                            Realm realm=Realm.getInstance(new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded().build());
//                          realm.delete(Friends.class);
                            Friends travelFriend= realm.where(Friends.class).equalTo("friendId",friendId).findFirst();
                            if (travelFriend==null)
                            {
//                              add new friend to DB
                                realm.beginTransaction();

                                Friends addFriend=realm.createObject(Friends.class);
                                addFriend.setName(ten);
                                addFriend.setFriendId(friendId);
                                addFriend.setAge(id);
//                                if (friend.getString())
//                                addFriend.setFavorite(0);
//                                else
//                                addFriend.setFavorite(1);
                                realm.commitTransaction();
                            }
//                            if this friend is favorite friend type add to FavoriteFriendArray alse add friendArray
//                            if(friend.)
//                            {
//
//                            }
//                            else
//                            {
                                friendArray.add(new Friends(ten, id));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<>();
                    header.put("Authorization", access_token);
                    return header;
                }
            };
            queue.add(jsonObjectRequest);

            FriendsListAdapter friendsListAdapter = new FriendsListAdapter(context, friendArray);
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
            Realm realm=Realm.getInstance(new RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded().build());
            List<Friends> friendArray= realm.where(Friends.class).findAll();
            textViewName.setText(element.getName() + "(" + friendArray.size() + ")");
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
    public int countFriend(){
        return this.friendArray.size();
    }
}
