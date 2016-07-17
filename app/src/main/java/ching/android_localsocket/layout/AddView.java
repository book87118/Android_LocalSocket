package ching.android_localsocket.layout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import ching.android_localsocket.R;

/**
 * Created by book871181 on 15/12/6.
 * Add a view on top of all the Activities.
 *
 */
public class AddView extends FrameLayout {

    private static String TAG = "AddView";
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View addView;
    private ImageView imageView;

    private boolean isAddView = false;



    /**
    * construct AddView function
    * */
    public AddView(Context context) {
        super(context);
        addView = LayoutInflater.from(context).inflate(R.layout.addview,this);
        imageView = (ImageView)addView.findViewById(R.id.imageView);

        setWindowManager(context);
    }

    /**
     * setting WindowManager
     *   NOT touch and focus
     *
     * **/

    private void setWindowManager(Context context){
        mWindowManager = (WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;

        mLayoutParams.format = PixelFormat.TRANSPARENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        mLayoutParams.gravity = 1;
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

    }

    private  ObjectAnimator hideAnimator;


    /*
    * setting Aniamtor
    * */

    private void setAnimator(){
        hideAnimator = new ObjectAnimator();

        hideAnimator = ObjectAnimator.ofFloat(imageView,"alpha",
                new float[]{0f,1f,0f});


    }
    /**
     * When Local Socket Client get Data(String), it will call this function,
     * And this function will show animatiton  over the screen.
     * For prove Client get the Data.
     * */

    public void showPictures(String result){
        setAnimator();
        AnimatorSet  mAnimatorSet = new AnimatorSet();
        //setting show up period
        mAnimatorSet.setDuration(500);

        mAnimatorSet.setInterpolator(new DecelerateInterpolator());//動畫設置减速

        switch (result){
            case "Up":
                imageView.setImageResource(R.drawable.direction_top);
                mAnimatorSet.play(hideAnimator);
                mAnimatorSet.start();
                break;

            case "Down":
                imageView.setImageResource(R.drawable.direction_down);
                mAnimatorSet.play(hideAnimator);
                mAnimatorSet.start();

                break;
            case "Left":
                imageView.setImageResource(R.drawable.direction_left);
                mAnimatorSet.play(hideAnimator);
                mAnimatorSet.start();

                break;

            case "Right":
                imageView.setImageResource(R.drawable.direction_right);
                mAnimatorSet.play(hideAnimator);
                mAnimatorSet.start();

                break;
            default:
                Toast.makeText(getContext(),"Default!",Toast.LENGTH_SHORT).show();
                break;

        }

    }

    /**
     * click two time connect will break connect and also stop add View
     *
     * */
    public void setAddView(){

        if(isAddView){
            Log.d(TAG,"removeView");
            mWindowManager.removeView(this);
            isAddView = false;
        }else{
            mWindowManager.addView(this,mLayoutParams);
            isAddView = true;

        }
    }

}
