package  com.cgollner.circledimageview;

import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * CircledImageView: Draws circled images
 * 
 * @author Christian Göllner
 *
 */
public class CircledImageView extends ImageView {
	public boolean mCircled;

	public CircledImageView(Context context) {
		super(context);
	}
	
	public CircledImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public CircledImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	/**
	 * In case the bitmap is manually changed, we make sure to 
	 * circle it on the next onDraw
	 */
	@Override
	public void setImageBitmap(Bitmap bm) {
		mCircled = false;
		super.setImageBitmap(bm);
	}

	/**
	 * In case the bitmap is manually changed, we make sure to 
	 * circle it on the next onDraw
	 */
	@Override
	public void setImageResource(int resId) {
		mCircled = false;
		super.setImageResource(resId);
	}
	
	/**
	 * In case the bitmap is manually changed, we make sure to 
	 * circle it on the next onDraw
	 */
	@Override
	public void setImageDrawable(Drawable drawable) {
		mCircled = false;
		super.setImageDrawable(drawable);
	}
	
	/**
	 * We want to make sure that the ImageView has the same height and width
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Drawable drawable = getDrawable();
		if (drawable != null) {
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int diw = drawable.getIntrinsicWidth();
			if (diw > 0) {
				int height = width * drawable.getIntrinsicHeight() / diw;
				setMeasuredDimension(width, height);
			} else
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		} else
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//Let's circle the image
		if ( !mCircled && getDrawable() != null) {
			Drawable d = getDrawable();
			try {
				//We use reflection here in case that the drawable isn't a 
				//BitmapDrawable but it contains a public getBitmap method.
				Bitmap bitmap = (Bitmap) d.getClass().getMethod("getBitmap").invoke(d);
				Bitmap circleBitmap = getCircleBitmap(bitmap);
				setImageBitmap(circleBitmap);	
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			} catch (NoSuchMethodException e) {
				//Seems like the current drawable is not a BitmapDrawable or 
				//that is doesn't have a public getBitmap() method.
			}
			
			//Mark as circled even if it failed, because if it fails once,
			//It will fail again.
			mCircled = true;
		}
		super.onDraw(canvas);
	}
	
	/**
	 * Method used to circle a bitmap.
	 * 
	 * @param bitmap The bitmap to circle
	 * @return The circled bitmap
	 */
	public static Bitmap getCircleBitmap(Bitmap bitmap) {
		int size = Math.min(bitmap.getWidth(), bitmap.getHeight());

		Bitmap output = Bitmap.createBitmap(size,
				size, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		BitmapShader shader;
		shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
				Shader.TileMode.CLAMP);

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(shader);

		RectF rect = new RectF(0, 0 ,size,size);
		int radius = size/2;
		canvas.drawRoundRect(rect, radius,radius, paint);
		return output;
	}
}
