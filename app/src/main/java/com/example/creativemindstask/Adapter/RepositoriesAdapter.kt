package com.example.creativemindstask.Adapter

import RepositoriesModel
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.creativemindstask.Utils.AppUtilities
import com.example.creativemindstask.R
import kotlinx.android.synthetic.main.repo_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class RepositoriesAdapter(
    private val context: Context,
    private val repos: ArrayList<RepositoriesModel>?
) :
    RecyclerView.Adapter<RepositoriesAdapter.MyViewHolder>(), Filterable {

    private var filteredRepo: ArrayList<RepositoriesModel>? = repos
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (filteredRepo == null)
            return 0

        return filteredRepo!!.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repo = filteredRepo?.get(position)
        holder.setData(repo, position)
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charString: String = constraint.toString()
//                var filteredList: ArrayList<RepositoriesModel>? = ArrayList()
//                filteredRepo = if (charString.isEmpty()) {
//                    repos
//                } else {
//                    if (repos != null) {
//                        for (row in repos) {
//                            if (row.name.toLowerCase(Locale.getDefault()).contains(
//                                    charString.toLowerCase(
//                                        Locale.getDefault()
//                                    )
//                                )
//                            ) {
//                                filteredRepo?.add(row)
//                            }
//                        }
//                    }
//                    filteredRepo
//                }
//
//                val filterResults = FilterResults()
//                filterResults.values = filteredRepo
//                return filterResults
//            }
//
//            override fun publishResults(
//                charSequence: CharSequence?,
//                filterResults: FilterResults?
//            ) {
//                // TODO: crash while typing in edittext
//                filteredRepo = filterResults?.values as ArrayList<RepositoriesModel>?
//                notifyDataSetChanged()
//            }
//
//        }
//
//    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun setData(repo: RepositoriesModel?, pos: Int) {
            itemView.repoNameTv.text = repo!!.name
            itemView.repoDescTv.text = repo.description
            itemView.repoFullNameTv.text = repo.full_name
            if (repo.fork) itemView.container.setBackgroundColor(context.getColor(android.R.color.white))
            else itemView.container.setBackgroundColor(context.getColor(android.R.color.holo_green_light))

            itemView.container.setOnLongClickListener {
                val builder =
                    AlertDialog.Builder(context)
                builder.setMessage(context.getString(R.string.repo_dialog_message))
                builder.setPositiveButton(
                    context.getString(R.string.repository_url)
                ) { dialog: DialogInterface?, which: Int ->
                    AppUtilities.openURL(
                        context,
                        repo.html_url
                    )
                }
                builder.setNegativeButton(
                    context.getString(R.string.owner_url)
                ) { dialog: DialogInterface?, id: Int ->
                    AppUtilities.openURL(
                        context,
                        repo.owner.html_url
                    )
                }
                builder.show()
                return@setOnLongClickListener false
            }

            // hide the divider between items in last item
            if (pos == itemCount - 1) itemView.dividerLine.visibility =
                View.GONE else itemView.dividerLine.visibility =
                View.VISIBLE
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredRepo = repos
                } else {
                    val resultList = ArrayList<RepositoriesModel>()
                    if (repos != null) {
                        for (row in repos) {
                            if (row.name.toLowerCase(Locale.ROOT)
                                    .contains(charSearch.toLowerCase(Locale.ROOT))
                            ) {
                                resultList.add(row)
                            }
                        }
                    }
                    filteredRepo = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredRepo
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredRepo = results?.values as ArrayList<RepositoriesModel>
                notifyDataSetChanged()
            }

        }
    }

}