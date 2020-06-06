package com.bingo.ybd.ext

import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_GRANTED
import com.afollestad.assent.*
import com.afollestad.assent.rationale.RationaleHandler

//拓展Assent动态申请权限
fun Activity.runWithPermissions(
    vararg permissions: Permission,
    requestCode: Int = 40,
    rationaleHandler: RationaleHandler? = null,
    granted: Callback,
    denied: Callback
) {
    if(isAllGranted(*permissions)) {
        val permissionList = permissions.asList()
        val grantResultList = IntArray(permissions.size)
        permissionList.forEachIndexed { index, _ ->
            grantResultList[index] = PERMISSION_GRANTED
        }
        granted.invoke(AssentResult(permissionList, grantResultList))
    }else {
        askForPermissions(
            *permissions,
            requestCode = requestCode,
            rationaleHandler = rationaleHandler
        ) {
            if(it.isAllGranted(*permissions)) {
                granted.invoke(it)
            }else{
                denied.invoke(it)
            }
        }
    }
}