package com.shuyu.github.kotlin.module.info

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.shuyu.github.kotlin.R
import com.shuyu.github.kotlin.databinding.ActivityUserInfoBinding
import com.shuyu.github.kotlin.di.Injectable
import com.shuyu.github.kotlin.module.ARouterAddress
import kotlinx.android.synthetic.main.activity_user_info.*
import javax.inject.Inject

/**
 * 用戶信息
 * Created by guoshuyu
 * Date: 2018-11-19
 */
@Route(path = ARouterAddress.UserInfoActivity)
class UserInfoActivity : AppCompatActivity(), Injectable {

    companion object {
        fun gotoUserInfo() {
            getRouterNavigation(ARouterAddress.UserInfoActivity).navigation()
        }

        fun getRouterNavigation(uri: String): Postcard {
            return ARouter.getInstance()
                    .build(uri)
        }
    }

    private lateinit var viewModel: UserInfoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityUserInfoBinding>(this, R.layout.activity_user_info)
        initTitle()
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UserInfoViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }


    /**
     * 初始化title
     */
    private fun initTitle() {
        setSupportActionBar(activity_userInfo_toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }
        activity_userInfo_toolbar.title = getString(R.string.person)
    }

}