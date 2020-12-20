package com.example.expandableyt

class GroupedItem(
    val header: Header,
    val itemList: List<Item>
) {

    class Header(
        val headerText: String
    )

    class Item(
        val itemText: String
    )
}