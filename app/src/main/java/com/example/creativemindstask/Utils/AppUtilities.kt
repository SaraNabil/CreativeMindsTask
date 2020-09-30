package com.example.creativemindstask.Utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object AppUtilities {
    fun openURL(context: Context, websiteUrl: String) {
        var url = websiteUrl
        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://$url"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(Intent.createChooser(browserIntent, "Open with"))
    }
}