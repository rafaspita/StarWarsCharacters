package com.rspitaliere.starwarscharacters.adapters.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rspitaliere.starwarscharacters.R;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;
import com.rspitaliere.starwarscharacters.utils.FontUtils;
import com.squareup.picasso.Cache;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by rspitaliere on 20/01/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieDTO> adapterList;
    private LayoutInflater layoutInflater;
    public MovieAdapterClickListener movieAdapterListener;
    private Context context;

    public MovieAdapter(Context context, List<MovieDTO> l, MovieAdapterClickListener listener){
        adapterList = l;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        movieAdapterListener = listener;



    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.line_recyclerview_movies, parent, false);
        MovieViewHolder quotationViewHolder = new MovieViewHolder(view);
        return quotationViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.title.setText(adapterList.get(position).getTitle().toLowerCase());
        holder.director.setText(adapterList.get(position).getDirector().toLowerCase());
        holder.producer.setText(adapterList.get(position).getProducer().toLowerCase());
        holder.release.setText(adapterList.get(position).getReleaseDate());
        Picasso.with(context).load("http://image.tmdb.org/t/p/w150/" + adapterList.get(position).getImageUrl()).placeholder(R.drawable.yoda_placeholder)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private TextView title, director, producer, release;
        private TextView directorT, producerT, releaseT;
        private ImageView poster;

        private MovieViewHolder(final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.line_title);
            director = itemView.findViewById(R.id.line_director);
            producer = itemView.findViewById(R.id.line_producer);
            release = itemView.findViewById(R.id.line_release);
            directorT = itemView.findViewById(R.id.line_director_text);
            producerT = itemView.findViewById(R.id.line_producer_text);
            releaseT = itemView.findViewById(R.id.line_release_text);
            poster = itemView.findViewById(R.id.line_poster);

            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   movieAdapterListener.buttonMoreInformation(view, getAdapterPosition());
                }
            });

            Context c = itemView.getContext();
            title.setTypeface(FontUtils.fontRegular(c));
            director.setTypeface(FontUtils.fontRegular(c));
            producer.setTypeface(FontUtils.fontRegular(c));
            release.setTypeface(FontUtils.fontRegular(c));
            directorT.setTypeface(FontUtils.fontRegular(c));
            producerT.setTypeface(FontUtils.fontRegular(c));
            releaseT.setTypeface(FontUtils.fontRegular(c));


            //poster = itemView.findViewById(R.id.line_poster);
//            link.setTypeface(Fonts.fontRegular(c));
//            user.setTypeface(Fonts.fontBold(c));


        }
    }




}
