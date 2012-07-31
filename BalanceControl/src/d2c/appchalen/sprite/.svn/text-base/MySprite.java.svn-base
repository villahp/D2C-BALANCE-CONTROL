package d2c.appchalen.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class MySprite {
	public Bitmap src;	//Ảnh chứa sprite sheet
    public float xPos;	//Vị trí left của sprite trên screen
    public float yPos;	//Vị trí top của sprite trên screen
    private float xPosCenter;	// Vị trí chính giữa tâm của sprite trên screen
    private float yPosCenter;	// Vị trí chính giữa tâm của sprite trên screen
    private Rect rect;	//Hình chữ nhật bao quanh một ô trong sheet
    private int fps;	//Frame per second
    private int totalFrame;	//Tổng số frame
    /**Frame hiện thời, bắt đầu từ 0*/
    public int currentFrame;	
    private long frameTime;	//Thời gian để chạy một frame, thường bằng 0 để lấy thời gian là fps
    private int spriteHeight;	//Chiều cao của sprite
    private int spriteWidth;	// Chiều rộng của sprite
    
    public MySprite() {
        rect = new Rect(0,0,0,0);
        frameTime =0;
        currentFrame =0;
        xPos = 0;
        yPos = 0;
    }
    
    /**Khởi tạo sprite với vị trí ban đầu (left,top) trên screen*/
    public MySprite(float xPos,float yPos)
    {
    	rect = new Rect(0,0,0,0);
        frameTime =0;
        currentFrame =0;
    	this.xPos=xPos;
    	this.yPos=yPos;
    }
    
    /**Khởi tạo sprite với vị trí ban đầu ở chính giữa tâm sprite trên screen*/
    public MySprite(float xPosCenter,float yPosCenter,boolean b)
    {
    	rect = new Rect(0,0,0,0);
        frameTime =0;
        currentFrame =0;
    	this.xPosCenter=xPosCenter;
    	this.yPosCenter=yPosCenter;
    }
 
    /**Khởi tạo các giá trị như ảnh, chiều cao, rộng, fps, tổng số frame*/
    public void init(Bitmap theBitmap, int Width, int Height, int theFPS, int theFrameCount) {
        src = theBitmap;
        spriteHeight = Height;
        spriteWidth = Width;
        rect.top = 0;
        rect.bottom = spriteHeight;
        rect.left = 0;
        rect.right = spriteWidth;
        fps = 1000 /theFPS;
        totalFrame = theFrameCount;
        
        //Log.d(Tag,"mSpriteHeight="+spriteHeight+" "+"mSpriteWidth="+spriteWidth);
    }
    
    public void init(Bitmap theBitmap)
    {
    	src = theBitmap;
    	spriteHeight = theBitmap.getHeight();
    	spriteWidth = theBitmap.getWidth();
    	rect.top = 0;
        rect.bottom = spriteHeight;
        rect.left = 0;
        rect.right = spriteWidth;
        fps = 1000 /1;
        totalFrame = 0;
    }
    boolean direct = true;
    /**Chạy sprite liên tục*/
    public void loop(long GameTime) {
        if(GameTime > frameTime + fps ) {
            frameTime = GameTime;
            currentFrame += 1;
            if(currentFrame >= totalFrame) {
                currentFrame = 0;
            }
        }
        //Log.d(Tag,"currentFrame="+currentFrame);
        rect.left = currentFrame * spriteWidth;
        rect.right = rect.left + spriteWidth;
    }
    /**Chạy sprite một lần cho đến frame được chỉ ra
     * .Thứ tự frame bắt đầu từ 1*/
    public void playToFrame(long GameTime, int frame)
    {
    	if ((currentFrame<totalFrame)&&(GameTime > frameTime + fps)) 
    	{
            frameTime = GameTime;
            currentFrame +=1;   
            if(currentFrame >= frame) {
                currentFrame = frame-1;
            }
        }                
    	
    	//Log.d(Tag,"currentFrame="+currentFrame);
    	
    	rect.left = currentFrame * spriteWidth;
        rect.right = rect.left + spriteWidth;    	
    }
    
    /**Chạy sprite một lần và quay trở lại frame đầu tiên*/
    public void playToBegin(long GameTime)
    {
    	boolean isPlaying=true;
    	if ((currentFrame<totalFrame)&&(GameTime > frameTime + fps)&&isPlaying) 
    	{
            frameTime = GameTime;
            currentFrame +=1;   
            if(currentFrame >= totalFrame) {
                currentFrame = 0;
                isPlaying=false;
            }
        }                
    	
    	//Log.d(Tag,"currentFrame="+currentFrame);
    	
    	rect.left = currentFrame * spriteWidth;
        rect.right = rect.left + spriteWidth;    	
    }
    
    /**Chuyển đến frame được đánh số bắt đầu từ 1*/
    public void gotoAndStop(int frame)
    {
    	currentFrame=frame-1;
    	rect.left = currentFrame * spriteWidth;
        rect.right = rect.left + spriteWidth; 
    }
    
    /**Vẽ sprite*/
    public void draw(Canvas canvas) {
        Rect dest = new Rect((int)getXPos(), (int)getYPos(), (int)getXPos() + spriteWidth,
        		(int)getYPos() + spriteHeight);
        canvas.drawBitmap(src, rect, dest, null);
    }
    
    
    /**Vẽ sprite với biến trans để làm mờ*/
    public void draw(Canvas canvas, int trans) 
    {
    	Paint paint=new Paint();
    	paint.setAlpha(trans);
        Rect dest = new Rect((int)getXPos(), (int)getYPos(), (int)getXPos() + spriteWidth,
        		(int)getYPos() + spriteHeight);
        canvas.drawBitmap(src, rect, dest, paint);
    }
    
    /**Vẽ sprite tính từ điểm trung tâm với biến trans để làm mờ*/
    public void drawCenter(Canvas canvas, int trans) 
    {
    	Paint paint=new Paint();
    	paint.setAlpha(trans);
        Rect dest = new Rect((int)(getXPosCenter()-spriteWidth/2), (int)(getYPosCenter()-spriteHeight/2), 
        		(int)(getXPosCenter() + spriteWidth/2), (int)(getYPosCenter() + spriteHeight/2));
        canvas.drawBitmap(src, rect, dest, paint);
    }    
    
    /**Dùng để xóa Sprite khỏi screen*/
    public void remove()
    {
    	src=null;
    }
    
    public float getXPos()
    {
    	return xPos;
    }
    
    public float getYPos()
    {
    	return yPos;
    }
    
    public float getXPosCenter()
    {
    	return xPosCenter;
    }    
    
    public float getYPosCenter()
    {
    	return yPosCenter;
    }
    
    public void setXPos(int xPos)
    {
    	this.xPos=xPos;
    }
    
    public void setYPos(int yPos)
    {
    	this.yPos=yPos;
    }
    
    public void setXPosCenter(float xPosCenter)
    {
    	this.xPosCenter=xPosCenter;
    }
    
    public void setYPosCenter(float yPosCenter)
    {
    	this.yPosCenter=yPosCenter;
    }
}
