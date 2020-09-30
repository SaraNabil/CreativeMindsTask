package com.example.creativemindstask.Model

import com.example.creativemindstask.R

data class OrderTrip(
    var title: String,
    var tripTime: String,
    var gender: String,
    var tripType: String,
    var numOfPeople: String,
    var mark: Boolean,
    var image:Int
)

object Supplier {
    val orders = listOf<OrderTrip>(
        OrderTrip(
            "ابي اروح الهايبر وما عندي سيارة ممكن حد يوديني",
            "منذ ساعة",
            "رجال",
            "فوعة سيارة",
            "2 فزعو",
            true,
            R.drawable.type_car
        ),
        OrderTrip(
            "بنات ضروري عندي عزومة وابي احد يساعدني",
            "منذ ساعة",
            "سيدات",
            "فوعة اكل",
            "3 فزعو",
            false,
            R.drawable.type_food
        )
    )

    val others = listOf<OrderTrip>(
        OrderTrip(
            "بنات ضروري عندي عزومة وابي احد يساعدني",
            "منذ ساعة",
            "سيدات",
            "فوعة اكل",
            "3 فزعو",
            false,
            R.drawable.type_food
        ),
        OrderTrip(
            "ابي اروح الهايبر وما عندي سيارة ممكن حد يوديني",
            "منذ ساعة",
            "رجال",
            "فوعة سيارة",
            "2 فزعو",
            true,
            R.drawable.type_car
        ),
        OrderTrip(
            "بنات ضروري عندي عزومة وابي احد يساعدني",
            "منذ ساعة",
            "سيدات",
            "فوعة اكل",
            "3 فزعو",
            false,
            R.drawable.type_food
        ),
        OrderTrip(
            "ابي اروح الهايبر وما عندي سيارة ممكن حد يوديني",
            "منذ ساعة",
            "رجال",
            "فوعة سيارة",
            "2 فزعو",
            true,
            R.drawable.type_car
        )

    )
}