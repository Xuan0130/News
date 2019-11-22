package com.example.news.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.news.News;
import com.example.news.NewsAdapter;
import com.example.news.NewsData;
import com.example.news.R;
import com.google.gson.Gson;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerview;
    String url = "https://stocknewsapi.com/api/v1/category?section=general&items=50&token=zpbyjki48l39tqnxksgiffygnoxwsqhihzyuvjjk";
    private NewsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerview = root.findViewById(R.id.recyclerview);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
         adapter = new NewsAdapter(getActivity());
        recyclerview.setAdapter(adapter);
        query();
    }
    private void query(){
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            } };
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("news",response);
                Gson gson = new Gson();
                NewsData newsData = gson.fromJson(response, NewsData.class);
                List<News> news = newsData.getData();
                adapter.setList(news);

            } };

        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}