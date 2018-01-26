package com.example.administrator.googleplay;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;


/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/03
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class TouchPullView extends View {

    private Paint mCirclepaint;
    private Paint circleWidthPaint;
    private int circleRadius ;
    private int circlePointX, circlePointY;
    private static  int maxPullHeoght ;
    private static  int minWidthPull ;
    private float mProgress = 0;
    private Path mPath;
    private Paint mPaintB;
    private int color;

    private Interpolator interpolator = new DecelerateInterpolator();
    private Interpolator pathInterpolator;

    private static  int MAX_DEGREE ;
    private float mControlX, mControlY, mEndX, mEndY;
    private static  int CONTROL_HEIGHT;

    public TouchPullView(Context context) {
        super(context);
        init(context,null);
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context ,AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.TouchPullView,0,0);
        circleRadius = array.getInteger(R.styleable.TouchPullView_circleRadius,80);
        maxPullHeoght = array.getInteger(R.styleable.TouchPullView_maxPullHeoght,400);
        minWidthPull = array.getInteger(R.styleable.TouchPullView_minWidthPull,250);
        MAX_DEGREE = array.getInteger(R.styleable.TouchPullView_max_degree,110);
        CONTROL_HEIGHT = array.getInteger(R.styleable.TouchPullView_control_height,10);
        color = array.getColor(R.styleable.TouchPullView_color,Color.argb(255,255,64,129));

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
        mCirclepaint = paint;

        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setStrokeWidth(2);
        paint1.setDither(true);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.WHITE);
        circleWidthPaint = paint1;

        Paint paintB = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintB.setAntiAlias(true);
        paintB.setDither(true);
        paintB.setColor(color);
        paintB.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintB = paintB;
        Path path = new Path();
        mPath = path;
        pathInterpolator = PathInterpolatorCompat.create(circleRadius * 4.0f / maxPullHeoght, 90f / MAX_DEGREE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制贝塞尔
        int count = canvas.save();
        float x = (getWidth() - getValues(getWidth(), minWidthPull, mProgress)) / 2;
        canvas.translate(x, 0);
        //绘制贝塞尔
        canvas.drawPath(mPath, mPaintB);
        canvas.drawCircle(circlePointX,circlePointY,circleRadius,circleWidthPaint);
        canvas.drawCircle(circlePointX, circlePointY, circleRadius, mCirclepaint);
        canvas.restoreToCount(count);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int minWidth = 2 * circleRadius + getPaddingLeft() + getPaddingEnd();
        int minHeight = (int) (maxPullHeoght * mProgress + getPaddingBottom() + getPaddingTop());

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minWidth, minHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minWidth, height);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, minHeight);
        }

    }

    public void setProgress(float progress) {
        this.mProgress = progress;

        requestLayout();
    }

    /**
     * 更新路径
     */
    private void updateLayout() {
        final float progress = interpolator.getInterpolation(mProgress);

        //可绘制区域宽度
        final float x = getValues(getWidth(), minWidthPull, mProgress);
        //可绘制区域高度
        final float y = getValues(0, maxPullHeoght, mProgress);
        final float circleY = y - circleRadius;
        circlePointX = (int) (x / 2);
        circlePointY = (int) circleY;

        Path path = mPath;
        path.reset();
        path.moveTo(0, 0);

        float degreeGet = MAX_DEGREE * pathInterpolator.getInterpolation(progress);
        float degree = (float) Math.toRadians(degreeGet);
        float disX = (float) (circleRadius * Math.sin(degree));
        float disY = (float) (circleRadius * Math.cos(degree));

        mEndX = circlePointX - disX;
        mEndY = circlePointY + disY;

        mControlY = getValues(0, CONTROL_HEIGHT, progress);
        double disHeight = mEndY - mControlY;
        mControlX = (float) (mEndX - (disHeight / Math.tan(degree)));

        //贝塞尔曲线
        path.quadTo(mControlX, mControlY, mEndX, mEndY);
        path.lineTo(circlePointX + (circlePointX - mEndX), mEndY);
        path.quadTo(circlePointX + (circlePointX - mControlX), mControlY, x, 0);


    }

    /**
     * 获取目前数值
     *
     * @param start
     * @param end
     * @param progress
     * @return
     */
    private float getValues(float start, float end, float progress) {
        return start + (end - start) * progress;
    }

    private ValueAnimator animator;

    /**
     * 自动返回
     */
    public void autoBack() {
        if (animator == null) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(mProgress, 0);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.setDuration(300);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Object o = valueAnimator.getAnimatedValue();
                    if (o instanceof Float) {
                        setProgress((Float) o);
                    }

                }
            });
            animator = valueAnimator;
        } else {
            animator.cancel();
            animator.setFloatValues(mProgress, 0);
        }
        animator.start();
    }
}
