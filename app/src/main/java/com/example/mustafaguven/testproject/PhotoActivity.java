package com.example.mustafaguven.testproject;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.ColorMatrix;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class PhotoActivity extends ActionBarActivity {

    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerator = new AccelerateInterpolator();
    static final int duration = 500;

    private ColorMatrix colorizerMatrix = new ColorMatrix();
    ColorDrawable mBackground;
    int mLeftDelta;
    int mTopDelta;
    float mWidthScale;
    float mHeightScale;
    private ImageView mImageView;
    private TextView mTextView;
    private RelativeLayout rlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    void init(){
        setContentView(R.layout.activity_photo);
        mImageView = (ImageView) findViewById(R.id.imgUser);
        rlMain = (RelativeLayout) findViewById(R.id.rlMain);
        mTextView = (TextView) findViewById(R.id.lblFullname);

        Bundle bundle = getIntent().getExtras();
        Drawable drawable = getResources().getDrawable(bundle.getInt("resourceId"));
        String description = bundle.getString("description");
        final int thumbnailTop = bundle.getInt("top");
        final int thumbnailLeft = bundle.getInt("left");
        final int thumbnailWidth = bundle.getInt("width");
        final int thumbnailHeight = bundle.getInt("height");

        setTitle(description);
        mImageView.setImageDrawable(drawable);
        mTextView.setText(description);

        mBackground = new ColorDrawable(bundle.getInt("backgroundColor"));
        rlMain.setBackground(mBackground);

        ViewTreeObserver observer = mImageView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                mImageView.getViewTreeObserver().removeOnPreDrawListener(this);

                int[] screenLocation = new int[2];
                mImageView.getLocationOnScreen(screenLocation);
                mLeftDelta = thumbnailLeft - screenLocation[0];
                mTopDelta = thumbnailTop - screenLocation[1];
                mWidthScale = (float) thumbnailWidth / mImageView.getWidth();
                mHeightScale = (float) thumbnailHeight / mImageView.getHeight();
                runEnterAnimation();

                return true;
            }
        });
    }

    public void runEnterAnimation() {
        mImageView.setPivotX(0);
        mImageView.setPivotY(0);
        mImageView.setScaleX(mWidthScale);
        mImageView.setScaleY(mHeightScale);
        mImageView.setTranslationX(mLeftDelta);
        mImageView.setTranslationY(mTopDelta);
        mTextView.setAlpha(0);
        mImageView.animate().setDuration(duration).
                scaleX(1).scaleY(1).
                translationX(0).translationY(0).
                setInterpolator(sDecelerator).
                withEndAction(new Runnable() {
                    public void run() {
                        mTextView.setTranslationY(-mTextView.getHeight());
                        mTextView.animate().setDuration(duration/2).
                                translationY(0).alpha(1).
                                setInterpolator(sDecelerator);
                    }
                });

        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0, 255);
        bgAnim.setDuration(duration);
        bgAnim.start();

        ObjectAnimator colorizer = ObjectAnimator.ofFloat(PhotoActivity.this, "saturation", 0, 1);
        colorizer.setDuration(duration);
        colorizer.start();
    }

    public void runExitAnimation(final Runnable endAction) {

        mTextView.animate().translationY(-mTextView.getHeight()).alpha(0).
                setDuration(duration/2).setInterpolator(sAccelerator).
                withEndAction(new Runnable() {
                    public void run() {
                        mImageView.animate().setDuration(duration).
                                scaleX(mWidthScale).scaleY(mHeightScale).
                                translationX(mLeftDelta).translationY(mTopDelta).
                                withEndAction(endAction);
                        mImageView.animate().alpha(0);
                        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0);
                        bgAnim.setDuration(duration);
                        bgAnim.start();

                        ObjectAnimator colorizer = ObjectAnimator.ofFloat(PhotoActivity.this, "saturation", 1, 0);
                        colorizer.setDuration(duration);
                        colorizer.start();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        runExitAnimation(new Runnable() {
            public void run() {
                finish();
            }
        });
    }


/*    public void setSaturation(float value) {
        colorizerMatrix.setSaturation(value);
        ColorMatrixColorFilter colorizerFilter = new ColorMatrixColorFilter(colorizerMatrix);
        mBitmapDrawable.setColorFilter(colorizerFilter);
    }*/

/*    ImageView getImage(){
        return (ImageView)findViewById(R.id.imgTest);
    }

    TextView getTextView(){
        return (TextView)findViewById(R.id.lblTest);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
