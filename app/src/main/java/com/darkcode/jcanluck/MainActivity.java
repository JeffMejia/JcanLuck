package com.darkcode.jcanluck;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;
import android.view.View;

//Logo de Rise Against - hecho en canvas.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView vista2 = new MyView(this);
        setContentView(vista2);

    }

    public class MyView extends View {

        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.rgb(0, 0, 0));

        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            Sopotekun herd = new Sopotekun(canvas, paint);

            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);

            herd.Estrellas(2);
            herd.flecha1(360, 378);
            herd.Kora();
            herd.puño();
            herd.frase(346, 720, 3, 2);


        }

    }
}
