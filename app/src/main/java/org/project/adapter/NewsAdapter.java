package org.project.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.project.R;
import org.project.base.RecyclerAdapter;
import org.project.helper.ImageLoaderHelper;
import org.project.net.news.NewsMessage;

/**
 * Created by wiesen on 2016/8/18.
 */
public class NewsAdapter extends RecyclerAdapter<NewsMessage> {
    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.recy_item_news;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        NewsMessage news = getItemData(position);
        if (news == null) return;
        ((TextView)holder.getView(R.id.tv_news_title)).setText(news.getTitle());
        ((TextView)holder.getView(R.id.tv_news_author)).setText(news.getAuthor_name());
        ((TextView)holder.getView(R.id.tv_news_source_from)).setText(news.getType() + "\t" + news.getRealtype());
        ((TextView)holder.getView(R.id.tv_time)).setText(news.getDate());
        ImageView pic1 = holder.getView(R.id.iv_pic1);
        ImageView pic2 = holder.getView(R.id.iv_pic2);
        ImageView pic3 = holder.getView(R.id.iv_pic3);
        if(TextUtils.isEmpty(news.getThumbnail_pic_s())){
            pic1.setVisibility(View.GONE);
        }else {
            pic1.setVisibility(View.VISIBLE);
            ImageLoaderHelper.loadImageByCenterCrop(mContext,news.getThumbnail_pic_s(),pic1);
        }
        if(TextUtils.isEmpty(news.getThumbnail_pic_s02())){
            pic2.setVisibility(View.GONE);
        }else {
            pic2.setVisibility(View.VISIBLE);
            ImageLoaderHelper.loadImageByCenterCrop(mContext,news.getThumbnail_pic_s02(),pic2);
        }
        if(TextUtils.isEmpty(news.getThumbnail_pic_s03())){
            pic3.setVisibility(View.GONE);
        }else {
            pic3.setVisibility(View.VISIBLE);
            ImageLoaderHelper.loadImageByCenterCrop(mContext,news.getThumbnail_pic_s03(),pic3);
        }
    }
}
