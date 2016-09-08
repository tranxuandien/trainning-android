package com.example.gl.study1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class Frag extends AppCompatActivity{
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag);
        viewPager=(ViewPager)findViewById(R.id.viewPage);
            if(viewPager!=null)
            {
            setViewPage(viewPager);
        }
        tabLayout=(TabLayout)findViewById(R.id.tableLayout);
        tabLayout.setupWithViewPager(viewPager);
        setTextTab(tabLayout);
    }

    private void setTextTab(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setText("Friends").setIcon(R.drawable.ic_tab_friendslist_change);
        tabLayout.getTabAt(1).setText("Logs").setIcon(R.drawable.ic_tab_chat_change);
        tabLayout.getTabAt(2).setText("Contacts").setIcon(R.drawable.ic_tab_timeline_change);
        tabLayout.getTabAt(3).setText("").setIcon(R.drawable.ic_tab_profile_change);
        tabLayout.getTabAt(4).setText("Search").setIcon(R.drawable.ic_tab_profile_change);
    }

    private void setViewPage(ViewPager viewPager) {
        ViewPageHomeAdapter homeAdapter= new ViewPageHomeAdapter(getSupportFragmentManager());
        homeAdapter.addFragment(new FriendsFragment());
        homeAdapter.addFragment(new LogFragment());
        homeAdapter.addFragment(new ContactFragment());
        homeAdapter.addFragment(new ProfileFragment());
        homeAdapter.addFragment(new SearchFriendListFragment());
        viewPager.setAdapter(homeAdapter);
    }


}
