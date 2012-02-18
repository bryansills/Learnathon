package org.gitmad.learnathon;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class Panel extends SurfaceView implements SurfaceHolder.Callback{
	private CanvasThread canvasthread;
	private ArrayList<PointF> circleList;
	private ArrayList<Circle> circleSizeList;
	
    public Panel(Context context, AttributeSet attrs) {
		super(context, attrs); 
	    getHolder().addCallback(this);
	    canvasthread = new CanvasThread(getHolder(), this);
	    setFocusable(true);
	    circleList = new ArrayList<PointF>();
	    circleSizeList = new ArrayList<Circle>();
	}

	public Panel(Context context) {
		super(context);
		getHolder().addCallback(this);
		canvasthread = new CanvasThread(getHolder(), this);
		setFocusable(true);
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

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
	    paint.setColor(Color.YELLOW);

	    for (PointF circle: circleList) {
	    	canvas.drawCircle(circle.x, circle.y, 20, paint);
	    }
	}

	public void addCircle(float xCo, float yCo) {
		circleList.add(new PointF(xCo, yCo));
	}

	public void addSizeCircle(float xCo, float yCo, int time) {
		circleSizeList.add(new Circle(xCo, yCo, time));
	}
}   