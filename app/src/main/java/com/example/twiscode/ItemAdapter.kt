package com.example.twiscode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.card_item.view.*

class ItemAdapter(private val list: ArrayList<Responses>, private val listener: MainActivity) :
    RecyclerView.Adapter<ItemAdapter.ListViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
            val view: View =
                LayoutInflater.from(viewGroup.context).inflate(R.layout.card_item, viewGroup, false)
            return ListViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val response = list[position]
            Glide.with(holder.itemView.context)
                .load(R.drawable.dummy)
                .apply(RequestOptions().override(250, 250))
                .into(holder.imgPhoto)
            holder.tvName.text = response.title
            holder.tvUser.text = response.user?.userName
            holder.tvPrice.text = response.price
            holder.tvCity.text = response.locationName
            val ivHalal = response.isHalal
            if (ivHalal==1)
                holder.itemView.iv_halal.visibility = View.VISIBLE
            else
                holder.itemView.iv_halal.visibility = View.GONE

            val cvStock = response.isAvailable
            if (cvStock == 1)
                holder.itemView.cv_stock.visibility = View.VISIBLE
            else
                holder.itemView.cv_stock.visibility = View.GONE
            holder.itemView.setOnClickListener {
                listener.onItemClick(null,holder.itemView, position, holder.itemId)
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvName: TextView = itemView.findViewById(R.id.tv_name)
            val tvUser: TextView = itemView.findViewById(R.id.tv_user)
            val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
            val tvCity: TextView = itemView.findViewById(R.id.tv_city)
            val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo)
        }
    }