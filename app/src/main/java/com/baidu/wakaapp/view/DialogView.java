/* * Copyright (c) 2018. puchunjie */package com.baidu.wakaapp.view;import android.app.AlertDialog;import android.app.Dialog;import android.content.Context;import android.support.annotation.NonNull;import android.widget.FrameLayout;import com.baidu.wakaapp.R;/** * Dialog对话框 */public class DialogView extends Dialog {    private AlertDialog.Builder builder;    public DialogView(@NonNull Context context) {        super(context);        init(context);    }    public DialogView(@NonNull Context context, int themeResId) {        super(context, themeResId);        init(context);    }    /**     * 当对话框上在的按钮点击时调用     *     * @param which 指示是哪一个按钮     */    protected void onButtonClick(int which) {    }    private void init(Context context) {        setContentView(R.layout.view_dialog);        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);    }    /**     * 的到builder     * @return     */    public AlertDialog.Builder getBuilder() {        return builder;    }    /**     * 设置builder     * @param builder     */    public void setBuilder(AlertDialog.Builder builder) {        this.builder = builder;    }    public static class Builder{    }}