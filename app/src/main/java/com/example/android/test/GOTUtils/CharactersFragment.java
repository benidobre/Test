package com.example.android.test.GOTUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.test.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bdobre on 27/07/2017.
 */

public class CharactersFragment extends Fragment {
    private RecyclerView recyclerView;
    private CharactersAdapter charactersAdapter;
    boolean hasMore = false;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.got_characters, container, false);

        Bundle bundle = getArguments();
        final int[] charactersIds = bundle.getIntArray("characters");

        recyclerView = rootView.findViewById(R.id.recyclerview_got_characters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);

        charactersAdapter = new CharactersAdapter((CharactersAdapter.CharacterOnClickHandler) getActivity());
        recyclerView.setAdapter(charactersAdapter);


        fetch(charactersIds,0,10);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                fetch(charactersIds,(page)*10,10);
            }
        };









        return rootView;
    }

    public void fetch(final int[] ids, final int start, final int nrCharacters){
        if(nrCharacters == 0 || start >= ids.length) {
            if(!hasMore) {
                recyclerView.addOnScrollListener(scrollListener);
                hasMore = true;
            }
            return;}
        Call<Character> characterCall = GOTService.Service.Get().getCharacter(ids[start]);
        characterCall.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if(response.isSuccessful()){
                    List<Character> list = charactersAdapter.getData();
                    Character c = response.body();
                    list.add(c);
                    charactersAdapter.setData(list);
                    fetch(ids,start +1, nrCharacters - 1);
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {

            }
        });

    }


}
