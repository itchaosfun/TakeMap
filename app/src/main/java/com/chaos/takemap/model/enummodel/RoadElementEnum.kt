package com.chaos.takemap.model.enummodel

import com.chaos.takemap.R
import com.chaos.takemap.config.ThemeConfig
import com.chaos.takemap.model.THEME_DARK

enum class RoadElementEnum(var type: Int, var desc: String, var lightIcon: Int, var darkIcon: Int) {
    LEFT(1001, "左转", R.mipmap.light_left, R.mipmap.dark_left),
    RIGHT(1002, "右转", R.mipmap.light_right, R.mipmap.dark_right),
    STRAIGHT(1003, "直行", R.mipmap.light_straight, R.mipmap.dark_straight),
    ROLLBACK(1004, "掉头", R.mipmap.light_rollback, R.mipmap.dark_rollback),
    LEFT_STRAIGHT(1005, "左转直行", R.mipmap.light_straight_left, R.mipmap.dark_straight_left),
    LEFT_ROLLBACK(1006, "左转掉头", R.mipmap.light_left_rollback, R.mipmap.dark_left_rollback),
    RIGHT_STRAIGHT(1007, "右转直行", R.mipmap.light_straight_right, R.mipmap.dark_straight_right),
    LEFT_RIGHT(1008, "左转右转", R.mipmap.light_left_right, R.mipmap.dark_left_right),

    SPEED_LIMIT_30(2001, "限速30", R.mipmap.speed_30, R.mipmap.speed_30),
    SPEED_LIMIT_40(2002, "限速40", R.mipmap.speed_40, R.mipmap.speed_40),
    SPEED_LIMIT_50(2003, "限速50", R.mipmap.speed_50, R.mipmap.speed_50),
    SPEED_LIMIT_60(2004, "限速60", R.mipmap.speed_60, R.mipmap.speed_60),
    SPEED_LIMIT_70(2005, "限速70", R.mipmap.speed_70, R.mipmap.speed_70),
    SPEED_LIMIT_80(2006, "限速80", R.mipmap.speed_80, R.mipmap.speed_80),
    SPEED_LIMIT_90(2007, "限速90", R.mipmap.speed_90, R.mipmap.speed_90),
    SPEED_LIMIT_100(2008, "限速100", R.mipmap.speed_100, R.mipmap.speed_100),
    SPEED_LIMIT_110(2009, "限速110", R.mipmap.speed_110, R.mipmap.speed_110),
    SPEED_LIMIT_120(2010, "限速120", R.mipmap.speed_120, R.mipmap.speed_120),

    LANE_NON_MOTOR(3001,"非机动车道",R.mipmap.light_non_motor,R.mipmap.dark_non_motor),
    LANE_MOTOR(3002,"机动车道",R.mipmap.light_motor,R.mipmap.dark_motor),
    LANE_BUS(3003,"公交车道",R.mipmap.light_bus,R.mipmap.dark_bus),


    ;

    companion object {
        fun valueOfByType(type: Int): Int {
            values().forEach {
                if (it.type == type) {
                    return if (ThemeConfig.theme == THEME_DARK) {
                        it.darkIcon
                    } else {
                        it.lightIcon
                    }
                }
            }
            return R.mipmap.default_error
        }
    }
}