package com.hnam.week5demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule
import android.app.Activity
import com.hnam.week5demo.room.AppDatabase
import com.hnam.week5demo.room.Account
import com.hnam.week5demo.room.AccountDAO


class MainActivity : AppCompatActivity() {

    var studentsDataSet: ArrayList<Account> = ArrayList()
    private lateinit var studentAdapter: StudentAdapter
    lateinit var dao: AccountDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRoomDatabase()

        setupRecyclerView()

        getStudents()


        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddAccountActivity::class.java)
            startActivityForResult(intent, CODE_ADD_NEW_ACCOUNT)
        }

        fab.setOnLongClickListener {
            dao.deleteAll()
            val size = studentsDataSet.size
            if (size > 0) {
                for (i in 0 until size) {
                    studentsDataSet.removeAt(0)
                }

                studentAdapter.notifyItemRangeRemoved(0, size)
            }
            return@setOnLongClickListener false
        }

    }

    private fun initRoomDatabase() {
        val db = AppDatabase.invoke(this)
        dao = db.accountDAO()
    }

    /**
     * setup layout manager and recyclerview
     */
    private fun setupRecyclerView() {
        rvStudents.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            this
        ) as androidx.recyclerview.widget.RecyclerView.LayoutManager?

        studentAdapter = StudentAdapter(studentsDataSet, this)
        rvStudents.adapter = studentAdapter
        studentAdapter.setListener(studentItemCLickListener)
    }

    private val studentItemCLickListener = object : StudentItemCLickListener {
        override fun onItemCLicked(position: Int) {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            intent.putExtra(ACCOUNT_NAME_KEY, studentsDataSet[position].name)
            intent.putExtra(ACCOUNT_AVATAR_KEY, studentsDataSet[position].avatar)
            intent.putExtra(ACCOUNT_DESCRIPTION_KEY, studentsDataSet[position].description)
            startActivity(intent)
        }

        override fun onItemLongCLicked(position: Int) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Confirmation")
                .setMessage("Remove ${studentsDataSet[position].name} ?")
                .setPositiveButton("OK") { _, _ ->
                    removeItem(position)
                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, _ -> dialog?.dismiss() }

            val myDialog = builder.create();
            myDialog.show()
        }

        override fun onEditIconClicked(position: Int) {

        }
    }

    private fun removeItem(position: Int) {
        dao.delete(studentsDataSet[position]) // remove from Room database  //
        studentsDataSet.removeAt(position) // remove student list on RAM
        studentAdapter.notifyItemRemoved(position) // notify data change
        Timer(false).schedule(500) {
            runOnUiThread {
                studentAdapter.setData(studentsDataSet)
                studentAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun getStudents() {
        val students = dao.getAll() // get Students from ROOM database
        this.studentsDataSet.addAll(students) // add to student list
        studentAdapter.notifyDataSetChanged() // notify data changed
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_ADD_NEW_ACCOUNT && resultCode == Activity.RESULT_OK) {
            val newStudentAdded = data?.extras?.getParcelable(ACCOUNT_OBJECT_KEY) as? Account
            newStudentAdded?.let { handleOnNewStudentAdded(it) }
        }
        if (requestCode == CODE_ADD_UPDATE_ACCOUNT && resultCode == Activity.RESULT_OK) {
            val newStudentAdded = data?.extras?.getParcelable(ACCOUNT_OBJECT_KEY) as? Account
            newStudentAdded?.let { handleOnStudentUpdated(it) }
        }
    }

    /**
     * Update the item
     */
    private fun handleOnStudentUpdated(newStudentAdded: Account) {
        // TODO : Update the updated item information
    }

    /**
     * append new data to student list and notify data change
     */
    private fun handleOnNewStudentAdded(student: Account) {
        studentAdapter.appendData(student)
    }

}




