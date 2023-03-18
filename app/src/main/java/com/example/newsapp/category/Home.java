package com.example.newsapp.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.MainNews;
import com.example.newsapp.R;
import com.example.newsapp.adapter.Adapter;
import com.example.newsapp.apiutilities.ApiUtilities;
import com.example.newsapp.model.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {
    String api = "9545aa553d3e4c57a8198a737ecd6ab9";
    private RecyclerView recyclerViewOfHome;
    ArrayList<Model> modelArrayList;
    Adapter adapter;
    private ProgressBar progressBar;
    String country = "in";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        recyclerViewOfHome = view.findViewById(R.id.recyclerViewOfHome);
        progressBar = view.findViewById(R.id.progress_horizontal);
        modelArrayList = new ArrayList<>();
        recyclerViewOfHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelArrayList);
        recyclerViewOfHome.setAdapter(adapter);
        findNews();
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

    public void findNews(){
        ApiUtilities.getApiInterface().getNews(country,100,api).enqueue(new Callback<MainNews>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful()){
                    modelArrayList.addAll(response.body().getArticles());
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {}
        });
    }
}
