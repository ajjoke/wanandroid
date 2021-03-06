package com.aj.article_module.adapter

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aj.article_module.R
import com.aj.article_module.bean.Article
import com.aj.article_module.bean.PageDataInfo
import com.aj.article_module.databinding.ArticleItemArticlesBinding
import com.aj.data_service.ArouterPageManger
import com.aj.data_service.ArouterUrlManage
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlin.random.Random


/**
 *
 * @Package:        com.aj.article_module.adapter
 * @ClassName:      OfficialAccountListAdapter
 * @Description:    公众号文章列表
 * @Author:         zhy
 * @CreateDate:     2020/8/03 11:12
 */

class OfficialAccountArticleListFMAdapter : BaseQuickAdapter<Article, BaseViewHolder>,
    LoadMoreModule {
    private val colors = intArrayOf(
        R.color.base_color_deep_gray,
        R.color.base_color_gray,
        R.color.base_color_757575,
        R.color.base_color_455A64,
        R.color.base_color_9E9E9E,
        R.color.base_color_BDBDBD
    )

    constructor(layoutResId: Int = R.layout.article_item_articles) : super(
        layoutResId
    )

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
        setOnItemClickListener { _, _, position ->
            val bundle = Bundle()
            bundle.putString(PageDataInfo.articleUrl, data[position].link)
            bundle.putString(PageDataInfo.articleTitle, data[position].title)
            ArouterPageManger.navigationWithParams(
                context,
                ArouterUrlManage.ARTICLE_ARTICLE_DETAIL,
                bundle
            )
        }

        setOnItemChildClickListener{ _: BaseQuickAdapter<Any?, BaseViewHolder>, view: View, _: Int ->
            when(view.id){

            }
        }
    }

    override fun convert(holder: BaseViewHolder, item: Article) {
        // 获取 Binding
        val articleDamaging: ArticleItemArticlesBinding? =
            DataBindingUtil.getBinding(holder.itemView)
        if (articleDamaging != null) {
            articleDamaging.article = item
        }
        val tvTab = holder.getView<View>(R.id.tv_tab)
        val gradientDrawable: GradientDrawable = tvTab.background as GradientDrawable
        gradientDrawable.setColor(
            ContextCompat.getColor(
                context,
                colors[Random.nextInt(colors.size)]
            )
        )
        val ivFavorite = holder.getView<ImageView>(R.id.iv_favorite)
        if (item.collect) {
            ivFavorite.setImageResource((R.drawable.article_ic_favorite_select_24))
        }else{
            ivFavorite.setImageResource((R.drawable.article_ic_favorite_normal_24))
        }

    }

}

