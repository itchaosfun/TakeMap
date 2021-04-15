package com.chaos.takemap.model.enummodel

enum class DefaultLocationEnum(var type:String,var defaultLon:Double,var defaultLat:Double) {
    YOUDU("由度",121.61123779896286,31.25195080871839),
    LISHUI("溧水",118.975170500000104,31.716902833333336),
    CHUANGXINGANG("创新港",121.18926894190434,31.279854609379353),
    DEFAULT("由度",121.61123779896286,31.25195080871839),
    ;

    companion object{
        fun valueOfLocationByType(type: String):DefaultLocationEnum{
            values().forEach {
                if (type == it.type){
                    return it
                }
            }
            return DEFAULT
        }
    }
}