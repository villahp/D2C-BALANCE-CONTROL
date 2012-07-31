package d2c.appchalen.unit;

import android.content.Context;
import android.graphics.Bitmap;
import d2c.appchalen.SplashScreen;
import d2c.appchalen.image.ImageData;
import d2c.appchalen.sprite.MySprite;

public class Hand extends Unit{
	public Bitmap mHand;
	public MySprite mSpriteHand;
	public Hand(Context context) {
		super(context);
		mHand = ImageData.Hand;
		mSpriteHand = new MySprite(10, 290);
	}
	
	public void moveHand()
	{
		// Gioi han x
		if(mSpriteHand.xPos < - mHand.getWidth()/3)
			mSpriteHand.xPos = - mHand.getWidth()/3;
		if(mSpriteHand.xPos >= SplashScreen.SCREENWIDTH - 5*mHand.getWidth()/4)
			mSpriteHand.xPos = SplashScreen.SCREENWIDTH - 5*mHand.getWidth()/4;
		// Gioi han y
		if(mSpriteHand.yPos > SplashScreen.SCREENHEIGHT - 2*mHand.getHeight()/3)
			mSpriteHand.yPos = SplashScreen.SCREENHEIGHT - 2*mHand.getHeight()/3;
		if(mSpriteHand.yPos < SplashScreen.SCREENHEIGHT/2)
			mSpriteHand.yPos = SplashScreen.SCREENHEIGHT/2;
	}
}
