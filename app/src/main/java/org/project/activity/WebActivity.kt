package org.project.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.webkit.WebView
import android.widget.Toast
import org.project.R
import org.project.base.BaseActivity

class WebActivity : BaseActivity() {

    val mShowWeb :WebView by lazy { findViewById(R.id.wv_show_web) as WebView}

    val mToolBar :Toolbar by lazy { findViewById(R.id.web_toolbar) as Toolbar}


    override fun getContentViewId(): Int {
        return R.layout.activity_web
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent:Intent = this.intent
        val url:String? = intent.extras.getString(INTERNET_URL)
        url?:finish().run { Toast.makeText(baseContext,"url:$url",Toast.LENGTH_SHORT).show(); return }
        mToolBar.setNavigationIcon(R.drawable.back)
        mToolBar.setTitle(R.string.tool_back)
        setSupportActionBar(mToolBar)
        mToolBar.setNavigationOnClickListener { onBackPressed() }

        mShowWeb.loadUrl(url)
    }

    companion object{
         val INTERNET_URL:String = "web_url"
         fun goToWebActivity(context :Context,url:String?){
            val intent:Intent = Intent(context,WebActivity::class.java)
            intent.putExtra(INTERNET_URL,url)
            context.startActivity(intent)
        }
    }
}
