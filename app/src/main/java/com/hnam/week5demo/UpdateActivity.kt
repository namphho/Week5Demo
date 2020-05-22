package com.hnam.week5demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hnam.week5demo.room.AppDatabase
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        db = AppDatabase.invoke(this)


        btnSave.setOnClickListener {
            handleOnBtnSaveClicked()
        }
    }

    private fun handleOnBtnSaveClicked() {
       // TODO update and put sutdent object backto main screen

//        db.studentDAO().update()

    }
}
