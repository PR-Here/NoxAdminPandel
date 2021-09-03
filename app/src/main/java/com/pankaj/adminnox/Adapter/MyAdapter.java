package com.pankaj.adminnox.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.pankaj.adminnox.Activity.DetailActivity;
import com.pankaj.adminnox.JavaClass.Utils;
import com.pankaj.adminnox.ModeActivity.Scientist;
import com.pankaj.adminnox.R;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.pankaj.adminnox.JavaClass.Utils.searchString;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context c;
    private int mBackground;
    private int[] mMaterialColors;
    public List<Scientist> scientists;
    interface ItemClickListener {
        void onItemClick(int pos);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private final TextView nameTxt, galaxyTxt;

        private ItemClickListener itemClickListener;
        ViewHolder(View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_layout);

            galaxyTxt = itemView.findViewById(R.id.no);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
        void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
    public MyAdapter(List<Scientist> scientists) {
        this.scientists = scientists;
    }
    private void prepareLetterIcons(Context c){
        TypedValue mTypedValue = new TypedValue();
        c.getTheme().resolveAttribute(R.attr.selectableItemBackground,
                mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.c = parent.getContext();
        prepareLetterIcons(c);
        View view = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get current scientist
        final Scientist s = scientists.get(position);
        //bind data to widgets
        holder.nameTxt.setText(s.getName());
        holder.galaxyTxt.setText(s.getGalaxy());

        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#efefef"));
        }
        //get name and galaxy
        String name = s.getName().toLowerCase(Locale.getDefault());
        //highlight name text while searching
        if (name.contains(searchString) && !(searchString.isEmpty())) {
            int startPos = name.indexOf(searchString);
            int endPos = startPos + searchString.length();
            Spannable spanString = Spannable.Factory.getInstance().
                    newSpannable(holder.nameTxt.getText());
            spanString.setSpan(new ForegroundColorSpan(Color.RED), startPos, endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.nameTxt.setText(spanString);
        } else {
            //Utils.show(ctx, "Search string empty");
        }
        //open detailactivity when clicked
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Utils.sendScientistToActivity(c, s,
                        DetailActivity.class);
            }
        });
    }
    @Override
    public int getItemCount() {
        return scientists.size();
    }
}
