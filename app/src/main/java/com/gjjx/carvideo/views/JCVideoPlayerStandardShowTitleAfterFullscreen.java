package com.gjjx.carvideo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by ylbf_ on 2016/7/1.
 */
public class JCVideoPlayerStandardShowTitleAfterFullscreen extends JCVideoPlayerStandard {
    public JCVideoPlayerStandardShowTitleAfterFullscreen(Context context) {
        super(context);
    }

    public JCVideoPlayerStandardShowTitleAfterFullscreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean setUp(String url, Object... objects) {
        if (super.setUp(url, objects)) {
            if (mIfCurrentIsFullscreen) {
                titleTextView.setVisibility(View.VISIBLE);
            } else {
                titleTextView.setVisibility(View.INVISIBLE);
            }
            return true;
        }
        return false;

    }
}
