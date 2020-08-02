package com.aj.base_module.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.aj.base_module.R
import kotlinx.android.synthetic.main.base_toolbar.*


/**
 *
 * @Package:        com.aj.base_module.ui.activity
 * @ClassName:      BaseFragmentActivity
 * @Description:    页面内容为fragment的activity
 * @Author:         zhy
 * @CreateDate:     2020/7/31 14:12
 */
abstract class BaseFragmentActivity : BaseActivity() {
    lateinit var  fragmentView :Fragment
    override fun getLayoutId(): Int = R.layout.base_activity_fragment

    override fun initView() {
        toolbar.title = setPageTitle()
        fragmentView = setFragmentView()
        showFragment()
    }

    abstract fun setPageTitle() : String

    abstract fun setFragmentTag() : String

    abstract fun setFragmentView() : Fragment

    private fun showFragment(){
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()

//        if (fragmentView == null) {
//            fragmentView = setFragmentView()
            ft.add(R.id.container, fragmentView, setFragmentTag())
//        } else {
//            ft.show(fragmentView)
//        }

        ft.commit()
    }


}