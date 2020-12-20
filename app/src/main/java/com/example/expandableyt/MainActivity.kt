package com.example.expandableyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val arrayList = arrayListOf<ExpandableAdapter>()

        val groupedItemList = Repository.createGroupedItemList(20)

        for (groupedItem in groupedItemList) {
            val adapter = ExpandableAdapter(groupedItem)
            arrayList.add(adapter)
        }

        val concatAdapterConfig = ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build()

        val concatAdapter = ConcatAdapter(concatAdapterConfig, arrayList)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        recyclerView.adapter = concatAdapter
    }
}