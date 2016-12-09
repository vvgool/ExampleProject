package org.project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.project.R;
import org.project.base.App;
import org.project.base.BaseFragment;
import org.project.module.ticket.TicketManager;
import org.project.weight.LabelView;

/**
 * Created by ljdy on 2016/8/16.
 */
public class MovieFragment extends BaseFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_movie;
    }

    @Override
    public String getTitle() {
        return App.getInstance().getString(R.string.movie_fragment_title);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TicketManager.requestTicketSource();
        ((LabelView)view.findViewById(R.id.lv_label)).setLabelString("折扣");
    }
}
