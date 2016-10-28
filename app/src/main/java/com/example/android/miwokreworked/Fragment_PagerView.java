package com.example.android.miwokreworked;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Zack on 2016/10/27.
 */
public class Fragment_PagerView extends FragmentPagerAdapter{

    private String tabTitles[] = new String[] { "Numbers", "Family", "Colours", "Phrases" };
    Context context;

    public Fragment_PagerView(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new NumbersFragments();
        else if (position == 1)
            return new FamilyFragment();
        else if (position == 2)
            return new ColoursFragments();
        else
        return new PhrasesFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
