package com.example.nogizaka5th

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nogizaka5th.adapter.ListMemberAdapter
import com.example.nogizaka5th.model.Member

class MainActivity : AppCompatActivity() {
    private lateinit var rvNogi: RecyclerView
    private val list = ArrayList<Member>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNogi = findViewById(R.id.rv_nogi)
        rvNogi.setHasFixedSize(true)

        list.addAll(getListMember())
        showRecyclerList()

    }
    private fun showDialog(){
        val view = View.inflate(this@MainActivity, R.layout.layout_dialog, null)

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val btn = view.findViewById<Button>(R.id.btnOk)
        btn.setOnClickListener {
            dialog.dismiss()
        }
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val displayWidth = displayMetrics.widthPixels
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        val dialogWindowWidth = (displayWidth * 0.96f).toInt()
        layoutParams.width = dialogWindowWidth
        dialog.window!!.attributes = layoutParams
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.about_page -> {
            startActivity(Intent(this, AboutActivity::class.java))
            true
        }
        R.id.menuInfo -> {
            showDialog()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    private fun getListMember():ArrayList<Member>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataCity = resources.getStringArray(R.array.data_city)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
val listMember = ArrayList<Member>()
        for (i in dataName.indices){
            val member = Member(dataName[i],dataCity[i],dataPhoto.getResourceId(i,-1))
            listMember.add(member)

        }
        return listMember
    }
    private fun showSelectedMember(member: Member) {
        Toast.makeText(this, "Kamu memilih " + member.name, Toast.LENGTH_SHORT).show()
    }


    private fun showRecyclerList(){
        rvNogi.layoutManager = LinearLayoutManager(this)
        val listMemberAdapter = ListMemberAdapter(list)
        rvNogi.adapter = listMemberAdapter

        listMemberAdapter.setOnItemClickCallback(object : ListMemberAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Member) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)

                intent.putExtra("EXTRA_NAME", data.name)
                intent.putExtra("EXTRA_CITY", data.city)
                intent.putExtra("EXTRA_PHOTO", data.photo)
                startActivity(intent)
            }



})

}}