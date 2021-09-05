package com.example.twiscode

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twiscode.room.Cart
import com.example.twiscode.room.MyDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.card_item.view.*

class CartAdapter (private var activity: Activity, private var data: ArrayList<Cart>, private var listener: Listeners) : RecyclerView.Adapter<CartAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CartAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.cart_item, viewGroup, false)
        return ListViewHolder(view)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    override fun onBindViewHolder(holder: CartAdapter.ListViewHolder, position: Int) {
        val cart = data[position]
        val harga = cart.price?.let { Integer.valueOf(it) }
        holder.tvName.text = cart.title
        holder.tvPrice.text = cart.price.toString()
        val weightt = cart.weight
        if (weightt == "")
            holder.lWeight.visibility = View.GONE
        else
            holder.lWeight.visibility = View.VISIBLE
            holder.tvWeight.text =cart.weight
        Glide.with(holder.itemView.context)
            .load(R.drawable.dummy)
            .apply(RequestOptions().override(250, 250))
            .into(holder.imgPhoto)

        var jumlah = data[position].jumlah
        holder.tvQuantity.text = 1.toString()

        holder.btnTambah.setOnClickListener {
            jumlah++
            cart.jumlah = jumlah
            update(cart)

            holder.tvQuantity.text = jumlah.toString()
            if (harga != null) {
                holder.tvPrice.text = ((harga * jumlah).toString())
            }
        }

        holder.btnKurang.setOnClickListener {
            if (jumlah <= 1) return@setOnClickListener
            jumlah--

            cart.jumlah = jumlah
            update(cart)

            holder.tvQuantity.text = jumlah.toString()
            if (harga != null) {
                holder.tvPrice.text = (harga * jumlah).toString()
            }
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        val tvWeight: TextView = itemView.findViewById(R.id.tv_weight)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo)
        val btnKurang: ImageView = itemView.findViewById(R.id.btn_minus)
        val btnTambah: ImageView = itemView.findViewById(R.id.btn_add)
        val lWeight: LinearLayout = itemView.findViewById(R.id.weight)
    }
    interface Listeners {
        fun onUpdate()
    }

    private fun update(data: Cart) {
        val myDb = MyDatabase.getInstance(activity)
        CompositeDisposable().add(Observable.fromCallable { myDb!!.cartDao().update(data) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listener.onUpdate()
            })
    }
}