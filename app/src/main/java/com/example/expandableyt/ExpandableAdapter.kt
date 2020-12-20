package com.example.expandableyt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpandableAdapter(private val groupedItem: GroupedItem)
    : RecyclerView.Adapter<ExpandableAdapter.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }

    private var isExpanded: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                ViewHolder.HeaderViewHolder(
                    layoutInflater.inflate(R.layout.expandable_header, parent, false)
                )
            }
            else -> {
                ViewHolder.ItemViewHolder(
                    layoutInflater.inflate(R.layout.expandable_item, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder.HeaderViewHolder -> {
                holder.onBind(groupedItem.header, onHeaderClicked())
            }
            is ViewHolder.ItemViewHolder -> {
                holder.onBind(groupedItem.itemList[position - 1])
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isExpanded) {
            groupedItem.itemList.size + 1
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    private fun onHeaderClicked() = object : View.OnClickListener {
        override fun onClick(view: View?) {
            isExpanded = !isExpanded

            if (isExpanded) {
                notifyItemRangeInserted(1, groupedItem.itemList.size)
                notifyItemChanged(0)
            } else {
                notifyItemRangeRemoved(1, groupedItem.itemList.size)
                notifyItemChanged(0)
            }
        }

    }

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        class HeaderViewHolder(itemView: View) : ViewHolder(itemView) {
            private val headerTextView = itemView.findViewById<TextView>(R.id.headerTextView)

            fun onBind(header: GroupedItem.Header, onClickListener: View.OnClickListener) {
                headerTextView.text = header.headerText
                itemView.setOnClickListener {
                    onClickListener.onClick(it)
                }
            }

        }

        class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
            private val itemTextView = itemView.findViewById<TextView>(R.id.itemTextView)

            fun onBind(item: GroupedItem.Item) {
                itemTextView.text = item.itemText
            }

        }

    }


}