package com.example.gl.study1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gl.study1.Model.FriendFragmentElement;
import com.example.gl.study1.Model.Friends;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class FriendsFragment extends Fragment {
    private FriendAdapter fa;
    public static final String TAG = FriendsFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        List<Friends> friends = new ArrayList<>();
        List<FriendFragmentElement> friendFragmentElements = new ArrayList<>();
        final List<Friends> friends = new ArrayList<>();
        final View view = inflater.inflate(R.layout.friend_list, container, false);
        final EditText editSearch = (EditText) view.findViewById(R.id.edt_search);
        final TextView txtv_myName = (TextView) view.findViewById(R.id.myName);
        ImageView img_Avatar = (ImageView) view.findViewById(R.id.imgAvata);
        img_Avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.popup_profile, null);
                final PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

                ImageView imgv_Close = (ImageView) layout.findViewById(R.id.btn_cancel);
                imgv_Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<Friends> friendAdd = new ArrayList<Friends>();

                List<Friends> friendses = new ArrayList<Friends>();

                friendses.add(new Friends("Fr1", 12));
                friendses.add(new Friends("end 1", 12));
                friendses.add(new Friends("Frie ", 12));
                friendses.add(new Friends("iend 1", 12));
                friendses.add(new Friends("nd ", 12));
                friendses.add(new Friends("d ", 12));
                friendses.add(new Friends("F1", 12));
                friendses.add(new Friends("nd ", 12));
                friendses.add(new Friends("Fre 1", 12));
                friendses.add(new Friends("ind 1", 12));
                friendses.add(new Friends("d 1", 12));
                friendses.add(new Friends("1", 12));


                for (Friends friend : friendses) {
                    if (friend.getName().toLowerCase().contains(editable.toString().toLowerCase())) {
                        friendAdd.add(friend);
                    }
                }
                final FragmentManager fragment = getFragmentManager();
                final FragmentTransaction transaction = fragment.beginTransaction();

                if (TextUtils.isEmpty(editSearch.getText().toString().trim())) {
                    if (fragment.findFragmentByTag(SearchFriendListFragment.TAG) != null) {
                        transaction.remove(fragment.findFragmentByTag(SearchFriendListFragment.TAG)).commit();
                    }
                } else {
                    if (fragment.findFragmentByTag(SearchFriendListFragment.TAG) == null) {
                        SearchFriendListFragment searchFriendFrag = new SearchFriendListFragment();
                        searchFriendFrag.updateData(friendAdd);
                        transaction.add(R.id.layoutSearch, searchFriendFrag, SearchFriendListFragment.TAG).commit();
                    } else {
                        ((SearchFriendListFragment) fragment.findFragmentByTag(SearchFriendListFragment.TAG)).updateData(friendAdd);
                    }
                }
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.friend_list);
        //view count number friends of user
        TextView friendCountText = (TextView) view.findViewById(R.id.friend_number);

        friendFragmentElements.add(new FriendFragmentElement("Recommends", true));
        friendFragmentElements.add(new FriendFragmentElement("Favorite", true));
        friendFragmentElements.add(new FriendFragmentElement("Friends", true));

        fa = new FriendAdapter(view.getContext(), friendFragmentElements);
        recyclerView.setAdapter(fa);
        fa.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        friendCountText.setText(fa.getItemCount() + " friends");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        fa.setOnRowClick(new OnRowClickListener() {
            @Override
            public void onClick(Friends friends, int position) {
//                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View layout = inflater.inflate(R.layout.popup_profile, null);
//                final PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
//
//                //set data
//                TextView txtvUserName = (TextView) layout.findViewById(R.id.user_name);
//                txtvUserName.setText("Text User Name");
            }
        });
    }

    public interface OnRowClickListener {
        void onClick(Friends friends, int position);
    }
}
