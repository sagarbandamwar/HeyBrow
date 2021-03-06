package com.example.heybrow.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.heybrow.Fragments.ChatsFragment;
import com.example.heybrow.Fragments.FriendsFragment;
import com.example.heybrow.Fragments.RequestsFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {


    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;
            case 1:
                ChatsFragment chatsFragment = new ChatsFragment();
                return  chatsFragment;
            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return  friendsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        switch (position){
            case 0:
                return "REQUESTS";
            case 1:
                return  "CHATS";
            case 2:
                return  "FRIENDS";
            default:
                return null;
        }

    }
}
