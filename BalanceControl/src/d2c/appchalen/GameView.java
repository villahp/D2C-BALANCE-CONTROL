package d2c.appchalen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import d2c.appchalen.image.ImageData;
import d2c.appchalen.thread.GameLoop;
import d2c.appchalen.unit.Hand;
import d2c.appchalen.unit.Ruler;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	public long mGameTimeLoop;
	private GameLoop thread;
	public Hand mHand;
	public Ruler mRuler;
	public GameView(Context context) {
		super(context);
		init();
		mHand = new Hand(context);
		mHand.mSpriteHand.init(mHand.mHand);
		mRuler = new Ruler(context);
		mRuler.mSpriteRuler.init(mRuler.mRuler);
	}
	
	public void init()
	{
		getHolder().addCallback(this);
		thread = new GameLoop(getHolder(), this);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		mHand.mSpriteHand.draw(canvas);
//		mRuler.mSpriteRuler.draw(canvas);
		drawRuler(canvas);
//		Log.d("ruler_pos", "x: " + mHand.mSpriteHand.xPos + " y: " + mHand.mSpriteHand.yPos);
	}
	
	public void drawRuler(Canvas canvas)
	{
		Paint mPaint = new Paint();
		Bitmap mLocalBitmap = ImageData.Ruler;
		Matrix mLocalMatrix = new Matrix();
		mLocalMatrix.setRotate((float)Math.round(-1.0F*(float)(180.0F * GameActivity.deltaAngle / Math.PI)));
		Bitmap mLocalBitmap2 = Bitmap.createBitmap(mLocalBitmap, 0 , 0, mLocalBitmap.getWidth(), mLocalBitmap.getHeight() , mLocalMatrix, true);
	    mRuler.mSpriteRuler.xPos = (float)(mHand.mSpriteHand.xPos + 15 * mHand.mHand.getWidth()/20 - mLocalBitmap.getHeight() * Math.sin(GameActivity.deltaAngle));
	    mRuler.mSpriteRuler.yPos = (float)(mHand.mSpriteHand.yPos + 50 - mLocalBitmap.getHeight() * Math.cos(GameActivity.deltaAngle));
		canvas.drawText("Angle: " + GameActivity.mAngle, 20, 20, mPaint);
		canvas.drawText("Delta Angle: " + Math.toDegrees(GameActivity.deltaAngle), 20, 40, mPaint);
	    canvas.drawText("xRuler: " + mRuler.mSpriteRuler.xPos + " yRuler: "+  mRuler.mSpriteRuler.yPos, 20, 60, mPaint);
	    canvas.drawBitmap(mLocalBitmap2, mRuler.mSpriteRuler.xPos, mRuler.mSpriteRuler.yPos, mPaint);
	    mLocalBitmap2.recycle();
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if(thread.getState() == Thread.State.TERMINATED)
		{
			thread = new GameLoop(getHolder(), this);
		}
		thread.setRunning(true);
		try {
			thread.start();
		} catch (IllegalThreadStateException e) {
			e.printStackTrace();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry)
		{
			try
			{
				thread.join();
				retry = false;
			}
			catch (InterruptedException e){}
		}
	}

}
