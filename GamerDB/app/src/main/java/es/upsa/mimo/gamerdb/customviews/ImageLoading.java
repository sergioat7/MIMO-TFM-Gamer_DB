package es.upsa.mimo.gamerdb.customviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import es.upsa.mimo.gamerdb.R;

public class ImageLoading extends ConstraintLayout {

    public ImageLoading(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.image_loading, this, true);
    }

    public ImageLoading(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.image_loading, this, true);
        if (attrs != null){
            TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.ImageLoading, 0, 0);
            setAttributes(typed);
        }
    }

    public ImageLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.image_loading, this, true);
        if (attrs != null){
            TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.ImageLoading, 0, 0);
            setAttributes(typed);
        }
    }

    private void setAttributes(TypedArray typed) {

        int color = typed.getColor(R.styleable.ImageLoading_loading_color, 0);
        int textColor = typed.getColor(R.styleable.ImageLoading_text_color, 0);

        ProgressBar pbLoading = findViewById(R.id.progress_bar_loading);
        TextView tvLoading = findViewById(R.id.text_view_loading);
        pbLoading.setIndeterminateTintList(ColorStateList.valueOf(color));
        tvLoading.setTextColor(textColor);
    }
}
