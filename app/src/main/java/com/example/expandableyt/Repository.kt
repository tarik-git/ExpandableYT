package com.example.expandableyt

import kotlin.random.Random

object Repository {

    fun createGroupedItemList(headerNumber: Int): List<GroupedItem> {
        val groupedItemList = arrayListOf<GroupedItem>()
        for (i in 0 until headerNumber) {
            val headerText = "Header $i"
            val header = GroupedItem.Header(headerText)
            val itemList = arrayListOf<GroupedItem.Item>()
            val indexNumber = Random.nextInt(2, 5)
            for (j in 0 until indexNumber) {
                val itemText = "Item $j"
                itemList.add(GroupedItem.Item(itemText))
            }
            val groupedItem = GroupedItem(header, itemList)
            groupedItemList.add(groupedItem)
        }
        return groupedItemList
    }

}