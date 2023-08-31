package rs.raf.vezbe11.presentation.view.recycler.viewholder

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.data.models.Category
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.databinding.LayoutItemSavedMealBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.fragments.EditSavedMealFragment
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class SavedMealViewHolder(private val itemBinding: LayoutItemSavedMealBinding, private val editClickListener: (Meal) -> Unit, private val deleteClickListener: (Meal) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: Meal) {
        itemBinding.titleTv.text = meal.name

        Picasso.get().load(meal.imageSource).into(itemBinding.imageView)

        itemBinding.deleteButton.setOnClickListener {
            deleteClickListener.invoke(meal)
        }

        itemBinding.editButton.setOnClickListener {
            editClickListener.invoke(meal)
        }
    }
}

