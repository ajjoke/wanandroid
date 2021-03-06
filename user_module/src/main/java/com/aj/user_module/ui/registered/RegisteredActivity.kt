package com.aj.user_module.ui.registered


import android.view.View
import androidx.lifecycle.Observer
import com.aj.base_module.ui.activity.BaseDataBindVMActivity
import com.aj.base_module.ui.viewmodel.BaseViewModel
import com.aj.base_module.ui.viewmodel.initViewModel
import com.aj.data_service.ArouterPageManger
import com.aj.data_service.ArouterUrlManage
import com.aj.data_service.Service.SharedPreferencesService
import com.aj.user_module.R
import com.aj.user_module.databinding.UserActivityRegisteredBinding
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route

import kotlinx.android.synthetic.main.user_activity_registered.*

/**
 * @author zhy
 * @time 2020/5/17.
 */
@Route(path = ArouterUrlManage.USER_REGISTERED_ACTIVITY)
class RegisteredActivity : BaseDataBindVMActivity<UserActivityRegisteredBinding>() , View.OnClickListener {

    @Autowired(name = ArouterUrlManage.DATAMODULESHAREPREFERENCESSERVICE)
    @JvmField
    var sharedPreferencesService: SharedPreferencesService? = null

    override fun getLayoutId(): Int = R.layout.user_activity_registered
    private val mViewModel by lazy {
        initViewModel(
            this, RegisteredViewModel::class, RegisteredRepository::class
        )
    }

    override fun getViewModel(): BaseViewModel {
        return mViewModel;
    }

    override fun initView() {
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener {
            this.finish()
        }

        mViewModel.loginBean.observe(this, Observer { loginBean ->
            if (loginBean != null) {
                sharedPreferencesService?.setIsLogin(true)
                finish()
            }
        })
        mViewModel.registeredBean.observe(this, Observer { loginBean ->
            if (loginBean != null) {
                mViewModel.login(et_account.text.toString(),et_password.text.toString())
            }
        })
        tv_goto_login.setOnClickListener(this)
        bt_registered.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_goto_login ->{
                ArouterPageManger.navigation(this, ArouterUrlManage.USER_LOGINACTIVITY)
                finish()
            }
            R.id.bt_registered ->{
                mViewModel.registered(et_account.text.toString(),et_password.text.toString())
            }
            else ->{

            }
        }

    }


}