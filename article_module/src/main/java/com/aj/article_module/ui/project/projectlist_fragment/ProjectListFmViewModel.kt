package com.aj.article_module.ui.project.projectlist_fragment


import androidx.lifecycle.MutableLiveData
import com.aj.article_module.bean.Article

import com.aj.article_module.bean.OfficialAccount

import com.aj.base_module.ui.viewmodel.BaseViewModel


class ProjectListFmViewModel(private val repository: ProjectListFmRepository) : BaseViewModel() {
    var articleList = MutableLiveData<List<Article>>()
    var officialAccount = MutableLiveData<OfficialAccount>()

    fun getArticles(num :Int) {
        launch {
            articleList.value =  repository.getArticles(num).data.datas
        }
    }

    fun getWxArticle() {
        launch {
            officialAccount.value = repository.getWxArticle().data
        }
    }
}