package com.example.administrator.googleplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/03
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class BserView extends View{
    private Paint mPaint;
    private Path mPath;
    public BserView(Context context) {
        super(context);
        init();
    }

    public BserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        mPaint = paint;

        Path path = new Path();

        path.moveTo(100,100);
        path.lineTo(300,300);

        path.quadTo(400,0,500,300);
        path.rQuadTo(100,-200,200,0);

        path.moveTo(100,500);
        path.rLineTo(200,200);

        path.cubicTo(400,500,600,900,700,700);
        path.rCubicTo(100,-200,300,200,400,0);


        mPath = path;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);

    }
}
