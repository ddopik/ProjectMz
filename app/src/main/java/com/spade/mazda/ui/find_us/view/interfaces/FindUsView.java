package com.spade.mazda.ui.find_us.view.interfaces;

import android.support.v4.app.Fragment;

import com.spade.mazda.base.BaseView;

import java.util.List;

/**
 * Created by Ayman Abouzeid on 11/6/17.
 */

public interface FindUsView extends BaseView {

    void addFragment(List<Fragment> fragmentList, List<String> fragmentTitles);
}
