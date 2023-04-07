package com.example.randombutton;

import static com.example.randombutton.R.string.successful_search;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randombutton.R;

public class MainActivity extends AppCompatActivity {


    private TextView coordinatesOut;
    private float x;
    private float y;
    private String sDown;
    private String sMove;
    private String sUp;


    private final float xCat = 500;
    private final float yCat = 500;
    private final float deltaCat = 50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatesOut = findViewById(R.id.coordinatesOut);

        coordinatesOut.setOnTouchListener(listener);
    }
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sDown = "Нажатие: координата X = " + x + ", координата y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE:
                    sMove = "Движение: координата X = " + x + ", координата y = " + y;

                    if (x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (yCat + deltaCat) && y > (yCat - deltaCat)) {

                       showToast(view, (int) xCat, (int) yCat);

//                        Toast toast = Toast.makeText(getApplicationContext(),"Вы нашли кота", Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0,0);
//                        ImageView cat = new ImageView(getApplicationContext());
//                        cat.setImageResource(R.drawable.img);
//                        toast.setView(cat);
//                        // linearLayout = (LinearLayout) toast.getView();
//                        //linearLayout.addView(cat, 1);
//                       toast.show();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = "";
                    sUp = "Отпускание: координата X = " + x + ", координата y = " + y;
                    break;
            }

            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);

            return true;
        }
    };

    public void showToast(View view, int xCat, int yCat) {

        LayoutInflater inflater = getLayoutInflater();

        view = inflater.inflate(R.layout.toast_image_layout,
                (ViewGroup) findViewById(R.id.relativeLayoutToast));
        Toast toast = new Toast(getApplicationContext());

        toast.setGravity(Gravity.BOTTOM, (int) xCat, (int) yCat + 300);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();

    }

}
