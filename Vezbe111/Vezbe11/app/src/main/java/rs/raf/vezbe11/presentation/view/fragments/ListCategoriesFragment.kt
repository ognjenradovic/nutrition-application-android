package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Category
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.FragmentListBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.vezbe11.presentation.view.states.CategoriesState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListCategoriesFragment : Fragment() {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CategoryAdapter { category ->
        openCategoryFragment(category)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun openCategoryFragment(category: Category) {
        val fragment = ListMealFragment.newInstance(category, "category")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initListeners() {
        binding.searchEditText.doAfterTextChanged {
            val filter = it.toString()
            mainViewModel.getCategoriesByName(filter)
        }
    }

    private fun initObservers() {
        mainViewModel.categoriesState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        mainViewModel.fetchAllCategories()
        mainViewModel.fetchAllMeals()
        mainViewModel.getAllCategories()
    }

    private fun renderState(state: CategoriesState) {
        when (state) {
            is CategoriesState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.categories.categories)
            }
            is CategoriesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is CategoriesState.DataFetched -> {
                showLoadingState(false)
                 /*Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show())*/
            }
            is CategoriesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        if (loading) {
            binding.loadingProgressBar.visibility = View.VISIBLE
            binding.searchEditText.isEnabled = false

            binding.searchEditText.postDelayed({
                binding.loadingProgressBar.visibility = View.GONE
                binding.searchEditText.isEnabled = true

            }, 2000)
        } else {
            binding.loadingProgressBar.visibility = View.GONE
            binding.searchEditText.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}