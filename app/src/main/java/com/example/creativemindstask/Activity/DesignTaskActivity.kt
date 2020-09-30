package com.example.creativemindstask.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.creativemindstask.Fragment.OrdersFragment
import com.example.creativemindstask.Fragment.OthersFragment
import com.example.creativemindstask.R
import kotlinx.android.synthetic.main.activity_design_task.*

class DesignTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_task)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    OrdersFragment(1)
                )
                .commitAllowingStateLoss()
            ordersBtn.setBackgroundResource(R.drawable.ovel_shape_white)
            othersBtn.setTextColor(resources.getColor(R.color.darkGray))
            ordersBtn.setTextColor(resources.getColor(android.R.color.black))
        }


        othersBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    OthersFragment(2)
                )
                .commitAllowingStateLoss()
            ordersBtn.setBackgroundResource(0)
            othersBtn.setBackgroundResource(R.drawable.ovel_shape_white)
            othersBtn.setTextColor(resources.getColor(android.R.color.black))
            ordersBtn.setTextColor(resources.getColor(R.color.darkGray))

        }

        ordersBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    OrdersFragment(1)
                )
                .commitAllowingStateLoss()
            ordersBtn.setBackgroundResource(R.drawable.ovel_shape_white)
            othersBtn.setBackgroundResource(0)
            othersBtn.setTextColor(resources.getColor(R.color.darkGray))
            ordersBtn.setTextColor(resources.getColor(android.R.color.black))
        }

        //close activity
        backBtn.setOnClickListener {
            finish()
        }
    }

}