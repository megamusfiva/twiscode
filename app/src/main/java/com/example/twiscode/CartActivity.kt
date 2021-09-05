package com.example.twiscode

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.room.Cart
import com.example.twiscode.room.MyDatabase

class CartActivity : AppCompatActivity() {

    lateinit var myDb: MyDatabase
    lateinit var s: SharedPref
    lateinit var rvProduk: RecyclerView
    lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        myDb = MyDatabase.getInstance(this)!!
        s = SharedPref(this)
        rvProduk = findViewById(R.id.rv_cart)
        tvTotal = findViewById(R.id.tv_total)
    }
    lateinit var adapter: CartAdapter
    var listProduk = ArrayList<Cart>()

    private fun displayProduk() {
        listProduk = myDb.cartDao().getAll() as ArrayList
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter = CartAdapter(this, listProduk, object : CartAdapter.Listeners {
            override fun onUpdate() {
                //hitungTotal()
            }
        })
        rvProduk.adapter = adapter
        rvProduk.layoutManager = layoutManager
    }

    var totalHarga = 0


    override fun onResume() {
        displayProduk()
       // hitungTotal()
        super.onResume()
    }
}



