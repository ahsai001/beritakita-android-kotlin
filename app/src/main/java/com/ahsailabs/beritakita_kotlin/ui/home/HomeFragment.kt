package com.ahsailabs.beritakita_kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahsailabs.beritakita_kotlin.R
import com.ahsailabs.beritakita_kotlin.configs.Config
import com.ahsailabs.beritakita_kotlin.ui.home.adapters.NewsAdapter
import com.ahsailabs.beritakita_kotlin.ui.home.models.News
import com.ahsailabs.beritakita_kotlin.ui.home.models.NewsListResponse
import com.ahsailabs.beritakita_kotlin.utils.InfoUtil.showToast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener


class HomeFragment : Fragment() {
    private var rvList: RecyclerView? = null
    private var newsList: ArrayList<News>? = null
    private var newsAdapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViews(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun loadViews(root: View) {
        rvList = root.findViewById(R.id.rvList)
    }

    private fun setupViews(){
        rvList!!.layoutManager = LinearLayoutManager(activity)
        newsList = ArrayList()
        newsAdapter = NewsAdapter(newsList!!)
        rvList!!.adapter = newsAdapter
    }


    private fun loadData() {
        AndroidNetworking.post(Config.newsListUrl)
            .addBodyParameter("groupcode", Config.GROUP_CODE)
            .addBodyParameter("keyword", "")
            .setTag("newslist")
            .setPriority(Priority.HIGH)
            .build()
            .getAsObject(
                NewsListResponse::class.java,
                object : ParsedRequestListener<NewsListResponse> {
                    override fun onResponse(response: NewsListResponse) {
                        if (response.status == 1) {
                            val resultList = response.data
                            //TODO: show listview dengan data resultList
                            resultList?.let {
                                newsList!!.clear()
                                newsList!!.addAll(it)
                                newsAdapter!!.notifyDataSetChanged()
                            }
                        } else {
                            //TODO: show info error
                            showToast(activity, response.message)
                        }
                    }

                    override fun onError(anError: ANError) {
                        //TODO: show info error
                        showToast(activity, anError.message)
                    }
                })
    }


    override fun onDestroyView() {
        AndroidNetworking.cancel("newslist")
        super.onDestroyView()
    }
}