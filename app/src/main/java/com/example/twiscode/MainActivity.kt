package com.example.twiscode

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twiscode.service.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var tbMain : Toolbar
    private lateinit var rvItem : RecyclerView
    var responses: ArrayList<Responses>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tbMain = findViewById(R.id.tb_main)
        setSupportActionBar(tbMain)
        supportActionBar?.elevation = 0f
        responses  = ArrayList()
        getData()
        rvItem = findViewById(R.id.rv_item)
        rvItem.setHasFixedSize(true)
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
                        ItemAdapter(this@MainActivity, it) }
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart -> {
                val intent = Intent(this@MainActivity, CartActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}