package com.example.younghyupyun.myapplication.base.model

import android.os.Parcel
import androidx.databinding.ObservableField
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TestBaseKotlinModel(val testId: Int) : TestBaseJavaModel(), ITestModel {
    @IgnoredOnParcel
    override val modelTest: ObservableField<String> = ObservableField()

    init {
        modelTest.set(dataName + "!@#!#!@#!@#!@#")
    }


    constructor(parcel: Parcel) : this(parcel.readInt()) {
        dataName = parcel.readString()
        //  modelTest.set(dataName + "!@#!#!@#!@#!@#")
    }

    companion object : Parceler<TestBaseKotlinModel> {
        @JvmStatic
        override fun TestBaseKotlinModel.write(dest: Parcel, flags: Int) {
            this.writeToParcel(dest, flags)
        }

        override fun create(parcel: Parcel): TestBaseKotlinModel = TestBaseKotlinModel(parcel)
    }


}