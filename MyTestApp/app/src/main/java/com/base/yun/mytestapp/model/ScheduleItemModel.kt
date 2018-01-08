package com.base.yun.mytestapp.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by YounghyubYun on 2018. 1. 8..
 */
data class ScheduleItemModel(override var id: Int, override var data: String?, private var dateOfTime: Long, private var title: String) : MyModel(id, data) {
    constructor(parcel: Parcel) : this(parcel.readInt(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeLong(dateOfTime)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return super.describeContents()
    }

    companion object CREATOR : Parcelable.Creator<ScheduleItemModel> {
        override fun createFromParcel(parcel: Parcel): ScheduleItemModel {
            return ScheduleItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ScheduleItemModel?> {
            return arrayOfNulls(size)
        }
    }
}