package com.example.twiscode

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.card_item.view.*

class ItemAdapter(private val context: Activity?,
                  private val responses: ArrayList<Responses>
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.card_item, parent,
                false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        responses[position].let { holder.bindItem(it) }
    }
    override fun getItemCount(): Int = responses.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(response: Responses) {
            Glide.with(itemView.context)
                .load(R.drawable.dummy)
                .apply(RequestOptions().override(250, 250))
                .into(itemView.img_photo)
            itemView.tv_name.text = response.title
            itemView.tv_user.text = response.user?.userName
            itemView.tv_price.text = response.price
            itemView.tv_city.text = response.locationName
            val ivHalal = response.isHalal
            if (ivHalal==1)
                itemView.iv_halal.visibility = View.VISIBLE
            else
                itemView.iv_halal.visibility = View.GONE

            val cvStock = response.isAvailable
            if (cvStock == 1)
                itemView.cv_stock.visibility = View.VISIBLE
            else
                itemView.cv_stock.visibility = View.GONE

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MoviesDetailActivity::class.java)
                intent.putExtra(MoviesDetailActivity.EXTRA_ID,movies.moviesId)
                intent.putExtra(MoviesDetailActivity.EXTRA_MOVIES, movies)
                itemView.context.startActivity(intent)
            }
        }

    }
}