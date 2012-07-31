package d2c.appchalen;

import d2c.appchalen.image.ImageData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashScreen extends Activity implements Runnable{

	public static DisplayMetrics mDisplayMetrics;
	public static int SCREENWIDTH;
	public static int SCREENHEIGHT;
	public float wp,hp;
	public final int DEFAULT_WIDTH = 500;
	public final int DEFAULT_HEIGHT = 600;
	Thread threadsplash;
	ImageView background;
	boolean active = true;
	long timestart = 2000;
	//
//	SoundPool soundpool;
//	int soundplay;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// khai bao khong su dung tieu de
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash_screen);
		mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		SCREENWIDTH = mDisplayMetrics.widthPixels;
        SCREENHEIGHT = mDisplayMetrics.heightPixels;
        Log.d("Tag", "x: " + SCREENWIDTH + " y: " + SCREENHEIGHT);
        wp = (float) SCREENWIDTH / DEFAULT_WIDTH;
		hp = (float) SCREENHEIGHT / DEFAULT_HEIGHT;
		// khai bao cai anh nen logo
		background = (ImageView) findViewById(R.id.logo);
		background.setBackgroundResource(R.drawable.splash);
		// bien nay bang true de noi len ko co hanh dong tac dong len man hinh luc no dang quang cao logo
		active = true;
		// khai bao doi tuong de play 1 file am thanh.
//		soundpool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
//		soundplay = soundpool.load(this, R.raw.intro, 0);
		
		ImageData.loadData(this);
		ImageData.resizeImage();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	// ham nay duoc goi khi man hinh dien thoai sang, duoc goi sau ham onResume()
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// neu hien tai ko co tuyen nao chay thi se khoi tao thread moi de chay qua trinh gioi thieu logo
		if (threadsplash == null) {
			threadsplash = new Thread(this);
			threadsplash.start();
		}
		super.onWindowFocusChanged(hasFocus);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		threadsplash = null;
//		soundpool.stop(AudioManager.STREAM_MUSIC);
	}
	
	// phuong thuc nay duoc goi ngay khi thread chay
	public void run() {
        // thuc hien cac lenh trong khoi try
		try {
			while (active && (timestart >= 0)) {
				timestart -= 20;
//				soundpool.play(soundplay, 0f, 1f, 0, 0, 1.5f);
				Thread.sleep(100);
			}

		} catch (InterruptedException e) {
		} finally {
			startActivity(new Intent(getApplicationContext(),MainMenu.class));
			finish();
		}
	}

	// ham nay de xu li khi co su kien cham tay vao man hinh,
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			active = false;
		}
		return super.onTouchEvent(event);
	}
    
    
}
