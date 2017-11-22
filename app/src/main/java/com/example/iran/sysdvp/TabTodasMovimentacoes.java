package com.example.iran.sysdvp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iran.models.Movimento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iran on 02/11/2017.
 */

public class TabTodasMovimentacoes extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_1, container, false);
    }
}
