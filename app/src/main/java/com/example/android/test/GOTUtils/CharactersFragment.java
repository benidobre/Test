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
    boolean hasMore = true;

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

        int page = 1;

            Call<List<Character>> charactersCall = GOTService.Service.Get().getCharacters(page, 100);
            charactersCall.enqueue(new Callback<List<Character>>() {
                @Override
                public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                    if (response.isSuccessful()) {
                        List<Character> list = response.body();
                        List<Character> listCharacters = new ArrayList<Character>();
                        for(int i=0;i<charactersIds.length;++i){
                            for(Character c:list){
                                String id = c.getUrl().substring("https://www.anapioficeandfire.com/api/characters/".length());
                                if(Integer.parseInt(id) == charactersIds[i]){
                                    listCharacters.add(c);
                                    break;
                                }
                            }
                        }
                        charactersAdapter.setData(listCharacters);

                    }else{
                        hasMore = false;
                    }
                }

                @Override
                public void onFailure(Call<List<Character>> call, Throwable t) {
                    hasMore = false;
                }
            });







        return rootView;
    }
}
