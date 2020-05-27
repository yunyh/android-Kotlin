package com.example.younghyupyun.myapplication.base.model

import android.os.Parcelable
import androidx.databinding.ObservableField

interface ITestModel : Parcelable {

    val modelTest: ObservableField<String>
}