package com.example.msptask4.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.msptask4.Data.User
import com.example.msptask4.R

class Adapter(
    var homes:List<User>
):RecyclerView.Adapter<Adapter.ViewHolder>() {
    private lateinit var mListener: onItemCliclListener

    interface onItemCliclListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemCliclListener) {
        mListener = listener
    }

    inner class ViewHolder(itemView: View, listener: onItemCliclListener) :
        RecyclerView.ViewHolder(itemView) {
        var email: TextView = itemView.findViewById(R.id.tv1nitem)
        var phoneNumber: TextView = itemView.findViewById(R.id.tv2initem)
        var id: TextView = itemView.findViewById(R.id.tv3initem)
        var password: TextView = itemView.findViewById(R.id.tv4initem)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.iteminrec, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.email.text = homes.get(position).email
        holder.phoneNumber.text = homes.get(position).phoneNumber.toString()
        holder.id.text = homes.get(position).id.toString()
        holder.password.text = homes.get(position).password
    }


    override fun getItemCount(): Int {
        return homes.size
    }
}
