package com.example.clickstask.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.clickstask.R
import com.example.clickstask.pojo.NewsModel
import kotlinx.android.synthetic.main.card_item.view.*

class NewsAdapter(private val activity: FragmentActivity, var newsList : MutableList<NewsModel>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
        return NewsViewHolder(view).listen { position->
            val newsItem = newsList!![position]
            val bundle = Bundle()
            bundle.putString("title",newsItem.title)
            bundle.putString("imageURL",newsItem.imageURL)
            bundle.putString("des",newsItem.description)
            activity.findNavController(R.id.nav_host_fragment_container).navigate(R.id.action_homeFragment_to_itemDetailsFragment,bundle)
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (newsList!!.isNotEmpty()){
            val newsItem = newsList!![position]
            holder.onBind(newsItem)
        }
    }



    override fun getItemCount(): Int {
        if (newsList!!.isNotEmpty())
            return newsList!!.size
        else
            return 0
    }

    fun updateList(newList: MutableList<NewsModel>){
        newsList = newList
        this!!.notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun onBind(newsItem:NewsModel){
                itemView.item_title_txt.text = newsItem.title
                itemView.item_source_txt.text = newsItem.sourceData.name
            if (!newsItem.imageURL.isNullOrEmpty())
                Glide.with(itemView)
                    .load(newsItem.imageURL)
                    .transform(CenterCrop())
                    .into(itemView.item_image)
            else
                itemView.item_image.setImageResource(R.drawable.no_image_available)
        }
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition)
        }
        return this
    }

}