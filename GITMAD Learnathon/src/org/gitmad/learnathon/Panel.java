package org.gitmad.learnathon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class Panel extends SurfaceView implements SurfaceHolder.Callback{
	private CanvasThread canvasthread;
	
    public Panel(Context context, AttributeSet attrs) {
		super(context, attrs); 
	    getHolder().addCallback(this);
	    canvasthread = new CanvasThread(getHolder(), this);
	    setFocusable(true);
	}

	public Panel(Context context) {
		super(context);
		getHolder().addCallback(this);
		canvasthread = new CanvasThread(getHolder(), this);
		setFocusable(true);
    }

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
	    paint.setColor(Color.YELLOW);
	    canvas.drawCircle(50, 50, 25, paint);
	}
	 
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
	    canvasthread.setRunning(true);
	    canvasthread.start();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		canvasthread.setRunning(false);
		while (retry) {
			try {
				canvasthread.join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}
	}
}   