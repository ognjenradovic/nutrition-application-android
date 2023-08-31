package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.FragmentListBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.SavedMealAdapter
import rs.raf.vezbe11.presentation.view.states.MealsState
import rs.raf.vezbe11.presentation.view.states.SavedMealsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class SavedMealFragment : Fragment(R.layout.fragment_saved_meals) {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: SavedMealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the desired layout file based on the layout resource ID
        val rootView = inflater.inflate(R.layout.fragment_saved_meals, container, false)

        // Initialize the _binding variable using DataBindingUtil
        _binding = FragmentListBinding.bind(rootView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        adapter = SavedMealAdapter(
            itemClickListener = { meal -> openMealDetailsFragment(meal) },
            editClickListener = { meal -> openEditMeal(meal) },
            deleteClickListener = { meal -> mainViewModel.deleteSaveMeal(meal.id.toString()) }
        )
        initUi()
        initObservers()
        binding.recyclerView.adapter = adapter
    }

    private fun openEditMeal(meal: Meal) {
        val fragment = EditSavedMealFragment.newInstance(meal, "jelo")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()
    }




    private fun openMealDetailsFragment(meal: Meal) {
        val fragment = FragmentMealDetails.newInstance(meal, "jelo")
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
//        binding.searchEditText.doAfterTextChanged {
//            val filter = it.toString()
//            mainViewModel.getMealsByName(filter);
//
//        }
    }

    private fun initObservers() {
        mainViewModel.savedMealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })


        mainViewModel.getSavedMeals()


    }

    private fun renderState(state: SavedMealsState) {
        when (state) {
            is SavedMealsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.meals.meals)
            }
            is SavedMealsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is SavedMealsState.DataFetched -> {
                showLoadingState(false)
                /*/* /*Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show())*/)*/)*/
            }
            is SavedMealsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
//        if (loading) {
//            binding.loadingProgressBar.visibility = View.VISIBLE
//            binding.searchEditText.isEnabled = false
//
//            binding.searchEditText.postDelayed({
//                binding.loadingProgressBar.visibility = View.GONE
//                binding.searchEditText.isEnabled = true
//
//            }, 2000)
//        } else {
//            binding.loadingProgressBar.visibility = View.GONE
//            binding.searchEditText.isEnabled = true
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}