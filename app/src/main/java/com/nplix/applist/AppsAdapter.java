package com.nplix.applist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Pawan on 2/20/2016.
 */
public class AppsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int FADE_DURATION = 1000;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private String TAG="LoadImage";

    Context context;

    Bundle bundle=new Bundle();
    private List<AppData> questionList;

    private boolean mWithHeader;
    private boolean mWithFooter;
    private View.OnClickListener mOnClickListener;

    public AppsAdapter(List<AppData> apps, Context context, boolean withHeader, boolean withFooter) {
        this.questionList = apps;
        this.context=context;
        this.mWithHeader=withHeader;
        this.mWithFooter=withFooter;

    }
    @Override
    public int getItemViewType(int position) {

        if (mWithHeader && isPositionHeader(position))
            return TYPE_HEADER;
        if (mWithFooter && isPositionFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewType==TYPE_HEADER) {

            return new header(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false));
        }
        else if(viewType==TYPE_FOOTER){
            return new footer(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer, viewGroup, false));
        }
        else {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.appitem, viewGroup, false);
            VideoViewHolder holder = new VideoViewHolder(itemView);
            itemView.setTag(holder);
            itemView.setOnClickListener(mOnClickListener);

            return holder;
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  header){
            //((header) holder).vName.setText(album_name);
        }
        else if(holder instanceof  footer){
            ((footer) holder).context = context;
        }
        else {
            AppData appData=getItem(position);

            ((VideoViewHolder)holder).vName.setText(appData.getAppName());
            ((VideoViewHolder)holder).vPackageName.setText(appData.getPackageName());
            ((VideoViewHolder)holder).vImage.setImageDrawable(appData.getIcon());


        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        int itemCount=0;
       if(questionList!=null) {


    itemCount = questionList.size();
    if (mWithHeader)
        itemCount = itemCount + 1;
    if (mWithFooter)
        itemCount = itemCount + 1;
   // return itemCount;
   }
return itemCount;
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }
    public void setOnClickListener(View.OnClickListener lis) {
        mOnClickListener = lis;
    }

    protected AppData getItem(int position) {
        return mWithHeader ? questionList.get(position - 1) : questionList.get(position);
    }

    private int getItemPosition(int position){
        return mWithHeader ? position - 1 : position;
    }

    public void setData(List<AppData> questionList) {
        this.questionList=questionList;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        protected ImageView vImage;
        protected TextView vName,vPackageName;


        protected  Context context;

        public VideoViewHolder(View v) {
            super(v);
            vImage = (ImageView)  v.findViewById(R.id.image);
            vName = (TextView) v.findViewById(R.id.name);
            vPackageName=(TextView) v.findViewById(R.id.packageName);
        }

        public void clearAnimation() {
            this.clearAnimation();
        }


    }

    public class header extends RecyclerView.ViewHolder {


        protected  Context context;
        protected int position;

        public header(View v) {
            super(v);


        }


    }


    public class footer extends RecyclerView.ViewHolder {


        protected  Context context;
        protected int position;

        public footer(View v) {
            super(v);


        }


    }

}
