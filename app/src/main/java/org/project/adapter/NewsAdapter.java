package org.project.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import org.project.R;
import org.project.base.RecyclerAdapter;
import org.project.oop.NewsOop;

/**
 * Created by ljdy on 2016/8/18.
 */
public class NewsAdapter extends RecyclerAdapter<NewsOop> {
    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewId() {
        return R.layout.recy_item_news;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        NewsOop news = getItemData(position);
        if (news == null) return;
        ((TextView)holder.getView(R.id.tv_news_title)).setText(news.mNewsTitle);
        ((ImageView)holder.getView(R.id.iv_news_from_icon)).setImageBitmap(news.mSourceIcon);
        ((TextView)holder.getView(R.id.tv_news_source_from)).setText(news.mSourceName);
        ((TextView)holder.getView(R.id.tv_news_comments)).setText(String.format(mContext.
                getString(R.string.news_item_comment),news.mCommentCount));
    }
}
