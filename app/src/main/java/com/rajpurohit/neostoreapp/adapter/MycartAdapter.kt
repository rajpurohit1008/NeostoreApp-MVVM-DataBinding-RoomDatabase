package com.rajpurohit.neostoreapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajpurohit.neostoreapp.R
import com.rajpurohit.neostoreapp.model.mycartmodel.MycartData
import com.rajpurohit.neostoreapp.model.mycartmodel.Mycartcurd
import com.rajpurohit.neostoreapp.network.ApiService
import com.rajpurohit.neostoreapp.viewactivity.MycartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MycartAdapter(val context: Context,val dataList:List<MycartData>?,val token:String?) :RecyclerView.Adapter<MycartAdapter.MycartVH>(){
 

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MycartVH {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.mycart_list2,parent,false)
      return MycartVH((view))
    }



    override fun onBindViewHolder(holder: MycartVH, position: Int) {
        val currentItem = dataList?.get(position)?.product
        holder.mycarttitle.text = currentItem?.name.toString()
        holder.mycartcategory.text = currentItem?.product_category.toString()
        holder.mycartcost.text = java.lang.String.valueOf("₹ ${currentItem?.cost.toString()}")
        Glide.with(context).load(currentItem?.product_images).centerCrop().into(holder.mycartImage)
        holder.deletemycartImage.setOnClickListener(){
            val callDelete = ApiService.myCartCURD().deleteMycart(token,currentItem?.id)
            callDelete.enqueue(object : Callback<Mycartcurd> {
                override fun onResponse(call: Call<Mycartcurd>, response: Response<Mycartcurd>) {
                    Toast.makeText(context, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<Mycartcurd>, t: Throwable) {
                    Toast.makeText(context, "something wrong mycart delete $t", Toast.LENGTH_SHORT).show()
                }
            })
            val intent = Intent(context,MycartActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
        val adapter = ArrayAdapter.createFromResource(context,R.array.numbers,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.quantitymycartSpinner?.adapter = adapter
       holder.quantitymycartSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
           override fun onNothingSelected(parent: AdapterView<*>?) {

           }

           override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               val quantityvalue = parent?.getItemAtPosition(position).toString()
               val callEditQuantity = ApiService.myCartCURD().editMycart(token,currentItem?.id,quantityvalue.toInt())
               callEditQuantity.enqueue(object : Callback<Mycartcurd> {
                   override fun onResponse(call: Call<Mycartcurd>, response: Response<Mycartcurd>) {
                       Toast.makeText(context, "${response.body()?.userMsg}", Toast.LENGTH_SHORT).show()
                   }

                   override fun onFailure(call: Call<Mycartcurd>, t: Throwable) {
                       Toast.makeText(context, "something wrong spinner $t", Toast.LENGTH_SHORT).show()
                   }
               })


           }

       }
    }

    override fun getItemCount(): Int = dataList!!.size
    inner class MycartVH(itemView: View):RecyclerView.ViewHolder(itemView){
        val mycarttitle: TextView = itemView.findViewById(R.id.mycart_title)
        val mycartcategory: TextView = itemView.findViewById(R.id.mycart_category)
        val mycartcost: TextView = itemView.findViewById(R.id.mycart_cost)
        val mycartImage:ImageView = itemView.findViewById(R.id.imageView_mycart)
        val deletemycartImage:ImageButton = itemView.findViewById(R.id.mycart_delete)
        val quantitymycartSpinner:Spinner?= itemView.findViewById(R.id.mycart_spinner)
        val mycontext = itemView.context
    }
}