package com.hnam.week5demo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.hnam.week5demo.room.AppDatabase
import com.hnam.week5demo.room.Account
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlin.collections.ArrayList


class AddAccountActivity : AppCompatActivity() {

    val spinnerData = ArrayList<Pair<String, Int>>()

    private var account = Account()
    private var avatar = -1

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        db = AppDatabase.invoke(this) // get Room database instance

        setupSpinner()
        handleSubmitData()

    }

    private fun handleSubmitData() {
        btnSubmit.setOnClickListener {
            account.name = tvName.text.toString()
            account.description = tvDescription.text.toString()
            account.avatar = avatar

            val studentDAO = db.accountDAO() // get DAO instance
            val id = studentDAO.insert(account) // insert our student object to ROOM database

            account.id = id.toInt()

            /**
             * send inserted student-object to main screen when insert database successful
             */
            val intent = Intent()
            intent.putExtra(ACCOUNT_OBJECT_KEY, account)
            setResult(Activity.RESULT_OK, intent);
            finish()
        }
    }

    private fun setupSpinner() {
        spinnerData.add(Pair("[---Select Coach--]", R.drawable.student_place_holder))
        spinnerData.add(Pair("Tien Dung", R.drawable.buitiendung))
        spinnerData.add(Pair("Cong Phuong", R.drawable.congphuong))
        spinnerData.add(Pair("Anh Duc", R.drawable.anhduc))
        spinnerData.add(Pair("Cong Vinh", R.drawable.conhvinh))
        spinnerData.add(Pair("Dinh Manh Ninh", R.drawable.dmninh))
        spinnerData.add(Pair("Duy Manh", R.drawable.duymanh))
        spinnerData.add(Pair("Huynh Duc", R.drawable.huynhduc))
        spinnerData.add(Pair("Quang Hai", R.drawable.quanghai))
        spinnerData.add(Pair("Van Toan", R.drawable.vantoan))
        spinnerData.add(Pair("Vinh Rau", R.drawable.vinhrau)) // clone the data

        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData.map { it.first })
        spiner.adapter = spinnerAdapter
        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                avatar = spinnerData[position].second
                Glide.with(this@AddAccountActivity)
                    .load(spinnerData[position].second)
                    .centerCrop()
                    .into(ivFan)
            }
        }
    }

}
