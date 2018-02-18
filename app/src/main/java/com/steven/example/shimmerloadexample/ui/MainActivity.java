package com.steven.example.shimmerloadexample.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.steven.example.shimmerloadexample.Config;
import com.steven.example.shimmerloadexample.R;
import com.steven.example.shimmerloadexample.adapter.RecyclerViewAdapter;
import com.steven.example.shimmerloadexample.model.Movie;
import com.steven.example.shimmerloadexample.model.SearchMovie;
import com.steven.example.shimmerloadexample.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SearchView mSearchView;
    private ShimmerFrameLayout mShimmerLayout;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<Movie> mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView = findViewById(R.id.search_view);
        mShimmerLayout = findViewById(R.id.shimmer_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        mSearchView.setIconified(false);

        mMovieList = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShimmerLayout.stopShimmerAnimation();
    }

    private void fetchData() {
        mShimmerLayout.setVisibility(View.VISIBLE);
        mShimmerLayout.startShimmerAnimation();
        String keyword = mSearchView.getQuery().toString();
        keyword = keyword.replace(" ", "+");
        RetrofitSingleton.getInstance().getRestAPI().searchTitle(keyword, 1, Config.API_KEY).enqueue(new Callback<SearchMovie>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<SearchMovie> call, @NonNull Response<SearchMovie> response) {
                if (response.isSuccessful()) {
                    mMovieList = response.body().Search;
                    mAdapter.addItems(mMovieList);
                    mAdapter.notifyDataSetChanged();
                    mShimmerLayout.stopShimmerAnimation();
                    mShimmerLayout.setVisibility(View.GONE);
                    mRecyclerView.smoothScrollToPosition(0);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchMovie> call, @NonNull Throwable t) {
                if (!call.isCanceled()) {
                    t.printStackTrace();
                }
            }
        });
    }
}
