package com.salehzamily.androidexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ModePagerAdapter extends FragmentStateAdapter {

    public ModePagerAdapter(@NonNull AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new FirstFragment();
        } else if (position == 1) {
            return new SecondFragment();
        } else {
            return new ThirdFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
