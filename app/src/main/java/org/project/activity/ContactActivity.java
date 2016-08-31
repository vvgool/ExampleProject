package org.project.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.project.R;
import org.project.adapter.ContactAdapter;
import org.project.base.BaseActivity;
import org.project.base.DividerItemDecoration;
import org.project.helper.ContactHelper;
import org.project.oop.ContactOOP;
import org.project.weight.SidebarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by wiesen on 16-8-29.
 */
public class ContactActivity extends BaseActivity implements SidebarView.SideBarListener {
    @BindView(R.id.sv_select_contact)
    SidebarView mContactSelect;

    @BindView(R.id.rv_contacts)
    RecyclerView mContactDisplay;

    @BindView(R.id.tb_contact_toolbar)
    Toolbar mToolbar;

    ContactAdapter mContactAdapter;

    private List<ContactOOP> mContacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setTitle(R.string.friends_list);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mContactAdapter = new ContactAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mContactDisplay.setLayoutManager(linearLayoutManager);
        mContactDisplay.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        mContactDisplay.setAdapter(mContactAdapter);
        mContacts = new ArrayList<>();
        if (ContactHelper.queryContacts(mContacts)){
            mContactAdapter.addAllData(mContacts);
        }

        mContactSelect.setOnSideBarClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contact;
    }

    @Override
    public void onItemClickListener(String str) {
        int position = 0;
        Map<String, ArrayList<ContactOOP>> allContacts = mContactAdapter.getAllContactsMap();
        for (String tile:ContactAdapter.mContent){
            if (str.equals(tile)) break;
            if (allContacts.containsKey(tile)) {
                position += 1 + allContacts.get(tile).size();
            }
        }
        mContactDisplay.scrollToPosition(position);
    }
}
