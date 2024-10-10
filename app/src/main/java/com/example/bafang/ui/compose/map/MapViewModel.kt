package com.example.myapplication.ui.compose.map

import android.util.Log
import androidx.lifecycle.ViewModel
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.LocationSource
import com.example.bafang.MyApplication


class MapViewModel : ViewModel(), LocationSource, AMapLocationListener {
    private var mListener: LocationSource.OnLocationChangedListener? = null
    private var mLocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null
    override fun activate(listener: LocationSource.OnLocationChangedListener?) {
        mListener = listener
        startMapLocation()
    }

    override fun deactivate() {
        mLocationClient?.stopLocation()
        mLocationClient?.onDestroy()
        mLocationClient = null
        mListener = null
    }

    override fun onLocationChanged(aMapLocation: AMapLocation?) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation.errorCode == 0) {
                mListener!!.onLocationChanged(aMapLocation) // 显示系统小蓝点
            } else {
                val errText =
                    ("定位失败," + aMapLocation.errorCode) + ": " + aMapLocation.errorInfo
                Log.e("location", errText)
            }
        }
    }

    fun startMapLocation() {
        initAMapLocationClient(mLocationClient, this) { client, option ->
            mLocationClient = client
            mLocationOption = option
        }
    }

    private fun initAMapLocationClient(
        locationClient: AMapLocationClient?,
        listener: AMapLocationListener,
        block: (AMapLocationClient, AMapLocationClientOption) -> Unit
    ) {
        if (null == locationClient) {
            val newLocationClient = AMapLocationClient(MyApplication.context)
            val locationClientOption = AMapLocationClientOption()

            // 设置监听器和定位模式
            newLocationClient.setLocationListener(listener)
            locationClientOption.locationMode =
                AMapLocationClientOption.AMapLocationMode.Hight_Accuracy

            // 设置定位参数和启动定位
            newLocationClient.setLocationOption(locationClientOption)
            block.invoke(newLocationClient.apply { startLocation() }, locationClientOption)
        }
    }
}