package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapp.category.Healths;
import com.example.newsapp.category.Home;
import com.example.newsapp.category.Intertenment;
import com.example.newsapp.category.Science;
import com.example.newsapp.category.Sports;
import com.example.newsapp.category.Technology;

public class PageAdapter extends FragmentPagerAdapter {
    int tabCount;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home();

            case 1:
                return new Sports();

            case 2:
                return new Healths();

            case 3:
                return new Science();

            case 4:
                return new Intertenment();

            case 5:
                return new Technology();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
