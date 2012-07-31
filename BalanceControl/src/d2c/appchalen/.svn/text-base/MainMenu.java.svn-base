package d2c.appchalen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{
	Button btnSetting;
	Button btnNewGame;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_mainmenu);
		btnNewGame = (Button)findViewById(R.id.New);
		btnNewGame.setOnClickListener(this);
		btnSetting = (Button)findViewById(R.id.Setting);
		btnSetting.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.New:
			startActivity(new Intent(MainMenu.this,GameActivity.class));
			break;
		case R.id.Setting:
			startActivity(new Intent(MainMenu.this, Settings.class));
			break;
		default:
			break;
		}
	}
}
