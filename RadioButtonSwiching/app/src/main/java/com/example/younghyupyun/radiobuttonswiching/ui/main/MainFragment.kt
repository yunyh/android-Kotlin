package com.example.younghyupyun.radiobuttonswiching.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.younghyupyun.radiobuttonswiching.adapters.PlantsListAdapter
import com.example.younghyupyun.radiobuttonswiching.databases.AppDatabase
import com.example.younghyupyun.radiobuttonswiching.databases.PlantsRepository
import com.example.younghyupyun.radiobuttonswiching.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        private const val PARAM = "param"
        private const val SAVED_PARAM = "saved_param"

        fun newInstance(viewString: String) =
            MainFragment().apply { arguments = Bundle().apply { putString(PARAM, viewString) } }
    }

    private val viewModel by lazy {
        checkNotNull(context).let {
            ViewModelProviders.of(
                this,
                MainViewModelProviderFactory(PlantsRepository(AppDatabase.getInstance(it).plantsDAO()))
            ).get(MainViewModel::class.java).apply { showText.set(arguments?.getString(PARAM)) }
        }
    }

    private val listAdapter = PlantsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        MainFragmentBinding.inflate(inflater, container, false).apply {
            model = viewModel
            plantsList.adapter = listAdapter
        }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.apply {
            viewModel.showText.set(getString(PARAM))
        }
    }
}
