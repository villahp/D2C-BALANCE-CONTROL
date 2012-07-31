package d2c.appchalen.unit;

import android.content.Context;

public class Unit {
	public float x = 0;
	public float y = 0;
	
	public Unit(Context context) {
		this.x = 0;
		this.y = 0;
	}
	
	public Unit(Context context,float x,float y) {
		this.x = x;
		this.y = y;
	}
}
