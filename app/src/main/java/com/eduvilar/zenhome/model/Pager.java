package com.eduvilar.zenhome.model;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.base.FragmentAdapter;
import com.eduvilar.zenhome.callback.FragmentChangeCallback;

import java.lang.reflect.Field;

/**
 * Created by Efra on 14/04/2016.
 */
public class Pager extends ViewPager implements ViewPager.PageTransformer {

    private BaseFragment[] fragments;
    private FragmentManager manager;
    private FixedSpeedScroller mScroller = null;
    private FragmentChangeCallback callback;

    public Pager(Context context) {
        super(context);
    }

    protected OnPageChangeListener listener;

    public Pager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        //if (ha != null && ha.viewPager.getCurrentItem() != ha.getFragmentPosition(new ListDocksFragment())) return false;
        // return super.onInterceptTouchEvent(event);
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        // return super.onTouchEvent(event);
        return false;
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        super.setOnPageChangeListener(listener);
        this.listener = listener;
    }

    public void setCurrentItem(Class<? extends BaseFragment> clazz) {
        setCurrentItem(clazz, false);
    }

    public void setCurrentItem(Class<? extends BaseFragment> clazz, boolean transition) {
        int i = -1;
        for (int t = 0; t < fragments.length; t++) {
            if (fragments[t].getClass().getSimpleName().equals(clazz.getSimpleName())) {
                i = t;
                break;
            }
        }
        if (i > -1) {
            setCurrentItem(i, transition);
            if (fragments[i].isViewReady()) {
                fragments[i].init();
            } else {
                final int a = i;
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        fragments[a].init();
                    }
                };
                handler.postDelayed(runnable, 200);
            }
        }
    }

    @Override
    public void setCurrentItem(int item, boolean transition) {
        boolean invokeMeLater = false;

        Class<? extends BaseFragment> clazz = fragments[item].getClass();

        if(super.getCurrentItem() == 0 && item == 0)
            invokeMeLater = true;

        if(invokeMeLater && listener != null)
            listener.onPageSelected(0);

        if (callback != null) {
            callback.fragmentChanged(clazz);
        }

        super.setCurrentItem(item, transition);
    }

    @Override
    public void transformPage(View view, float position) {
        if ((int) view.getTag() == getCurrentItem()) {
            if (position <= -1.0F || position >= 1.0F) {
                view.setTranslationX(view.getWidth() * position);
                view.setAlpha(0.0F);
            } else if (position == 0.0F) {
                view.setTranslationX(view.getWidth() * position);
                view.setAlpha(1.0F);
            } else {
                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                view.setTranslationX(view.getWidth() * -position);
                view.setAlpha(1.0F - Math.abs(position));
            }
        } else {
            view.setAlpha(0.0F);
            if (position <= -1.0F || position >= 1.0F) {
                view.setTranslationX(view.getWidth() * position);
            }
        }
    }

    /*
     * Set the factor by which the duration will change
     */
    public void setScrollDuration(int duration) {
        mScroller.setScrollDuration(duration);
    }

    public BaseFragment getCurrentFragment() {
        return fragments[getCurrentItem()];
    }


    /*
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    public void init(Context context, BaseFragment[] fragments, FragmentManager manager, FragmentChangeCallback callback) {
        try {
            setPageTransformer(false, this);
            this.fragments = fragments;
            this.manager = manager;
            this.callback = callback;
            setAdapter(new FragmentAdapter(this.manager, this.fragments));
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            mScroller = new FixedSpeedScroller(context, new DecelerateInterpolator());
            setScrollDuration(500);
            scroller.set(this, mScroller);
        } catch (Exception ignored) {
        }
    }

    private class FixedSpeedScroller extends Scroller {

        private int mDuration = 200;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setScrollDuration(int duration) {
            mDuration = duration;
        }
    }
}