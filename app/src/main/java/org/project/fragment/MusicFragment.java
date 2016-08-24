package org.project.fragment;

import org.project.R;
import org.project.base.App;
import org.project.base.BaseFragment;

/**
 * Created by ljdy on 2016/8/16.
 */
public class MusicFragment extends BaseFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_music;
    }

    @Override
    public String getTitle() {
        return App.getInstance().getString(R.string.music_fragment_title);
    }
}
