package bego.com.alqowyy.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class Tv_P188 extends TextView {
    public Tv_P188(Context context) {
        super(context);
        init();
    }
    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/QCF_188.TTF");
            setTypeface(tf);
        }
    }
    public Tv_P188(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Tv_P188(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Tv_P188(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}
