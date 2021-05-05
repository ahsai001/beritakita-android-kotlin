package com.ahsailabs.beritakita_kotlin.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ahsailabs.beritakita_kotlin.NewsDetailActivity
import com.ahsailabs.beritakita_kotlin.R
import com.ahsailabs.beritakita_kotlin.configs.Config
import com.ahsailabs.beritakita_kotlin.ui.home.adapters.NewsAdapter
import com.ahsailabs.beritakita_kotlin.ui.home.models.News
import com.ahsailabs.beritakita_kotlin.ui.home.models.NewsListResponse
import com.ahsailabs.beritakita_kotlin.utils.HttpUtil
import com.ahsailabs.beritakita_kotlin.utils.InfoUtil
import com.ahsailabs.beritakita_kotlin.utils.InfoUtil.showToast
import com.ahsailabs.beritakita_kotlin.utils.SwipeRefreshLayoutUtil
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.facebook.shimmer.ShimmerFrameLayout


class HomeFragment : Fragment() {
    private var rvList: RecyclerView? = null
    private var newsList: ArrayList<News>? = null
    private var newsAdapter: NewsAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var swipeRefreshLayoutUtil: SwipeRefreshLayoutUtil? = null
    private var sflLoading: ShimmerFrameLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        setupListener()
    }

    override fun onResume() {
        super.onResume()
        swipeRefreshLayoutUtil?.refreshNow()
    }

    private fun loadViews(root: View) {
        rvList = root.findViewById(R.id.rvList)
        swipeRefreshLayout = root.findViewById(R.id.srlHome)
        sflLoading = root.findViewById(R.id.sflLoading)
    }

    private fun setupViews(){
        rvList!!.layoutManager = LinearLayoutManager(activity)
        newsList = ArrayList()
        newsAdapter = NewsAdapter(newsList!!)
        rvList!!.adapter = newsAdapter

        swipeRefreshLayoutUtil = SwipeRefreshLayoutUtil.init(swipeRefreshLayout) { loadData() }
    }

    private fun setupListener() {
        //setup item click listener
        newsAdapter?.setOnChildViewClickListener(object : NewsAdapter.OnChildViewClickListener<News> {
            override fun onClick(view: View?, dataModel: News, position: Int) {
                InfoUtil.showSnackBar(view, "data ke-$position clicked")
                NewsDetailActivity.start(context!!, dataModel.id!!)
            }

            override fun onLongClick(view: View?, dataModel: News, position: Int) {

            }
        })
    }

    private fun showLoading() {
        rvList?.visibility = View.GONE
        sflLoading?.startShimmer()
        sflLoading?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        rvList?.visibility = View.VISIBLE
        sflLoading?.stopShimmer()
        sflLoading?.visibility = View.GONE
    }

    private fun loadData() {
        showLoading()
        AndroidNetworking.post(Config.newsListUrl)
            .setOkHttpClient(HttpUtil.getCLient(requireContext()))
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

                        swipeRefreshLayoutUtil?.refreshDone()
                        hideLoading()
                    }

                    override fun onError(anError: ANError) {
                        //TODO: show info error
                        showToast(activity, anError.message)

                        swipeRefreshLayoutUtil?.refreshDone()
                        hideLoading()
                    }
                })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home_action_refresh) {
            swipeRefreshLayoutUtil?.refreshNow()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        AndroidNetworking.cancel("newslist")
        super.onDestroyView()
    }
}