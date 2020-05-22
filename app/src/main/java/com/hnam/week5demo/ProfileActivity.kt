package com.hnam.week5demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getAndPutData()
    }

    private fun getAndPutData() {
        val data = intent.extras

        if (data != null) {
            val name = data.getString(ACCOUNT_NAME_KEY)
            val description = data.getString(ACCOUNT_DESCRIPTION_KEY)
            val avatar = data.getInt(ACCOUNT_AVATAR_KEY)


            tvDescription.text = name
            tvName.text = name
            tvName02.text = name
            tvDescription.text = description
            Glide.with(this)
                .load( avatar)
                .centerCrop()
                .placeholder(R.drawable.student_place_holder)
                .into(ivHeader)

        }
    }
}
