package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.FragmentFilterBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.MealAdapter
import rs.raf.vezbe11.presentation.view.states.FilterMealsState
import rs.raf.vezbe11.presentation.view.states.MealsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class FilterListFragment : Fragment() {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MealAdapter
    private var selectedParameter: String = "Category"  // Set default value
    private var currentPage = 1
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var selectedSortOption: String

    private val itemsPerPage = 20

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
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
        loadingProgressBar = binding.loadingProgressBar
        loadingProgressBar.visibility = View.GONE

        adapter = MealAdapter { meal: Meal ->
            openMealDetailsFragment(meal)
        }
        binding.recyclerView1.layoutManager = LinearLayoutManager(context)
        binding.recyclerView1.adapter = adapter
        binding.searchEditText.doAfterTextChanged {
            val filter = it.toString()
            currentPage = 1 // Reset page to 1 when a new filter is applied
            mainViewModel.getMealsByParameter(filter, selectedParameter, currentPage)
        }
        binding.nextPage.setOnClickListener {
            loadNextPage()
        }
        binding.previousPage.setOnClickListener {
            previousPage()
        }
        val sortOptions = listOf("None", "Ascending", "Descending")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sortOptions)
        binding.sortSpinner.adapter = spinnerAdapter

        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSortOption = sortOptions[position]
                // Call your sorting function here, passing the selectedSortOption
                sortMeals(selectedSortOption)
            }

            private fun sortMeals(sortOption: String) {
                val meals = adapter.currentList.toMutableList()

                when (sortOption) {
                    "None" -> {
                        // Do nothing
                    }
                    "Ascending" -> {
                        meals.sortBy { it.name }
                    }
                    "Descending" -> {
                        meals.sortByDescending { it.name }
                    }
                }

                adapter.submitList(meals)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }

        }

        val filterOptions = listOf("Name", "Category", "Area", "Ingredients", "Tag","Saved Meals")
        val spinnerAdapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, filterOptions)
        binding.filterSpinner1.adapter = spinnerAdapter1

        binding.filterSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedParameter = filterOptions[position]
                currentPage = 1 // Reset page to 1 when a new parameter is selected
                mainViewModel.getMealsByParameter(binding.searchEditText.text.toString(), selectedParameter, currentPage)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }



    }

    private fun loadNextPage() {
        currentPage++
        mainViewModel.getMealsByParameter(
            binding.searchEditText.text.toString(),
            selectedParameter,
            currentPage
        )
    }
    private fun previousPage() {
        currentPage--
        mainViewModel.getMealsByParameter(
            binding.searchEditText.text.toString(),
            selectedParameter,
            currentPage
        )
    }

    private fun openMealDetailsFragment(meal: Meal) {
        mainViewModel.fetchMealDetails(meal.id)
        val fragment = FragmentMealDetails.newInstance(meal, "jelo")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initObservers() {
        mainViewModel.filterMealsState.observe(viewLifecycleOwner, Observer { state ->
            Timber.e(state.toString())
            renderState(state)
        })
        mainViewModel.getMealsByParameter(
            binding.searchEditText.text.toString(),
            selectedParameter,
            currentPage
        )
    }

    private fun renderState(state: FilterMealsState) {
        when (state) {
            is FilterMealsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.meals.meals)
            }
            is FilterMealsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is FilterMealsState.DataFetched -> {
                showLoadingState(false)
                 /*Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show())*/
            }
            is FilterMealsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        if (loading) {
            loadingProgressBar.visibility = View.VISIBLE
            binding.searchEditText.isEnabled = false

            binding.searchEditText.postDelayed({
                loadingProgressBar.visibility = View.GONE
                binding.searchEditText.isEnabled = true

            }, 2000)
        } else {
            loadingProgressBar.visibility = View.GONE
            binding.searchEditText.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}