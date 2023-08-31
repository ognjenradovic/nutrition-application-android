package rs.raf.vezbe11.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.view.fragments.FilterListFragment
import rs.raf.vezbe11.presentation.view.fragments.ListCategoriesFragment
import rs.raf.vezbe11.presentation.view.fragments.ListMealFragment
import rs.raf.vezbe11.presentation.view.fragments.SavedMealFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> ListCategoriesFragment()
            FRAGMENT_2 -> FilterListFragment()
            else -> SavedMealFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> "Categories"
            FRAGMENT_2 -> "Filter"
            else -> "Saved"
        }
    }

}