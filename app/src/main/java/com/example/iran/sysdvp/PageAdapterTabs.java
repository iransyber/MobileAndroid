package com.example.iran.sysdvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Iran on 02/11/2017.
 */

public class PageAdapterTabs extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapterTabs(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabTodasMovimentacoes todas = new TabTodasMovimentacoes();
                return todas;
            case 1:
                TabEntradas entradas = new TabEntradas();
                return entradas;
            case 2:
                TabSaidas saidas = new TabSaidas();
                return saidas;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
