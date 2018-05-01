package com.darkcode.jcanluck;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

//Logo de Rise Against - hecho en canvas.
public class MainActivity extends AppCompatActivity {
    int cont = 0;//Menu Principal
    int contd = 0;//Botones del Sub menus
    int xT=0,yT=0;//Cordenadas para Traslacion
    int angle =0;//Angulo del mov de las flechas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView vista = new MyView(this);
        setContentView(vista);

    }

    public class MyView extends View {
        Drawable back;
        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.rgb(0, 0, 0));
            //Agregando una Imagen al Canvas
            back = context.getResources().getDrawable(R.drawable.back);

        }

        protected void onDraw(Canvas canvas) {
            //posicionando la imagen en el canvas
            back.setBounds(460,20,520,70);

            super.onDraw(canvas);
            Paint paint = new Paint();
            Sopotekun herd = new Sopotekun(canvas, paint);

            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);

            herd.Estrellas(2);
            herd.flechas(360, 378,angle);
            herd.Kora();
            herd.punio();
            herd.frase(xT, yT, 3, 2);

            //Botones
            paint.setColor(Color.GREEN);
            canvas.drawRect(new RectF(10,20,180,70),paint);
            canvas.drawRect(new RectF(220,20,420,70),paint);
            canvas.drawRect(new RectF(560,20,700,70),paint);
            //mantiene oculto al Boton "Regresar" mientras no se encuentre en algun sub-menu
            herd.bBack();
            //texto sobre los botones
            paint.setTextSize(30);
            paint.setFakeBoldText(true);
            paint.setColor(Color.BLACK);
            canvas.drawText("ROTACION.",20,60,paint);
            canvas.drawText("TRASLACION.",220,60,paint);
            canvas.drawText("Reset.",580,60,paint);

            //Validaciones de Menus
            paint.setColor(Color.WHITE);
            canvas.drawText("m0: "+cont+" m1: "+contd,30,100,paint);

            //si presiona boton "Rotacion".
            if(cont==1){
                herd.limpiaBotones(back);
                paint.setTextSize(10);
                paint.setColor(Color.WHITE);
                canvas.drawText("Rotacion",10,10,paint);
            }
            //si presiona boton "Traslacion".
            if(cont==2){
                herd.limpiaBotones(back);
                paint.setTextSize(10);
                paint.setColor(Color.WHITE);
                canvas.drawText("Traslacion",60,10,paint);
            }

        }

        public boolean onTouchEvent(MotionEvent evento){
            float getx = evento.getX();
            float gety = evento.getY();
            int accion = evento.getAction();
            //detecta si Mantiene el dedo sobre la pantalla
            if(accion == MotionEvent.ACTION_MOVE){
                //Confirma que si esta en el menu principal.
                if(contd==0){
                    //Detecta si toca el BOTON Rotacion
                    if(getx > 10 && getx < 180 && gety > 20 && gety < 70){
                        cont = 1;
                        invalidate();//Repinta el canvas con los nuevos valores
                    }

                    //Detecta si toca el BOTON Traslacion
                    if(getx > 220 && getx < 420 && gety > 20 && gety < 70){
                        cont = 2;
                        invalidate();//Repinta el canvas con los nuevos valores
                    }

                }

                //*********Al presionar el boton "ROTACION" entra en su sub-menu.*******//

                //1.1 Boton De GIRO ANTI-HORARIO.
                if((getx > 10 && getx < 180 && gety > 20 && gety < 70)&&(cont==1)){
                    contd = 1;
                    angle--;
                    invalidate();//Repinta el canvas con los nuevos valores
                }
                //1.2 Boton De GIRO HORARIO.
                if((getx > 220 && getx < 420 && gety > 20 && gety < 70)&&(cont==1)){
                    contd = 2;
                    angle++;
                    invalidate();//Repinta el canvas con los nuevos valores
                }

                //*********DENTRO DE LOS SUB-MENUS.*************//

                //cuando el Usuario toque la flecha, regresa al Menu Inicial
                //donde se observan los botones "ROTACION" y "TRASLACION".
                if((getx > 460 && getx < 520 && gety > 20 && gety < 70)&&((cont==1)||(cont==2))){
                    contd=0;
                    cont=0;
                    invalidate();//Repinta el canvas con los nuevos valores
                }
                //Restaura el canvas, Cuando el usuario toque el Boton "Reset".
                if((getx > 560 && getx < 700 && gety > 20 && gety < 70)&&((cont==1)||(cont==2))){
                    yT=0;
                    angle=0;
                    cont=0;
                    contd=0;
                    invalidate();//Repinta el canvas con los nuevos valores
                }

                //*********Al presionar el boton "TRASLACION" entra en su sub-menu.***************//

                //2.1 Boton de Traslacion con movimiento hacia Arriba.
                if((getx > 10 && getx < 180 && gety > 20 && gety < 70)&&(cont==2)){
                    contd = 1;
                    yT--;
                    invalidate();//Repinta el canvas con los nuevos valores
                }
                //2.2 Boton de Traslacion con movimiento hacia Abajo.
                if((getx > 220 && getx < 420 && gety > 20 && gety < 70)&&(cont==2)){
                    contd = 2;
                    yT++;
                    invalidate();//Repinta el canvas con los nuevos valores

                }

            }
        return true;
        }

    }
}
