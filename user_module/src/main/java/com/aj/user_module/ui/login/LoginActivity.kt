package com.aj.user_module.ui.login


import android.util.Log
import androidx.lifecycle.Observer
import com.aj.base_module.ui.activity.BaseDataBindVMActivity
import com.aj.base_module.ui.viewmodel.BaseViewModel
import com.aj.base_module.ui.viewmodel.initViewModel
import com.aj.data_service.ArouterUrlManage
import com.aj.user_module.R
import com.aj.user_module.databinding.UserActivityLoginBinding
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.user_activity_login.*

/**
 * @author zhy
 * @time 2020/5/17.
 */
@Route(path = ArouterUrlManage.USER_LOGINACTIVITY)
class LoginActivity : BaseDataBindVMActivity<UserActivityLoginBinding>() {


    override fun getLayoutId(): Int = R.layout.user_activity_login
    private val mViewModel by lazy {
        initViewModel(
            this, LoginViewModel::class, LoginRepository::class
        )
    }

    override fun getViewModel(): BaseViewModel {
        return mViewModel;
    }

    override fun initView() {
        mViewModel.loginBean.observe(this, Observer { loginBean ->
            if (loginBean != null) {
                finish()
            }
        })

    }

    override fun initData() {
        bt_login.setOnClickListener {
            mViewModel.login(et_account.text.toString(),et_password.text.toString())
        }
    }



}