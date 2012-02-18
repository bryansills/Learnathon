package org.gitmad.learnathon;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GITMADLearnathonActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Panel panel = (Panel)findViewById(R.id.SurfaceView01);
        
        OnTouchListener listener = new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				float xCo = event.getX();
				float yCo = event.getY();
//				int time = (int) event.getHistorySize();
				panel.addCircle(xCo, yCo);
				return true;
			}
        };
        panel.setOnTouchListener(listener);
    }
}