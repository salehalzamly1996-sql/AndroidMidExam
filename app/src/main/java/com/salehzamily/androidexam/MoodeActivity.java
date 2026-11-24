


package com.salehzamily.androidexam;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MoodeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        setupToolbar();
        setupTabs();

        Button setModeButton = findViewById(R.id.buttonSetMode);
        setModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showModeMenu(view);
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mode");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupTabs() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ModePagerAdapter adapter = new ModePagerAdapter(this);
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("First");
                    } else if (position == 1) {
                        tab.setText("Second");
                    } else {
                        tab.setText("Third");
                    }
                });

        tabLayoutMediator.attach();
    }

    private void showModeMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(MoodeActivity.this, view);
        popupMenu.getMenu().add("Light");
        popupMenu.getMenu().add("Night");
        popupMenu.getMenu().add("System");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String selectedMode = item.getTitle().toString();

                if (selectedMode.equals("Light")) {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO);
                } else if (selectedMode.equals("Night")) {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }

                return true;
            }
        });

        popupMenu.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1 || viewPager.getCurrentItem() == 2) {
            viewPager.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }
}

