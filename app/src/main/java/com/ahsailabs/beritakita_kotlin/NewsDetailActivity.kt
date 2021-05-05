package com.ahsailabs.beritakita_kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.ahsailabs.beritakita_kotlin.configs.Config
import com.ahsailabs.beritakita_kotlin.ui.detail.models.NewsDetail
import com.ahsailabs.beritakita_kotlin.ui.detail.models.NewsDetailResponse
import com.ahsailabs.beritakita_kotlin.utils.HttpUtil
import com.ahsailabs.beritakita_kotlin.utils.InfoUtil
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity() {
    private var tvTitle: TextView? = null
    private var tvUser: TextView? = null
    private var tvDate: TextView? = null
    private var tvBody: MaterialTextView? = null
    private var ivPhoto: ImageView? = null
    private var svMain: ScrollView? = null
    private var llLoadingPanel: LinearLayout? = null
    private var pbLoadingIndicator: ProgressBar? = null

    companion object {
        private const val PARAM_NEWS_ID = "param_news_id"

        fun start(context: Context, newsId: String){
            val detailIntent = Intent(context, NewsDetailActivity::class.java)
            detailIntent.putExtra(PARAM_NEWS_ID, newsId)
            context.startActivity(detailIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        val newsId = intent.getStringExtra(PARAM_NEWS_ID)
        loadViews()
        loadData(newsId!!)
    }

    private fun loadViews() {
        tvTitle = findViewById(R.id.tvTitle)
        tvUser = findViewById(R.id.tvUser)
        tvDate = findViewById(R.id.tvDate)
        tvBody = findViewById(R.id.tvBody)
        ivPhoto = findViewById(R.id.ivPhoto)
        svMain = findViewById(R.id.svMain)
        llLoadingPanel = findViewById(R.id.llLoadingPanel)
        pbLoadingIndicator = findViewById(R.id.pbLoadingIndicator)
    }

    private fun showLoading() {
        svMain!!.visibility = View.GONE
        llLoadingPanel!!.visibility = View.VISIBLE
        pbLoadingIndicator!!.progress = 50
    }

    private fun hideLoading() {
        svMain!!.visibility = View.VISIBLE
        llLoadingPanel!!.visibility = View.GONE
        pbLoadingIndicator!!.progress = 0
    }

    private fun loadData(newsId: String) {
        showLoading()
        AndroidNetworking.get(Config.newsDetailUrl.replace("{id}", newsId))
                .setOkHttpClient(HttpUtil.getCLient(this))
                .setTag("newsdetail")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(NewsDetailResponse::class.java, object : ParsedRequestListener<NewsDetailResponse> {
                    override fun onResponse(response: NewsDetailResponse) {
                        if (response.status == 1) {
                            //show detail in views
                            response.data?.let { updateViews(it) }
                        } else {
                            InfoUtil.showToast(this@NewsDetailActivity, response.message)
                        }
                        hideLoading()
                    }

                    override fun onError(anError: ANError) {
                        InfoUtil.showToast(this@NewsDetailActivity, anError.message)
                        hideLoading()
                    }
                })
    }

    override fun onDestroy() {
        AndroidNetworking.cancel("newsdetail")
        super.onDestroy()
    }

    private fun updateViews(newsDetail: NewsDetail) {
        tvTitle?.text = newsDetail.title
        title = newsDetail.title
        //supportActionBar?.title = newsDetail.title
        tvDate?.text = newsDetail.createdAt
        tvUser?.text = newsDetail.createdBy
        tvBody?.text = newsDetail.body
        Picasso.get().load(newsDetail.photo).into(ivPhoto)
    }
}