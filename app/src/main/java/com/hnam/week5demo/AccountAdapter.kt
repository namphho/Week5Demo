package com.hnam.week5demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hnam.week5demo.room.Account
import kotlinx.android.synthetic.main.item_student.view.*
import java.util.*

/**
 * Created by Huu Hoang on 4/17/19.
 */
class StudentAdapter(private var items: ArrayList<Account>, private val context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<AccountVH>() {

    private lateinit var mListener: StudentItemCLickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AccountVH {
        return AccountVH(LayoutInflater.from(context).inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(accountVH: AccountVH, position: Int) {
        accountVH.tvName.text = "#$position ${items[position].name}"
        accountVH.tvClass.text = items[position].description
        Glide.with(context)
            .load(items[position].avatar)
            .centerCrop()
            .placeholder(R.drawable.student_place_holder)
            .into(accountVH.ivAvatar)


        accountVH.itemView.setOnClickListener {
            mListener.onItemCLicked(position)
        }

        accountVH.itemView.setOnLongClickListener {
            mListener.onItemLongCLicked(position)
            true
        }
    }

    fun setListener(listener: StudentItemCLickListener) {
        this.mListener = listener
    }

    fun setData(items: ArrayList<Account>) {
        this.items = items
    }

    fun appendData(newStudentAdded: Account) {
        this.items.add(newStudentAdded)
        notifyItemInserted(items.size - 1)
    }

}

class AccountVH(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    var tvName = view.tvName
    var tvClass = view.tvDescription
    var ivAvatar = view.ivAvatar
}

interface StudentItemCLickListener {
    fun onItemCLicked(position: Int)
    fun onItemLongCLicked(position: Int)
    fun onEditIconClicked(position: Int)
}
