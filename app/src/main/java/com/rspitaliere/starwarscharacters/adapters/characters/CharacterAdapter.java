package com.rspitaliere.starwarscharacters.adapters.characters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rspitaliere.starwarscharacters.R;
import com.rspitaliere.starwarscharacters.utils.FontUtils;

import java.util.List;

/**
 * Created by rspitaliere on 31/08/17.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<CharacterClassAdapter> adapterList;
    private LayoutInflater layoutInflater;
    public CharacterAdapterClickListener characterAdapterListener;

    public CharacterAdapter(Context context, List<CharacterClassAdapter> l, CharacterAdapterClickListener listener){
        adapterList = l;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        characterAdapterListener = listener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.line_recyclerview_characters, parent, false);
        CharacterViewHolder quotationViewHolder = new CharacterViewHolder(view);
        return quotationViewHolder;
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        holder.user.setText(adapterList.get(position).getUser());
        holder.character.setText(adapterList.get(position).getCharacter().toLowerCase());
        holder.link.setText(adapterList.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder{
        private TextView character, user, link;
        private CardView moreInfo;

        private CharacterViewHolder(final View itemView) {
            super(itemView);

            character = itemView.findViewById(R.id.line_character);
            user = itemView.findViewById(R.id.line_user);
            link = itemView.findViewById(R.id.line_link);
            moreInfo = itemView.findViewById(R.id.line_more_info);

            moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    characterAdapterListener.buttonMoreInformation(view, getAdapterPosition());
                }
            });


            Context c = itemView.getContext();
            character.setTypeface(FontUtils.fontRegular(c));
            user.setTypeface(FontUtils.fontRegular(c));
            link.setTypeface(FontUtils.fontRegular(c));
//            link.setTypeface(Fonts.fontRegular(c));
//            user.setTypeface(Fonts.fontBold(c));


        }
    }




}
