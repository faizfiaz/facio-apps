package com.facio.apps.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facio.apps.databinding.ItemTripBinding
import com.facio.apps.domain.models.Trips
import com.facio.apps.ui.base.BaseAdapter

class TripListAdapter(data: ArrayList<Trips>, action: (Trips, ItemTripBinding) -> Unit) : BaseAdapter<Trips>(data) {

    private val action: (Trips, ItemTripBinding) -> Unit = action
    private lateinit var binding: ItemTripBinding


    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemTripBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return GenericViewHolder(binding)
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        if (holder.viewDataBinding is ItemTripBinding) {
            (holder.viewDataBinding as ItemTripBinding).itemViewModel =
                    UserListItemViewModel(getItem(position), action, binding)
        }
    }

}