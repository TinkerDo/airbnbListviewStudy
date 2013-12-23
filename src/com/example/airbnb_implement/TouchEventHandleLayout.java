package com.example.airbnb_implement;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TouchEventHandleLayout extends LinearLayout {
	private int mTouchSlop;

	public TouchEventHandleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		ViewConfiguration vc = ViewConfiguration.get(context);
		mTouchSlop = vc.getScaledTouchSlop();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = MotionEventCompat.getActionMasked(ev);		
		if (action == MotionEvent.ACTION_MOVE) {
			if (ev.getHistorySize() > 1) {
				float diffY = ev.getY() - ev.getHistoricalY(0);
				android.util.Log.v("ids", "diffY: " + diffY);
				if (diffY > 10) {
					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this
							.getLayoutParams();
					params.topMargin += diffY;
					android.util.Log.v("ids", "diffY: " + diffY + " , topMargin: " + params.topMargin);
					this.setLayoutParams(params);					
				}
				android.util.Log.v("ids", "move: " + ev.getY());
				return true;
			}		
		}		
		if (action == MotionEvent.ACTION_SCROLL){
			float diffY = ev.getY() - ev.getHistoricalY(0);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this
					.getLayoutParams();
			params.topMargin += diffY;
			android.util.Log.v("ids", "diffY: " + diffY + " , topMargin: " + params.topMargin);
			this.setLayoutParams(params);
			return true;
		}
		android.util.Log.v("ids", "other: " + ev.getY());
		return super.onInterceptTouchEvent(ev);
	}

}
