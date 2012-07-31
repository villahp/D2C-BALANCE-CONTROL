package d2c.appchalen;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import d2c.appchalen.image.ImageData;

public class GameActivity extends Activity implements SensorEventListener{
	GameView mGameView;
	Context mContext;
	SensorManager mSensorManager;
	Sensor mAccelerometers;
	public static double mAngle;
	public double lastAngle = mAngle;
	public static double deltaAngle;
	public static float mLength = ImageData.Ruler.getHeight()/2;
	private float[] gravity = new float[3];
	private double ratio;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		mAccelerometers = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mContext = this;
		mGameView = new GameView(mContext);
		setContentView(mGameView);
	}
	
	@Override
	protected void onResume() {
		mSensorManager.registerListener(this, mAccelerometers,
				SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(this, mAccelerometers);
		super.onPause();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//ignore
	}

	public void onSensorChanged(SensorEvent event) {
		//get x y
		mGameView.mHand.mSpriteHand.xPos = mGameView.mHand.mSpriteHand.xPos - event.values[0]*3F;
		mGameView.mHand.mSpriteHand.yPos = mGameView.mHand.mSpriteHand.yPos + event.values[1]*3F;
		mGameView.mRuler.mSpriteRuler.xPos = mGameView.mHand.mSpriteHand.xPos + 15.2F*mGameView.mHand.mHand.getWidth()/20;
		mGameView.mRuler.mSpriteRuler.yPos = mGameView.mHand.mSpriteHand.yPos - mGameView.mRuler.mRuler.getHeight() + 50;
		for (int i = 0; i < 3; i++) {
			gravity[i] = (float) (0.1 * event.values[i] + 0.9 * gravity[i]);
		}
		
		ratio = gravity[1] / SensorManager.GRAVITY_EARTH;
		if (ratio > 1.0)
			ratio = 1.0;
		if (ratio < -1.0)
			ratio = -1.0;
		mAngle = Math.toDegrees(Math.acos(ratio));
		if(mAngle != lastAngle)
		{
			deltaAngle = mAngle - lastAngle;
			lastAngle = mAngle;
		}
//		if (gravity[2] < 0)
//			mAngle = -mAngle;
//		Log.d("angle",	": " + Math.toDegrees(mAngle));
	}
	
}
