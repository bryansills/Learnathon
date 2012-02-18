package org.gitmad.learnathon;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class CanvasThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private Panel panel;
    private boolean run = false;

    public CanvasThread(SurfaceHolder surfaceHolder, Panel panel) {
        this.surfaceHolder = surfaceHolder;
        this.panel = panel;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        Canvas c;
        while (run) {
            c = null;
            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    panel.onDraw(c);
                }
            } catch (Exception e) {
            	Log.w("SHIT", "WTF");
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
