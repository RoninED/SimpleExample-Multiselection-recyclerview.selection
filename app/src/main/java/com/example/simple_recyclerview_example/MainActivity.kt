package com.example.simple_recyclerview_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        var items = arrayOf("111111111111111111", "22222222222222222", "3333333333333333333", "44444444444444", "55555555555555", "5566666666666666", "777777777777777", "88888888888888888", "999999999999", "1000000000000000000000")

        var myadapter = MyAdapter(items)

        var myrecyclerView = findViewById<RecyclerView>(R.id.my_recyclerView)

        myrecyclerView.adapter = myadapter





    }
}
