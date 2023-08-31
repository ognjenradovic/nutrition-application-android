package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Category
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemCategoryBinding

class CategoryViewHolder(private val itemBinding: LayoutItemCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(category: Category, onItemClick: (Category) -> Unit) {
        itemBinding.titleTv.text = category.name
        Picasso.get().load(category.imageURL).into(itemBinding.imageView)
        itemBinding.dotsIcon.setOnClickListener {
            showDescriptionPopup(category.description)
        }
        itemBinding.root.setOnClickListener {
            onItemClick(category)
        }
    }

    private fun showDescriptionPopup(description: String) {
        val rootView = itemBinding.root
        Snackbar.make(rootView, description, Snackbar.LENGTH_LONG).show()
    }
}