package com.example.android.test.GOTUtils;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bdobre on 27/07/2017.
 */

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>{
    private List<Character> characters = new ArrayList<>();
    private CharacterOnClickHandler clickHandler;


    public interface CharacterOnClickHandler{
       public void onClick(Character character);
    }

    public CharactersAdapter(CharacterOnClickHandler clickHandler){
        this.clickHandler = clickHandler;
    }

    public void setData(List<Character> c){
        characters = c;
        notifyDataSetChanged();
    }

    public List<Character> getData(){
        return characters;
    }


    @Override
    public CharactersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.got_character_item,parent,false);
        return new CharactersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharactersViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        if(characters == null) return 0;
        return characters.size();
    }

    public class CharactersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;
        private TextView name;
        public CharactersViewHolder(View view){
            super(view);
            image = view.findViewById(R.id.character_image);
            name = view.findViewById(R.id.character_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(characters.get(adapterPosition));
        }

        public void bind(int position){
            Character character = characters.get(position);
            name.setText(character.getName());
            Picasso.with(name.getContext()).load(Uri.parse("https://robohash.org/"+character.getName())).into(image);

        }
    }
}
