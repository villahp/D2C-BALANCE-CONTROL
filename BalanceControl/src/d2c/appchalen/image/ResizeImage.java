package d2c.appchalen.image;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ResizeImage {
	public static Bitmap getResizedBitmap(Bitmap bm, float newHeight,
			float newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = newWidth / width;
		float scaleHeight = newHeight / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}
}
