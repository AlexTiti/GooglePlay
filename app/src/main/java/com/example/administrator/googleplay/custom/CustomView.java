package com.example.administrator.googleplay.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.googleplay.R;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/08
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class CustomView extends View {
    private boolean showText;
    private int lab;
    private Paint mPaintText;
    private Paint mPaintShadow;

    private int centerX,centerY;

    public CustomView(Context context) {
        super(context);
        init(context,null);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context ,AttributeSet attrs){
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomView,0,0);
        showText = array.getBoolean(R.styleable.CustomView_showText,true);
        lab = array.getInteger(R.styleable.CustomView_lab,0);
        array.recycle();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        mPaintText = paint;

        Paint paint1 = new Paint();
        paint1.setColor(0xff101010);
        paint1.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
        mPaintShadow = paint1;
    }

    public boolean isShowText() {
        return showText;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
//        canvas.translate(centerX,centerY);
//       String string = "AAAA";
//       Rect rect = new Rect();
//       mPaintText.getTextBounds(string,0,string.length(),rect);
//       canvas.drawText(string,-rect.width()/2,rect.height()/2,mPaintText);
//       canvas.restore();

        Rect rect = new Rect(0,0,centerX*2,centerY*2);
        canvas.drawRect(rect,mPaintText);



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = getWidth()/2;
        centerY = getHeight()/2;
    }
}
