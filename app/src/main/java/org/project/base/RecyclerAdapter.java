package org.project.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiesen on 2016/8/18.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private LayoutInflater mInflater;
    private List<T> mDataList;
    public Context mContext;
    public RecyclerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataList = new ArrayList<>();
    }

    public void addAllData(List<T> list){
        mDataList.clear();
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(T t){
        mDataList.add(t);
        notifyDataSetChanged();
    }

    public T getItemData(int position){
        return mDataList.get(position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(getItemViewId(),null);
        return new RecyclerViewHolder(view);
    }

    public abstract int getItemViewId();


    @Override
    public int getItemCount() {
        return mDataList.size();
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private SparseArray<View> mViewCollection;
        public RecyclerViewHolder(View itemView) {
            super(itemView);

        }

        public <V extends View> V getView(int id){
            if (mViewCollection == null){
                mViewCollection = new SparseArray<>();
            }
            View view = mViewCollection.get(id);
            if (view == null){
                view = itemView.findViewById(id);
                mViewCollection.put(id,view);
            }
            return (V) view;
        }
    }
}
