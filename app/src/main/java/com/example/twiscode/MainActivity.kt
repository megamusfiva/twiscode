package com.example.twiscode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.room.Cart
import com.example.twiscode.room.MyDatabase
import com.example.twiscode.service.ApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cart_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var tbMain: Toolbar
    private lateinit var rvItem: RecyclerView
    var responses: ArrayList<Responses>? = null
    lateinit var myDb: MyDatabase
    lateinit var cart: Cart
    lateinit var res: Responses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tbMain = findViewById(R.id.tb_main)
        setSupportActionBar(tbMain)
        supportActionBar?.elevation = 0f
        responses = ArrayList()
        getData()
        rvItem = findViewById(R.id.rv_item)
        rvItem.setHasFixedSize(true)
        myDb = MyDatabase.getInstance(this)!!
    }

    private fun getData() {
        pb_main.visibility = View.VISIBLE
        val apiService = ApiClient.getApiService()
        apiService.getList().enqueue(object : Callback<ArrayList<Responses>> {
            override fun onResponse(
                call: Call<ArrayList<Responses>>,
                response: Response<ArrayList<Responses>>
            ) {
                pb_main.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    responses = response.body()
                    val adapter = responses?.let {
                        ItemAdapter(it, this@MainActivity)
                    }
                    rvItem.adapter = adapter
                    rvItem.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<Responses>>, t: Throwable) {
                pb_main.visibility = View.INVISIBLE
                Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun insert() {
        CompositeDisposable().add(Observable.fromCallable { myDb.cartDao().insert(cart) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkKeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Berhasil menambah kekeranjang", Toast.LENGTH_SHORT).show()
            })
    }
    private fun update(cart: Cart) {
        CompositeDisposable().add(Observable.fromCallable { myDb.cartDao().update(cart) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkKeranjang()
                Log.d("respons", "data inserted")
                Toast.makeText(this, "Berhasil menambah kekeranjang", Toast.LENGTH_SHORT).show()
            })
    }

    private fun checkKeranjang() {
        val dataKranjang = myDb.cartDao().getAll()

        if (dataKranjang.isNotEmpty()) {
            count_tv.visibility = View.VISIBLE
            count_tv.text = dataKranjang.size.toString()
        } else {
            count_tv.visibility = View.GONE
        }
    }

        fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            cart= Cart()
            insert()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val menuItem = menu.findItem(R.id.cart)
        val view = menuItem.actionView
        view.setOnClickListener {
            val intent = Intent(this@MainActivity, CartActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}