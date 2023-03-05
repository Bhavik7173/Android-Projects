package com.example.sample.Frags;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.Adapter;
import com.example.sample.AlumniNames;
import com.example.sample.Api;
import com.example.sample.MyRetrofit;
import com.example.sample.R;
import com.example.sample.RecyclerViewClickInterface;
import com.example.sample.SpacingItemDecorator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment implements RecyclerViewClickInterface {

    Context context;
    CardView Jobs,Events;
    public Adapter adapter;

    public List<AlumniNames> alumniNames;
    private Api apiInterface;
    String[] item;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    public RecyclerView recyclerView;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_search,null);

        recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpacingItemDecorator(20));
        return view;
    }
    public FragmentSearch(Context context)
    {
        this.context=context;
    }
    public void fetchContact(String key) {
        Api apiInterface2 =  MyRetrofit.getRetrofit(getString(R.string.IP)).create(Api.class);
        this.apiInterface = apiInterface2;
        apiInterface2.getContact(key).enqueue(new Callback<List<AlumniNames>>() {
            public void onResponse(Call<List<AlumniNames>> call, Response<List<AlumniNames>> response) {

                alumniNames = response.body();
                adapter=new Adapter(alumniNames,context,recyclerViewClickInterface);
                recyclerView.setAdapter((RecyclerView.Adapter) adapter);
                adapter.notifyDataSetChanged();
            }

            public void onFailure(Call<List<AlumniNames>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error",t.toString());
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        recyclerViewClickInterface=(RecyclerViewClickInterface)context;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchContact(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onItemClick(int position) {

    }
}
