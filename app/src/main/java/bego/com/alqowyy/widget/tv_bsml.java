package bego.com.alqowyy.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class tv_bsml extends TextView {
    public tv_bsml(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/QCF_BSML.TTF");
            setTypeface(tf);
        }
    }

    public tv_bsml(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public tv_bsml(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public tv_bsml(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}
