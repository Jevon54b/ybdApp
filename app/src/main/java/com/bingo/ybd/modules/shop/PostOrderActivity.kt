package com.bingo.ybd.modules.shop

import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.bingo.ybd.R
import com.bingo.ybd.base.activity.BaseVMActivity
import com.bingo.ybd.base.viewmodel.BaseViewModel
import com.bingo.ybd.data.model.MedBrief
import com.bingo.ybd.data.model.MedInOrder
import com.bingo.ybd.ext.errorToast
import com.bingo.ybd.modules.shop.custom.MedPostItemAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_post_order.*

class PostOrderActivity : BaseVMActivity(){

    val TAG = "PostOrderActivity"

    var medList: List<MedInOrder>? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_post_order
    }

    override fun initView() {
        initTestData()
        recyclerView.adapter = MedPostItemAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        backImg.setOnClickListener {
            finish()
        }

        editInfoView.setOnClickListener {
            showEditDialog()
        }

        postOrderBtn.setOnClickListener {
            onPostOrderBtnClick()
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioBtn1 -> {
                    deliverFeeText.text = "￥4"
                }
                R.id.radioBtn2 -> {
                    deliverFeeText.text = "￥2"
                }
            }
        }

    }

    private fun onPostOrderBtnClick() {
        if (nameText.text.isBlank() || addressText.text.isBlank() || phoneText.text.isBlank()) {
            errorToast("收货信息不能为空")
            return
        }
        if (radioGroup.checkedRadioButtonId == -1) {
            errorToast("请选择配送方式")
            return
        }
    }

    private fun showEditDialog() {
        MaterialDialog(this).show {
            title(R.string.title_deliver_info)
            customView(R.layout.dialog_deliver_info_edit, scrollable = true, horizontalPadding = true)
            positiveButton(R.string.ensure_text) { dialog ->
                val nameEdit: EditText = dialog.getCustomView().findViewById(R.id.nameEdit)
                val phoneEdit: EditText = dialog.getCustomView().findViewById(R.id.phoneEdit)
                val addressEdit: EditText = dialog.getCustomView().findViewById(R.id.addressEdit)
                this@PostOrderActivity.nameText.text = nameEdit.text.toString()
                this@PostOrderActivity.phoneText.text = phoneEdit.text.toString()
                this@PostOrderActivity.addressText.text = addressEdit.text.toString()
            }
            negativeButton(android.R.string.cancel)
            lifecycleOwner(this@PostOrderActivity)
        }
    }

    fun initTestData() {
        val json = " [\n" +
                "                {\n" +
                "                    \"med_id\": 20029,\n" +
                "                    \"om_id\": 42,\n" +
                "                    \"name\": \"[健途]甜橙味多种维生素泡腾片\",\n" +
                "                    \"pic\": \"https://imgq.ddky.com/c/product/533839/big/z_1.jpg?t=1517896916255&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "                    \"prescript\": 0,\n" +
                "                    \"packing_size\": \"详见说明书。\",\n" +
                "                    \"med_num\": 1,\n" +
                "                    \"price\": 35.0,\n" +
                "                    \"total_sum\": 59.0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"med_id\": 20008,\n" +
                "                    \"om_id\": 43,\n" +
                "                    \"name\": \"[鱼峰山]跌打万花油\",\n" +
                "                    \"pic\": \"https://imgq.ddky.com/c/product/544258/big/z_1.jpg?t=1559626518766&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F800x800%21%2Fquality%2F100\",\n" +
                "                    \"prescript\": 0,\n" +
                "                    \"packing_size\": \"详见说明书。\",\n" +
                "                    \"med_num\": 1,\n" +
                "                    \"price\": 20.0,\n" +
                "                    \"total_sum\": 59.0\n" +
                "                }\n" +
                "            ]\n"

        val gson = Gson()
        medList = gson.fromJson(json, object : TypeToken<List<MedBrief>>() {}.type)

    }

    override fun getViewModel(): BaseViewModel {
        return BaseViewModel()
    }

}