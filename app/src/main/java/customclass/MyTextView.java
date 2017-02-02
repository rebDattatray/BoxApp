package customclass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rebelute.boxdemoapp.R;


public class MyTextView extends TextView {

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		//Typeface.createFromAsset doesn't work in the layout editor. Skipping...
		if (isInEditMode()) {
			return;
		}

		TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
		String fontName = styledAttrs.getString(R.styleable.TypefacedTextView_typeface);
		styledAttrs.recycle();

		if (fontName != null) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/"+fontName);
			setTypeface(typeface);
		}
	}
}

