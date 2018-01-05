package com.base.yun.mytestapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.yun.mytestapp.R
import com.base.yun.mytestapp.model.MyModel
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private var isEdit = false

    companion object {
        private const val PARAM = "param"
        fun newInstance(item: MyModel): Fragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(PARAM, item)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val param: MyModel? = arguments?.get(PARAM) as? MyModel
        param?.let {
            detail_id.setText(param.id.toString())
            detail_text.setText(param.data)
        }
        detail_edit_button.setOnClickListener({
            editMode()
        })
    }

    private fun editMode() {
        isEdit = !isEdit
        detail_id.isEnabled = isEdit
        detail_text.isEnabled = isEdit
        detail_edit_button.text = if (isEdit) {
            "Confirm"
        } else {
            "Edit"
        }
    }
}