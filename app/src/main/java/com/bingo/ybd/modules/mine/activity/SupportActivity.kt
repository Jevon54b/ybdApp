package com.bingo.ybd.modules.mine.activity

import android.os.Handler
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.config.Settings
import com.bingo.ybd.constant.Constant
import com.bingo.ybd.data.model.Comment
import com.bingo.ybd.data.model.Message
import com.bingo.ybd.data.model.SupportInfo
import com.bingo.ybd.ext.errorToast
import com.bingo.ybd.ext.hideKeyboard
import com.bingo.ybd.ext.warningToast
import com.bingo.ybd.modules.mine.custom.MessageAdapter
import com.bingo.ybd.modules.mine.vm.SupportViewModel
import com.bingo.ybd.util.StringUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_support.*
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.lang.ref.WeakReference
import java.net.URI

class SupportActivity : BaseVMActivity(){

    companion object{
        const val TAG = "SupportActivity"
        const val MSG_OPEN = 1;
        const val MSG_CLOSE = 2 ;
        const val MSG_MESSAGE = 3;
        const val MSG_ERROR = 4;
    }

    private val supportViewModel: SupportViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.activity_support
    }
    override fun getViewModel(): BaseViewModel {
        return supportViewModel
    }

    lateinit var messageAdapter: MessageAdapter
    private var supportWSClient : WebSocketClient? = null
    private val msgHandler = MessageHandler(this)

    private class MessageHandler(activity:SupportActivity) : Handler(){
        private val sActivity: WeakReference<SupportActivity> = WeakReference(activity)

        override fun handleMessage(msg: android.os.Message) {
            if (sActivity.get() == null){
                return
            }
            val activity = sActivity.get()!!
            when(msg.what){
                MSG_OPEN -> {
                    activity.messageAdapter.add(Message("系统","成功连接客服",0,StringUtils.getCurrentTimeStr()))
                    activity.messageAdapter.notifyDataSetChanged()
                }
                MSG_CLOSE -> {
                    activity.messageAdapter.add(Message("系统","客服断开连接，请退出重新连接",0,StringUtils.getCurrentTimeStr()))
                    activity.messageAdapter.notifyDataSetChanged()
                }
                MSG_MESSAGE -> {
                    val jsonStr = msg.obj as String
                    Log.e(TAG, "handleMessage: "+ jsonStr )
                    val jsonObj = Gson().fromJson<Message>(jsonStr,object:TypeToken<Message>(){}.type)
                    activity.messageAdapter.add(jsonObj)
                    activity.messageAdapter.notifyDataSetChanged()
                }
                MSG_ERROR -> {
                    activity.messageAdapter.add(Message("系统","连接由于未知原因断开，请退出重新连接",0,StringUtils.getCurrentTimeStr()))
                    activity.messageAdapter.notifyDataSetChanged()
                }
                else -> return
            }
        }
    }

    override fun initView() {
        sendMsgBtn.setOnClickListener {
            val msg = sendMsgEdit.text.toString()
            if (supportWSClient == null){
                warningToast("请连接客服后再发送")
            }else{
                if(TextUtils.isEmpty(msg)){
                    warningToast("内容为空,请输入")
                }else{
                    messageAdapter.add(Message(Settings.Account.userName,msg,1))
                    hideKeyboard(sendMsgEdit)
                    sendMsgEdit.setText("")
                    sendMessageToSupport(msg)
                }
            }

        }


        messageAdapter = MessageAdapter(this)
        msgRecyclerView.adapter = messageAdapter
        msgRecyclerView.layoutManager = LinearLayoutManager(this)
    }


    override fun initData() {
        supportViewModel.getSupportInfo().observe(this, Observer {
            when(it.status){
                Constant.RESPONSE_SUCCESS -> {
                    showSupportInfoMsg(it.data)
                }
                Constant.RESPONSE_FAILED -> {
                    errorToast("连接失败")
                    finish()
                }
                else -> {
                    errorToast("系统错误")
                    finish()
                }
            }
        })
    }

    private fun showSupportInfoMsg(data: SupportInfo) {
        when(data.code){
            0 -> {
                messageAdapter.add(Message("系统","当前无客服在线，请稍后重试"))
                messageAdapter.notifyDataSetChanged()
            }
            -1 -> {
                messageAdapter.add(Message("系统","当前客服忙线，请稍后重试"))
                messageAdapter.notifyDataSetChanged()
            }
            1 -> {
                messageAdapter.add(Message("系统","正在为您连接客服..."))
                messageAdapter.notifyDataSetChanged()
                linkSupport(data.supportId)
            }
        }
    }

    private fun linkSupport(supportId: Int) {
        val uri = URI.create("ws://193.112.205.254:8080/ybd/WebSocketTest2/$supportId/1")
        supportWSClient = object:WebSocketClient(uri){
            override fun onOpen(handshakedata: ServerHandshake?) {
                Log.e(SupportActivity.TAG, "onOpen" )
                msgHandler.sendEmptyMessage(MSG_OPEN)
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                Log.e(SupportActivity.TAG, "onClose" )
                msgHandler.sendEmptyMessage(MSG_CLOSE)
            }

            override fun onMessage(message: String?) {
                Log.e(SupportActivity.TAG, "onMessage")
                message?.let {
                    msgHandler.sendMessage(android.os.Message.obtain(msgHandler, MSG_MESSAGE,it))
                }
            }

            override fun onError(ex: Exception?) {
                ex?.printStackTrace()
                Log.e(SupportActivity.TAG, "onError" )
                msgHandler.sendEmptyMessage(MSG_ERROR)
            }

        }
        supportWSClient!!.connect()
    }

    private fun sendMessageToSupport(msg: String) {
        val name = Settings.Account.userName
        val time = StringUtils.getCurrentTimeStr()
        val msg = Message(name,msg,1,time)
        supportWSClient?.send(Gson().toJson(msg))
    }


    override fun onDestroy() {
        super.onDestroy()
        supportWSClient?.close()
        MessageHandler(this).removeCallbacksAndMessages(null)
    }




}