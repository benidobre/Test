package com.example.android.test.GOTUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.test.R;

import java.util.List;

/**
 * Created by bdobre on 27/07/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>{
    private List<Book> books;
    private final BookAdapterOnClickHandler clickHandler;

    public BooksAdapter(BookAdapterOnClickHandler clickHandler){
        this.clickHandler = clickHandler;
    }

    @Override
    public BooksAdapter.BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_list_item,parent,false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksAdapter.BooksViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(books == null) return 0;
        return books.size();
    }

    public void setData(List<Book> list){
        books = list;
        notifyDataSetChanged();
    }

    public interface BookAdapterOnClickHandler{
        void onClick(Book book);
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView bookTextView;
        public BooksViewHolder(View view){
            super(view);
            bookTextView = view.findViewById(R.id.tv_bike);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(books.get(adapterPosition));

        }
        public void bind(int position){
            Book book = books.get(position);
            bookTextView.setText(book.getName());
        }
    }
}
