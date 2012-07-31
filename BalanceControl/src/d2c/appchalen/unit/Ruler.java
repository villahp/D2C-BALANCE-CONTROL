package d2c.appchalen.unit;

import android.content.Context;
import android.graphics.Bitmap;
import d2c.appchalen.image.ImageData;
import d2c.appchalen.sprite.MySprite;

public class Ruler extends Unit {
	public Bitmap mRuler;
	public MySprite mSpriteRuler;
	public Ruler(Context context) {
		super(context);
		mRuler = ImageData.Ruler;
		mSpriteRuler = new MySprite(170, 55);
	}
}
