package com.aj.base_module.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager


/**
 *
 * @Package:        com.aj.base_module.utils
 * @ClassName:      PackageUtils
 * @Description:    版本包名等获取
 * @Author:         zhy
 * @CreateDate:     2020/9/10 17:32
 */
object PackageUtils {
    /**
     * 获取版本名称
     *
     * @param context 上下文
     *
     * @return 版本名称
     */
    fun getVersionName(context: Context): String? {

        //获取包管理器
        val pm: PackageManager = context.getPackageManager()
        //获取包信息
        try {
            val packageInfo: PackageInfo = pm.getPackageInfo(context.getPackageName(), 0)
            //返回版本号
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     *
     * @return 版本号
     */
    fun getVersionCode(context: Context): Int {

        //获取包管理器
        val pm: PackageManager = context.getPackageManager()
        //获取包信息
        try {
            val packageInfo: PackageInfo = pm.getPackageInfo(context.getPackageName(), 0)
            //返回版本号
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 获取App的名称
     *
     * @param context 上下文
     *
     * @return 名称
     */
    fun getAppName(context: Context): String? {
        val pm: PackageManager = context.getPackageManager()
        //获取包信息
        try {
            val packageInfo: PackageInfo = pm.getPackageInfo(context.getPackageName(), 0)
            //获取应用 信息
            val applicationInfo = packageInfo.applicationInfo
            //获取albelRes
            val labelRes = applicationInfo.labelRes
            //返回App的名称
            return context.getResources().getString(labelRes)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}