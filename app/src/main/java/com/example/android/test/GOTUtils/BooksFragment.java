package com.example.android.test.GOTUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.test.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bdobre on 27/07/2017.
 */

public class BooksFragment extends Fragment {

    private RecyclerView recyclerView;
    private BooksAdapter booksAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.got_books, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview_got_books);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);

        booksAdapter = new BooksAdapter((BooksAdapter.BookAdapterOnClickHandler) getActivity());
        recyclerView.setAdapter(booksAdapter);


        Call<List<Book>> bookCall = GOTService.Service.Get().getBooks(12);
        bookCall.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    List<Book> list = response.body();
                    booksAdapter.setData(list);
                    int j =0 ;
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                int j =0 ;
            }
        });
        return rootView;
    }
}
