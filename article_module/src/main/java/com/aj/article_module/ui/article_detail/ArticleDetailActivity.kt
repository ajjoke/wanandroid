package com.aj.article_module.ui.article_detail

import android.os.Bundle
import android.text.Html
import android.webkit.WebSettings
import com.aj.article_module.R
import com.aj.article_module.bean.PageDataInfo
import com.aj.base_module.ui.activity.BaseActivity
import com.aj.data_service.ArouterUrlManage
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.article_activity_article_detail.*
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.article_activity_article_detail.toolbar



/**
 * @author zhy
 * @time 2020/6/23.
 */
@Route(path = ArouterUrlManage.ARTICLE_ARTICLE_DETAIL)
class ArticleDetailActivity : BaseActivity() {
    var url = ""
    override fun getLayoutId(): Int = R.layout.article_activity_article_detail

    override fun initView() {
        val bundle  = intent.extras
        val title = bundle?.getString(PageDataInfo.articleTitle).toString()
        toolbar.title = Html.fromHtml(title)
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener {
            this.finish()
        }
        initWebView()
    }

    override fun initData() {
        super.initData()
        val bundle  = intent.extras
        url = bundle?.getString(PageDataInfo.articleUrl).toString()

        wv_article.loadUrl(url)
    }

    private fun initWebView() {

        //声明WebSettings子类
        val webSettings: WebSettings = wv_article.settings

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true

        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小

        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。

        webSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放

        webSettings.displayZoomControls = false //隐藏原生的缩放控件


        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存

        webSettings.allowFileAccess = true //设置可以访问文件

        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口

        webSettings.loadsImagesAutomatically = true //支持自动加载图片

        webSettings.defaultTextEncodingName = "utf-8" //设置编码格式

        wv_article.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        view.loadUrl(url)
                    }
                    return true
                } catch (e: Exception) {
                    return false
                }

            }
        }

    }
}