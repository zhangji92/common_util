package com.zj.common.utils

import android.Manifest
import android.annotation.SuppressLint
import com.zj.common.utils.PermissionConstants.PermissionGroup
import com.zj.common.utils.PermissionConstants
import android.os.Build
import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@SuppressLint("InlinedApi")
object PermissionConstants {
    const val CALENDAR = "CALENDAR"
    const val CAMERA = "CAMERA"
    const val CONTACTS = "CONTACTS"
    const val LOCATION = "LOCATION"
    const val MICROPHONE = "MICROPHONE"
    const val PHONE = "PHONE"
    const val SENSORS = "SENSORS"
    const val SMS = "SMS"
    const val STORAGE = "STORAGE"
    const val ACTIVITY_RECOGNITION = "ACTIVITY_RECOGNITION"
    private val GROUP_CALENDAR = arrayOf<String?>(
        Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR
    )
    private val GROUP_CAMERA = arrayOf<String?>(
        Manifest.permission.CAMERA
    )
    private val GROUP_CONTACTS = arrayOf<String?>(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.GET_ACCOUNTS
    )
    private val GROUP_LOCATION = arrayOf<String?>(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )
    private val GROUP_MICROPHONE = arrayOf<String?>(
        Manifest.permission.RECORD_AUDIO
    )
    private val GROUP_PHONE = arrayOf<String?>(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_PHONE_NUMBERS,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.WRITE_CALL_LOG,
        Manifest.permission.ADD_VOICEMAIL,
        Manifest.permission.USE_SIP,
        Manifest.permission.PROCESS_OUTGOING_CALLS,
        Manifest.permission.ANSWER_PHONE_CALLS
    )
    private val GROUP_PHONE_BELOW_O = arrayOf<String?>(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_PHONE_NUMBERS,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.WRITE_CALL_LOG,
        Manifest.permission.ADD_VOICEMAIL,
        Manifest.permission.USE_SIP,
        Manifest.permission.PROCESS_OUTGOING_CALLS
    )
    private val GROUP_SENSORS = arrayOf<String?>(
        Manifest.permission.BODY_SENSORS
    )
    private val GROUP_SMS = arrayOf<String?>(
        Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS,
        Manifest.permission.RECEIVE_WAP_PUSH, Manifest.permission.RECEIVE_MMS
    )
    private val GROUP_STORAGE = arrayOf<String?>(
        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val GROUP_ACTIVITY_RECOGNITION = arrayOf<String?>(
        Manifest.permission.ACTIVITY_RECOGNITION
    )

    @JvmStatic
    fun getPermissions(@PermissionGroup permission: String?): Array<String?> {
        if (permission == null) return arrayOfNulls(0)
        when (permission) {
            CALENDAR -> return GROUP_CALENDAR
            CAMERA -> return GROUP_CAMERA
            CONTACTS -> return GROUP_CONTACTS
            LOCATION -> return GROUP_LOCATION
            MICROPHONE -> return GROUP_MICROPHONE
            PHONE -> return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                GROUP_PHONE_BELOW_O
            } else {
                GROUP_PHONE
            }
            SENSORS -> return GROUP_SENSORS
            SMS -> return GROUP_SMS
            STORAGE -> return GROUP_STORAGE
            ACTIVITY_RECOGNITION -> return GROUP_ACTIVITY_RECOGNITION
        }
        return arrayOf(permission)
    }

    @StringDef(CALENDAR, CAMERA, CONTACTS, LOCATION, MICROPHONE, PHONE, SENSORS, SMS, STORAGE)
    @Retention(
        RetentionPolicy.SOURCE
    )
    annotation class PermissionGroup
}