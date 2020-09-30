package com.example.creativemindstask.Activity

import RepositoriesModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.creativemindstask.Adapter.RepositoriesAdapter
import com.example.creativemindstask.R
import com.example.creativemindstask.Network.ServiceBuilder
import com.example.creativemindstask.Utils.CacheUtils
import com.example.creativemindstask.Utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_developing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DevelopingActivity : AppCompatActivity() {


    private val PAGE_SIZE = 10
    private val ACCESS_TOKEN = "d10a496879e523a4a6586630e4f62ad0f372250f"

    lateinit var repoAdapter: RepositoriesAdapter
    var repos: ArrayList<RepositoriesModel>? = null
    lateinit var layoutManager: LinearLayoutManager

    lateinit var scroller: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developing)
        repos = ArrayList()

        layoutManager = LinearLayoutManager(
            this@DevelopingActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        // init scroll listener for infinite scrolling
        scroller = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (page > 1) getListOfReposWithCache(page)
            }
        }
        // init adapter
        repoAdapter = RepositoriesAdapter(this@DevelopingActivity, repos)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(scroller)
        recyclerView.adapter = repoAdapter

        filterEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                repoAdapter.filter.filter(s.toString())
            }
        })
        swipeToRefresh.setOnRefreshListener {
            CacheUtils.deleteCache(this)

            // clear all data so list become have 10 items only after api call is done
            repos!!.clear()

            // re-init the list of data
            scroller.resetState()

            // to remove filter while refreshing the data
            filterEdit.setText("")

            getListOfReposWithCache(1)
        }
        getListOfReposWithCache(1)
    }

    private fun getListOfReposWithCache(pageNumber: Int) {

        progressBar.smoothToShow();
        val request = ServiceBuilder.getInstance()?.getApi()
        val call = request?.getListOfRepositories(pageNumber, PAGE_SIZE, ACCESS_TOKEN)

        call?.enqueue(object : Callback<List<RepositoriesModel>?> {
            override fun onResponse(
                call: Call<List<RepositoriesModel>?>,
                response: Response<List<RepositoriesModel>?>
            ) {
                if (response.isSuccessful) {
                    progressBar.smoothToHide();
                    swipeToRefresh.isRefreshing = false

                    if (response.raw().networkResponse != null) {
                        // from network
                        Log.e("RETROFIT RESPONSE =>", "from network")
                    } else if (response.raw().cacheResponse != null) {
                        // from cache
                        Log.e("RETROFIT RESPONSE =>", "from cache")

                    }
                    response.body()?.let { repos!!.addAll(it) }

                    repoAdapter.notifyItemRangeInserted(
                        repoAdapter.itemCount,
                        response.body()!!.size
                    )

                }
            }

            override fun onFailure(call: Call<List<RepositoriesModel>?>, t: Throwable) {
                progressBar.smoothToHide();
                Toast.makeText(this@DevelopingActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}