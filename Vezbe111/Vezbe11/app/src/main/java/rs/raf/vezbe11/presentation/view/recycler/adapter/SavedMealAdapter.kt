package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.databinding.LayoutItemSavedMealBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.MealDiffCallBack
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MealViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.SavedMealViewHolder

class SavedMealAdapter(
    private val itemClickListener: (Meal) -> Unit,
    private val editClickListener: (Meal) -> Unit,
    private val deleteClickListener: (Meal) -> Unit
) : ListAdapter<Meal, SavedMealViewHolder>(MealDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SavedMealViewHolder {
        val itemBinding = LayoutItemSavedMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedMealViewHolder(itemBinding,editClickListener,deleteClickListener)
    }

    override fun onBindViewHolder(holder: SavedMealViewHolder, position: Int) {
        val meal = getItem(position)
        holder.bind(meal)

        // Set click listener for the item view
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(meal)
        }
    }
}