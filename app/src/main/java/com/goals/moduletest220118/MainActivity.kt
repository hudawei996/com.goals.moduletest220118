package com.goals.moduletest220118

import android.app.Activity
import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goals.arouter_annotations.ARouter
import com.goals.loginmodule.LoginJavaMainActivity
import com.goals.personal.PersonalJavaMainActivity

/**
 * 把注解用在kotlin文件上了，只能在java文件中使用（困扰了几天，一直没往这方面想！！针对kotlin文件可能要换种写法，暂未研究，知道的同学可以留言）
并且自动生成的代码路径因项目不同可能不同，比如我的是在

作者：喂_balabala
链接：https://www.jianshu.com/p/d665c2b49483
 */
@ARouter(path="MainActivity")
//class MainActivity : AppCompatActivity() {
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //如果所有子项目的页面也和主项目名称一样，会产生混淆，编辑的时候虽然是不同的页面，但是编译后显示还是会乱套
        //TODO 所以不同的模块，xml页面名称最好是唯一的
        setContentView(R.layout.activity_main)

        var serverUrl = BuildConfig.DEBUG

        //主项目可以不通过路由，直接跳转到各个子项目的页面，因为他依赖了所有的项目，可以不用路由表来找
        //子项目中跳转到 隔壁项目 互不依赖 需要通过主项目中的路由表 subsequent
    }

    fun jumpLogin(view: android.view.View) {
        var intent = Intent(this, LoginJavaMainActivity::class.java)
        intent.putExtra("name", "goals")
        startActivity(intent)
    }

    fun jumpPersonal(view: android.view.View) {
        var intent = Intent(this, PersonalJavaMainActivity::class.java)
        intent.putExtra("name", "goals")
        startActivity(intent)
    }

    fun jump3(view: android.view.View) {
//        var targetClass = MainActivity3$$$$$$$$$ARouter.findTagretClass("MainActivity3")
//        startActivity(targetClass)
    }
}