package com.example.creativemindstask.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creativemindstask.R
import com.example.creativemindstask.Model.Supplier
import com.example.creativemindstask.Adapter.TripOrdersAdapter
import kotlinx.android.synthetic.main.fragment_waiting.view.*


class CancelledFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cancelled, container, false)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.recyclerView.layoutManager = layoutManager
        val tripOrderAdapter =
            TripOrdersAdapter(
                activity!!,
                Supplier.orders,
                false
            )
        view.recyclerView.adapter = tripOrderAdapter
        return view
    }

}