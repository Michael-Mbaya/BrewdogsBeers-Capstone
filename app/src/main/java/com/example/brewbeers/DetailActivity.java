package com.example.brewbeers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.adapters.CharPagerAdapter;
import com.example.brewbeers.models.BeersModel;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private com.example.brewbeers.adapters.CharPagerAdapter adapterViewPager;
    List<BeersModel> mDocList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        ButterKnife.bind(this);

        mDocList = Parcels.unwrap(getIntent().getParcelableExtra("characters"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new com.example.brewbeers.adapters.CharPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mDocList);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }

}
