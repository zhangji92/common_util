package com.zj.common.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.zj.common.R
import com.zj.common.databinding.TitleToolBarBinding
import com.zj.common.expand.statusBarHeight


class TitleToolBar(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var leftOnClickListener: OnClickListener? = null
    var rightImgOnClickListener: OnClickListener? = null
    var rightTextOnClickListener: OnClickListener? = null

    private val binding by lazy {
        TitleToolBarBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.title_tool_bar)
        // 状态栏十分显示
        val visibility = typedArray.getInt(R.styleable.title_tool_bar_visibility, View.VISIBLE)
        val toolBar: Toolbar = binding.root.findViewById(R.id.tool_bar)
        if (visibility == VISIBLE) {
            toolBar.visibility = visibility
            toolBar.post {
                val layoutParams = toolBar.layoutParams
                layoutParams.height = context.statusBarHeight()
                toolBar.layoutParams = layoutParams
            }
        } else {
            toolBar.visibility = visibility
        }

        // 设置左右间距
        val padding = typedArray.getDimension(R.styleable.title_tool_bar_padding, 0f)
        binding.padding = padding.toInt()

        // 设置返回事件
        val leftImg = typedArray.getResourceId(R.styleable.title_tool_bar_leftImg, R.mipmap.back)
        if (leftImg != -1) {
            binding.leftImg = ContextCompat.getDrawable(context, leftImg)
        }
        // 设置标题
        val title = typedArray.getString(R.styleable.title_tool_bar_title)
        // 如果为null则返回空字符串
        binding.title.text = title ?: ""
        // 设置字体大小 默认16sp
        val titleSize = typedArray.getDimension(R.styleable.title_tool_bar_titleSize, 18f)
        binding.title.textSize = titleSize
        // 设置主题文字颜色 默认黑色
        val titleColor =
            typedArray.getResourceId(R.styleable.title_tool_bar_titleColor, android.R.color.black)
        binding.title.setTextColor(ContextCompat.getColor(context, titleColor))

        // 设置右侧文字
        val rightText = typedArray.getString(R.styleable.title_tool_bar_rightText)
        binding.rightText.text = rightText ?: ""
        // 文字颜色
        val rightColor =
            typedArray.getResourceId(R.styleable.title_tool_bar_navTextColor, android.R.color.black)
        binding.rightText.setTextColor(ContextCompat.getColor(context, rightColor))
        // 右侧字体颜色
        val navTextSize = typedArray.getDimension(R.styleable.title_tool_bar_navTextSize, 14f)
        binding.rightText.textSize = navTextSize
        // 设置字体某一侧的突破
        val leftBounds = typedArray.getDrawable(R.styleable.title_tool_bar_leftBounds)
        val rightBounds = typedArray.getDrawable(R.styleable.title_tool_bar_rightBounds)
        val topBounds = typedArray.getDrawable(R.styleable.title_tool_bar_topBounds)
        val bottomBounds = typedArray.getDrawable(R.styleable.title_tool_bar_bottomBounds)
        binding.rightText.setCompoundDrawablesWithIntrinsicBounds(
            leftBounds,
            topBounds,
            rightBounds,
            bottomBounds
        )
        // 右侧图片
        val rightImg = typedArray.getDrawable(R.styleable.title_tool_bar_rightImg)
        binding.rightImg.setImageDrawable(rightImg)
        
        // 返回键点击时间
        binding.back.setOnClickListener {
            if (leftOnClickListener == null) {
                (it.context as Activity).finish()
            } else {
                leftOnClickListener?.onClick(it)
            }
        }

        binding.rightImg.setOnClickListener {
            rightImgOnClickListener?.onClick(it)
        }

        binding.rightText.setOnClickListener {
            rightTextOnClickListener?.onClick(it)
        }

    }


}