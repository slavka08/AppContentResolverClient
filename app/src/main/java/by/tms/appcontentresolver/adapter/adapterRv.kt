package by.tms.appcontentresolver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.tms.appcontentresolver.databinding.ItemLayoutBinding
import by.tms.appcontentresolver.entity.Item

class adapterRv:  ListAdapter<Item, adapterRv.MyViewHolder>(DIFF)  {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                (oldItem.id == newItem.id && oldItem.name == newItem.name)
        }
    }
    class MyViewHolder(private val binder: ItemLayoutBinding): RecyclerView.ViewHolder(binder.root) {
        fun bind(item: Item){
            binder.tvName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(getItem(position))
    }
}