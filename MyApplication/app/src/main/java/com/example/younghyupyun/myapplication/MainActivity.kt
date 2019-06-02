package com.example.younghyupyun.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.younghyupyun.myapplication.base.annotations.ActivityUiType
import com.example.younghyupyun.myapplication.base.annotations.RequiredViewModel
import com.example.younghyupyun.myapplication.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

@RequiredViewModel(MainViewModel::class)
@ActivityUiType(MainViewModel.MainActivityUi::class)
class MainActivity : BaseActivity<MainViewModel, MainViewModel.MainActivityUi>(),
    MainViewModel.MainActivityUi {

    override fun action() {

    }

    override val uiHandler: MainViewModel.MainActivityUi
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { viewModel.testSnackbar() }
        list_view.adapter = BaseRecyclerView()
        list_view.layoutManager = LinearLayoutManager(baseContext, RecyclerView.HORIZONTAL, false)
        indicator_view.attachRecyclerView(list_view)
        PagerSnapHelper().attachToRecyclerView(list_view)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUi()
    }
}

class BaseRecyclerView : RecyclerView.Adapter<BaseRecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = "TextView $position"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView by lazy {
            itemView.findViewById<TextView>(R.id.item_text)
        }

    }
}