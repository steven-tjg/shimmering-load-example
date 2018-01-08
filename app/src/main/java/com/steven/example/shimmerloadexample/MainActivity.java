package com.steven.example.shimmerloadexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ShimmerRecyclerView mShimmeringRV;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShimmeringRV = findViewById(R.id.recycler_view);
    }
}
