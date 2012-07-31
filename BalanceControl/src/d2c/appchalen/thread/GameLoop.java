package d2c.appchalen.thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import d2c.appchalen.GameView;

public class GameLoop extends Thread{
private SurfaceHolder mSurfaceHolder;
	
	private GameView game;
	private boolean running = false;	

	public GameLoop(SurfaceHolder surfaceHolder, GameView surfaceView) 
	{
		mSurfaceHolder = surfaceHolder;
		game = surfaceView;
	}		

	public void setRunning(boolean b) 
	{
		running = b;
	}
	@Override
	public void run() 
	{
		while (running) 
		{
			Canvas c = null;
			try 
			{
				game.mGameTimeLoop = System.currentTimeMillis();
				c = mSurfaceHolder.lockCanvas(null);
				synchronized (mSurfaceHolder) 
				{
					game.mHand.moveHand();
					game.onDraw(c);
				}
				}
			catch (Exception e)
			{
				e.printStackTrace();
			} 
			finally 
			{
				if (c != null) 
				{
					mSurfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}
}
