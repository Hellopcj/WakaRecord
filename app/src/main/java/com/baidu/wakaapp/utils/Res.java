/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.wakaapp.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 动态获取资源id工具类
 */
public class Res {

    /**
     * 记录Fragment实例
     */
    private static String sFragmentHashCode = null;

    /**
     * 非线程安全,需要在主线程操作
     */
    private static Map<String, ContextInfo> sMap = new HashMap<>();

    public static void setFragmentHashCode(String hashCode) {
        sFragmentHashCode = hashCode;
    }

    private static String getFragmentHashCode() {
        return sFragmentHashCode;
    }

    /**
     * 添加Res源
     *
     * @param context
     */
    public static void addResource(Context context) {
        addResource(context, context.getPackageName());
    }

    /**
     * 添加Res源
     *
     * @param context
     * @param packageName
     */
    public static void addResource(Context context, String packageName) {
        sMap.put(packageName, new ContextInfo(context));
    }

    /**
     * 添加Res源
     *
     * @param context
     * @param packageName
     * @param inflaterContext 用于创建View
     */
    public static void addResource(Context context, String packageName, Context inflaterContext) {
        ContextInfo contextInfo = new ContextInfo(context);
        contextInfo.setInflaterContext(inflaterContext);
        sMap.put(packageName, contextInfo);
    }

    /**
     * 删除资源
     *
     * @param packageName
     */
    public static void removeResource(String packageName) {
        sMap.remove(packageName);
    }

    /**
     * 清除所有资源
     */
    public static void clear() {
        sMap.clear();
    }

    /**
     * 清除资源
     *
     * @param context
     */
    public static void clear(Context context) {
        boolean match = false;
        Set<String> keys = sMap.keySet();
        for (String pkgName : keys) {
            ContextInfo info = sMap.get(pkgName);
            if (info.mResContext == context) {
                match = true;
                break;
            }
        }
        if (match) {
            clear();
        }
    }

    /**
     * 清除资源
     *
     * @param context
     */
    public static void clear(String hashCode, Context context) {
        if (TextUtils.isEmpty(hashCode)) {
            return;
        }
        if (!hashCode.equals(getFragmentHashCode())) {
            return;
        }
        clear(context);
    }

    /**
     * 延迟1秒清空资源
     *
     * @param hashCode
     * @param context
     */
    public static void clearDelay(final String hashCode, final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clear(hashCode, context);
            }
        }, 1000);
    }

    /**
     * 获取字符串
     *
     * @param resName
     *
     * @return
     */
    public static String getString(String resName) {
        ResInfo info = findResInfo(resName, "string");
        if (info != null) {
            return info.getString();
        }
        return null;
    }

    /**
     * 获取dimen值
     *
     * @param resName
     *
     * @return
     */
    public static int getDimensionPixelSize(String resName) {
        ResInfo info = findResInfo(resName, "dimen");
        if (info != null) {
            return info.getDimensionPixelSize();
        }
        return 0;
    }

    /**
     * 获取Color值
     *
     * @param resName
     *
     * @return
     */
    public static int getColor(String resName) {
        ResInfo info = findResInfo(resName, "color");
        if (info != null) {
            return info.getColor();
        }
        return 0;
    }

    public static Drawable getDrawable(String resName) {
        ResInfo info = findResInfo(resName, "drawable");
        if (info != null) {
            return info.getDrawable();
        }
        return null;
    }

    /**
     * 解码图片
     *
     * @param resName
     *
     * @return
     */
    public static Bitmap decodeBitmap(String resName) {
        ResInfo info = findResInfo(resName, "drawable");
        if (info != null) {
            return info.decodeBitmap();
        }
        return null;
    }

    /**
     * 获取getColorStateList
     *
     * @param resName
     *
     * @return
     */
    public static ColorStateList getColorStateList(String resName) {
        ResInfo info = findResInfo(resName, "color");
        if (info != null) {
            return info.getColorStateList();
        }
        return null;
    }

    /**
     * inflate view
     *
     * @param resName
     *
     * @return
     */
    public static View inflate(String resName) {
        ResInfo info = findResInfo(resName, "layout");
        if (info != null) {
            return info.inflate();
        }
        return null;
    }

    public static View inflate(String resName, ViewGroup root, boolean attachToRoot) {
        ResInfo info = findResInfo(resName, "layout");
        if (info != null) {
            return info.inflate(root, attachToRoot);
        }
        return null;
    }

    /**
     * 获取id
     *
     * @param resName 资源名称
     *
     * @return 资源id
     */
    public static int id(String resName) {
        ResInfo info = findResInfo(resName, "id");
        if (info != null) {
            return info.getResId();
        }
        return -1;
    }

    /**
     * 获取 drawable id
     *
     * @param resName 资源名称
     *
     * @return 资源id
     */
    public static int drawableIId(String resName) {
        ResInfo info = findResInfo(resName, "drawable");
        if (info != null) {
            return info.getResId();
        }
        return -1;
    }
    /**
     * 查找View
     *
     * @param parent
     * @param idName
     *
     * @return
     */
    public static View findViewById(View parent, String idName) {
        return parent.findViewById(id(idName));
    }

    /**
     * 加载动画
     *
     * @param resName
     *
     * @return
     */
    public static Animation loadAnimation(String resName) {
        ResInfo info = findResInfo(resName, "anim");
        if (info != null) {
            return info.loadAnimation();
        }
        return null;
    }

    /**
     * 获取raw类型资源id
     *
     * @param resName 资源名称
     *
     * @return 资源id
     */
    public static int getRaw(String resName) {
        return getIdentifier(resName, "raw");
    }

    /**
     * 获取style类型资源id
     *
     * @param resName 资源名称
     *
     * @return 资源id
     */
    public static int getStyle(String resName) {
        return getIdentifier(resName, "style");
    }

    /**
     * 查找可以解析对应资源的Res
     *
     * @param resName
     * @param resType
     *
     * @return
     */
    private static ResInfo findResInfo(String resName, String resType) {
        Set<String> keys = sMap.keySet();
        for (String pkgName : keys) {
            ContextInfo info = sMap.get(pkgName);
            Context context = info.mResContext;
            int id = getIdentifier(context.getResources(), resName, resType, pkgName);
            if (id > 0) {
                return new ResInfo(info, id);
            }
        }
        Log.e("puchunjie",  resName + ", type:" + resType);
        return null;
    }

    /**
     * 获取资源id
     *
     * @param res
     * @param name
     * @param defType
     * @param defPackage
     *
     * @return 如果异常返回-1
     */
    private static int getIdentifier(Resources res, String name, String defType, String defPackage) {
        try {
            return res.getIdentifier(name, defType, defPackage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取id
     *
     * @param resName 资源名称
     * @param defType 资源类型
     *
     * @return 资源id
     */
    private static int getIdentifier(String resName, String defType) {
        ResInfo info = findResInfo(resName, defType);
        if (info != null) {
            return info.getResId();
        }
        return -1;
    }

    /**
     * 用于保存Context信息
     */
    private static class ContextInfo {

        /**
         * 用于查找资源
         */
        private final Context mResContext;
        private Context mInflaterContext;

        public ContextInfo(Context context) {
            mResContext = context;
        }

        public void setInflaterContext(Context context) {
            mInflaterContext = context;
        }
    }

    /**
     * 描述资源信息
     */
    private static class ResInfo {

        private final ContextInfo mContextInfo;
        private final int mResId;

        public ResInfo(ContextInfo context, int resId) {
            mContextInfo = context;
            mResId = resId;
        }

        public String getString() {
            return getResources().getString(mResId);
        }

        public int getDimensionPixelSize() {
            return getResources().getDimensionPixelSize(mResId);
        }

        public Drawable getDrawable() {
            return getResources().getDrawable(mResId);
        }

        /**
         * 解码图片
         *
         * @return
         */
        public Bitmap decodeBitmap() {
            return BitmapFactory.decodeResource(getResources(), mResId);
        }


        public int getColor() {
            return getResources().getColor(mResId);
        }

        public ColorStateList getColorStateList() {
            return getResources().getColorStateList(mResId);
        }

        public View inflate() {
            return getLayoutInflater().inflate(mResId, null);
        }

        public View inflate(ViewGroup root, boolean attachToRoot) {
            return getLayoutInflater().inflate(mResId, root, attachToRoot);
        }

        public int getResId() {
            return mResId;
        }

        public Animation loadAnimation() {
            return AnimationUtils.loadAnimation(mContextInfo.mResContext, mResId);
        }

        private Resources getResources() {
            return mContextInfo.mResContext.getResources();
        }

        /**
         * 用于创建View
         *
         * @return
         */
        private LayoutInflater getLayoutInflater() {
            if (mContextInfo.mInflaterContext != null) {
                return LayoutInflater.from(mContextInfo.mInflaterContext);
            }
            return LayoutInflater.from(mContextInfo.mResContext);
        }
    }
}
