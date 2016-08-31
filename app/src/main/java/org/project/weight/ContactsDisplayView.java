package org.project.weight;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.project.R;
import org.project.adapter.ContactAdapter;
import org.project.base.DividerItemDecoration;
import org.project.helper.HanziHelper;
import org.project.oop.ContactOOP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wiesen on 16-8-30.
 */
public class ContactsDisplayView extends RelativeLayout implements SidebarView.SideBarListener {
    private SidebarView mSidebarView;
    private RecyclerView mContactsDisplay;
    private ContactsAdapter mContactAdapter;

    public ContactsDisplayView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    private void initView(){
        if (mSidebarView != null){
            mSidebarView.setOnSideBarClickListener(this);
        }
        if (mContactsDisplay != null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mContactsDisplay.setLayoutManager(linearLayoutManager);
            mContactsDisplay.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
            mContactAdapter = new ContactsAdapter(getContext());
            mContactsDisplay.setAdapter(mContactAdapter);
        }
    }


    public void addContacts(List<ContactOOP> contactOOPs){
        if (mContactAdapter != null){
            mContactAdapter.addAllData(contactOOPs);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0;i<childCount;i++){
            View childAt = getChildAt(i);
            if (childAt instanceof SidebarView){
                mSidebarView = (SidebarView) childAt;
            }
            if (childAt instanceof RecyclerView){
                mContactsDisplay = (RecyclerView) childAt;
            }
        }
        initView();
    }

    @Override
    public void onItemClickListener(String str) {

    }


    private static class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.RecyclerViewHolder> {
        private LayoutInflater mInflater;
        public List<ContactOOP> mDataList;
        public Context mContext;

        private static final int CONTACT_TITLE = 0;
        private static final int CONTACT_ITEM = 1;
        private Map<String,ArrayList<ContactOOP>> mContactCollection;
        private static final String[] mContent = SidebarView.mContent;


        public ContactsAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mDataList = new ArrayList<>();
            mContactCollection = new HashMap<>();
        }

        public void addAllData(List<ContactOOP> list) {
            new LoadDataAsy().execute(list);
        }

        @Override
        public int getItemViewType(int position) {
            ContactOOP itemData = getItemData(position);
            if (itemData.mNumber == null || itemData.mNumber.equals("")){
                return CONTACT_TITLE;
            }
            return CONTACT_ITEM;
        }

        public int getItemViewId(int viewType) {
            return viewType == CONTACT_ITEM ? R.layout.item_contact_content:R.layout.item_contact_title;
        }


        class LoadDataAsy extends AsyncTask<List<ContactOOP>,Void,Boolean> {

            @Override
            protected Boolean doInBackground(List<ContactOOP>... params) {
                List<ContactOOP> contactOOPs = params[0];
                Pattern pattern = Pattern.compile("[A-Z]");
                for (ContactOOP contactOOP: contactOOPs){
                    String firstName = HanziHelper.hanziToPinyinByFirst(contactOOP.mHostName);
                    Matcher matcher = pattern.matcher(firstName);
                    boolean matches = matcher.matches();
                    firstName = matches? firstName: mContent[mContent.length-1];

                    if (mContactCollection.containsKey(firstName)){
                        mContactCollection.get(firstName).add(contactOOP);
                    }else {
                        ArrayList<ContactOOP> mapContactOOP = new ArrayList<>();
                        mapContactOOP.add(contactOOP);
                        mContactCollection.put(firstName,mapContactOOP);
                    }

                }
                for (String str:mContent){
                    if (mContactCollection.containsKey(str)){
                        mDataList.add(new ContactOOP(str,null));
                        mDataList.addAll(mContactCollection.get(str));
                    }
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                notifyDataSetChanged();
            }
        }

        public List<ContactOOP> getAllData(){
            return mDataList;
        }



        public ContactOOP getItemData(int position){
            return mDataList.get(position);
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(getItemViewId(viewType),null);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            ContactOOP itemData = getItemData(position);
            if(CONTACT_ITEM == getItemViewType(position)){
                ((TextView)holder.getView(R.id.tv_host_name)).setText(itemData.mHostName);
                ((TextView)holder.getView(R.id.tv_host_num)).setText(itemData.mNumber);
            }else {
                ((TextView)holder.getView(R.id.tv_contact_title)).setText(itemData.mHostName);
            }
        }


        @Override
        public int getItemCount() {
            return mDataList.size();
        }



        public class RecyclerViewHolder extends RecyclerView.ViewHolder{

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
}
