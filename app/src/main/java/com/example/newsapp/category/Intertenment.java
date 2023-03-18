package com.example.newsapp.category;

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

import com.example.newsapp.adapter.Adapter;
import com.example.newsapp.apiutilities.ApiUtilities;
import com.example.newsapp.MainNews;
import com.example.newsapp.model.Model;
import com.example.newsapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Intertenment extends Fragment {

    String api = "9545aa553d3e4c57a8198a737ecd6ab9";
    private RecyclerView recyclerViewOfIntertainment;
    ArrayList<Model> modelArrayList;
    Adapter adapter;
    private ProgressBar progressBar;
    String country = "in";
    private String category = "entertainment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intertenmet_fragments, null);

        recyclerViewOfIntertainment = view.findViewById(R.id.recyclerViewOfIntertenments);
        progressBar = view.findViewById(R.id.progress_horizontal2);
        modelArrayList = new ArrayList<>();
        recyclerViewOfIntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelArrayList);
        recyclerViewOfIntertainment.setAdapter(adapter);

        findNews();
        progressBar.setVisibility(View.VISIBLE);


        return view;
    }

    public void findNews(){
        ApiUtilities.getApiInterface().getCategoryNews(country,category, 100, api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful()){
                    modelArrayList.addAll(response.body().getArticles());
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
    }
}
