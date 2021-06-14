package com.rajpurohit.neostoreapp.viewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.adapter.TableAdapter
import com.rajpurohit.neostoreapp.model.TableModel
import com.rajpurohit.neostoreapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TableActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        recyclerView =findViewById(R.id.recycleview)
        recyclerView.layoutManager = LinearLayoutManager(this)
      //  recyclerView.adapter = TableAdapter(this)
        //Toast.makeText(this, "ider tak chal raha h", Toast.LENGTH_SHORT).show()
     val tableData = ApiService.getTableProductList()
   val tableCall =   tableData?.getTableProduct("1",10,1)
        val accesstokentable = intent.getStringExtra("tokenhome")
       // Toast.makeText(this, "$accesstokentable", Toast.LENGTH_SHORT).show()
      tableCall?.enqueue(object : Callback<TableModel> {
          override fun onResponse(call: Call<TableModel>, response: Response<TableModel>) {
              Toast.makeText(applicationContext, "successfull", Toast.LENGTH_SHORT).show()
                  if (response.isSuccessful) {
                      val tableDatalist = response.body()
                      val datalist = tableDatalist?.data
                      recyclerView.adapter = datalist?.let { TableAdapter(applicationContext, it,accesstokentable) }
                  }
          }

          override fun onFailure(call: Call<TableModel>, t: Throwable) {
              Toast.makeText(applicationContext, "Something wrong $t ", Toast.LENGTH_LONG).show()
          }
      })

    }

    fun onClickTableBackicon(view: View) {
        onBackPressed()
    }



}