package com.bingo.ybd.data.storage

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.bingo.ybd.AppContext
import java.io.*
import java.lang.Exception
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 基于属性代理封装SharedPreferences
 */
class Preference<T>(private val key: String, private val defaultValue: T) :
    ReadWriteProperty<Any?, T> {

    companion object {
        private const val SHARE_PRE_NAME = "ybd_app"

        private val mPreferences: SharedPreferences by lazy {
            AppContext.getSharedPreferences(
                SHARE_PRE_NAME,
                Context.MODE_PRIVATE
            )
        }

        fun clear() {
            mPreferences.edit().clear()
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
        findPreference(key, defaultValue)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) =
        putPreference(key, value)

    private fun findPreference(key: String, defaultValue: T): T = with(mPreferences) {
        when (defaultValue) {
            is Int -> getInt(key, defaultValue)
            is Long -> getLong(key, defaultValue)
            is Boolean -> getBoolean(key, defaultValue)
            is String -> getString(key, defaultValue)
            is Float -> getFloat(key, defaultValue)
            is Serializable -> getSerializable(key)
            else -> throw IllegalArgumentException("This type can't be saved into SharedPreferences")
        } as T
    }


    private fun putPreference(key: String, value: T) {
        with(mPreferences.edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Serializable -> putSerializable(key, defaultValue)
                else -> throw IllegalArgumentException("This type can't be saved into SharedPreferences")
            }
        }.apply()
    }

    private fun putSerializable(key: String, value: T): SharedPreferences.Editor {
        return try {
            val baos = ByteArrayOutputStream()
            val oos = ObjectOutputStream(baos)
            oos.writeObject(value)
            val string64 = String(Base64.encode(baos.toByteArray(), 0))
            mPreferences.edit().apply {
                putString(key, string64)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            mPreferences.edit()
        }
    }

    private fun getSerializable(key: String): Any? {
        var obj: Any? = null
        try {
            val string64 = mPreferences.getString(key, null)
            if (string64.isNullOrEmpty()) {
                return null
            }
            val byteArray = Base64.decode(string64.toByteArray(), 1)


            val bais = ByteArrayInputStream(byteArray)
            val ois = ObjectInputStream(bais)
            obj = ois.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return obj
    }


}