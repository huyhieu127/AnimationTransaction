package com.huyhieu.animationtransaction.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

public class MyDrawable extends Drawable {
    Bitmap bitmap;
    Paint maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int side;
    float radius;

    public MyDrawable(Bitmap wrappedBitmap, int strokeWidth, int color) {
        bitmap = wrappedBitmap;
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(strokeWidth);
        borderPaint.setColor(color);
        side = Math.min(bitmap.getWidth(), bitmap.getHeight());
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        Matrix matrix = new Matrix();
        RectF src = new RectF(0, 0, side, side);
        src.offset((bitmap.getWidth() - side) / 2f, (bitmap.getHeight() - side) / 2f);
        RectF dst = new RectF(bounds);
        dst.inset(borderPaint.getStrokeWidth(), borderPaint.getStrokeWidth());
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shader.setLocalMatrix(matrix);
        maskPaint.setShader(shader);
        matrix.mapRect(src);
        radius = src.width() / 2f;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect b = getBounds();
        canvas.drawCircle(b.exactCenterX(), b.exactCenterY(), radius, maskPaint);
        canvas.drawCircle(b.exactCenterX(), b.exactCenterY(), radius + borderPaint.getStrokeWidth() / 2, borderPaint);
    }

    @Override public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) { }
    @Override public int getOpacity() {return PixelFormat.TRANSLUCENT;}
}