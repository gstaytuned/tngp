package com.staytuned.mo.tngptutorial

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.staytuned.mo.tngptutorial.networking.RedditNewsResponse
import com.staytuned.mo.tngptutorial.networking.RestAPI
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    val list: ArrayList<RedditNewsResponse.RedditChildrenResponse> = ArrayList()

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_main,
                container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadService()
        loadServicePost()
        rv.adapter = MyAdapter(list, activity)
        rv.layoutManager = LinearLayoutManager(activity)
    }

    private fun loadServicePost() {
        var body: MutableMap<String, String> = mutableMapOf()
        body.put("test", "test2")
     //   RestAPI().create().saveData(body).enqueue(object  : Callback<>)
    }

    private fun loadService() {
        RestAPI().create().getTop("", "10").enqueue(object : Callback<RedditNewsResponse> {
            override fun onResponse(call: Call<RedditNewsResponse>?, response: Response<RedditNewsResponse>?) {
                if (response!!.isSuccessful) {
                    list.addAll(response.body()!!.data.children)
                    rv.adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<RedditNewsResponse>?, t: Throwable?) {

            }

        })
    }
}