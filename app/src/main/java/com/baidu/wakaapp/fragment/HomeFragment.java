/* * Copyright (c) 2018. puchunjie */package com.baidu.wakaapp.fragment;import android.os.Bundle;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.CompoundButton;import android.widget.ImageButton;import android.widget.Switch;import com.baidu.wakaapp.R;public class HomeFragment extends Fragment implements View.OnClickListener {    private ImageButton ib_start_lan;    private ImageButton ib_start_por;    private Switch sw_audio;    private Switch sw_anchor;    @Nullable    @Override    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,                             @Nullable Bundle savedInstanceState) {        View view = inflater.inflate(R.layout.fragment_home, null);        return view;    }    @Override    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        initView(view);    }    private void initView(View view) {//        // 设置声音控件监听//        sw_audio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//            @Override//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {//                if (isChecked) {////                }//            }//        });//        // 设置开启主播模式控件监听//        sw_anchor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//            @Override//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {//                if (isChecked) {////                }//            }//        });    }    @Override    public void onClick(View v) {        switch (v.getId()) {            default:                break;        }    }}