package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.MealDiffCallBack
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MealViewHolder

class MealAdapter(private val itemClickListener: (Meal) -> Unit) : ListAdapter<Meal, MealViewHolder>(MealDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemBinding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = getItem(position)
        holder.bind(meal)

        // Set click listener for the item view
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(meal)
        }
    }
}