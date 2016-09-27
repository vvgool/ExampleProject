package org.project.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.project.R;
import org.project.base.RecyclerAdapter;
import org.project.helper.HanziHelper;
import org.project.oop.ContactOOP;
import org.project.weight.SidebarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wiesen on 16-8-29.
 */
public class ContactAdapter extends RecyclerAdapter<ContactOOP> {
    private static final int CONTACT_TITLE = 0;
    private static final int CONTACT_ITEM = 1;
    private Map<String,ArrayList<ContactOOP>> mContactCollection;
    public static final String[] mContent = SidebarView.mContent;


    public ContactAdapter(Context context) {
        super(context);
        mContactCollection = new HashMap<>();
    }



    @Override
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

    @Override
    public int getItemViewId(int viewType) {
        return viewType == CONTACT_ITEM ? R.layout.item_contact_content:R.layout.item_contact_title;
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

    public Map<String, ArrayList<ContactOOP>> getAllContactsMap() {

        return mContactCollection;
    }

    class LoadDataAsy extends AsyncTask<List<ContactOOP>,Void,Boolean>{

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
}
