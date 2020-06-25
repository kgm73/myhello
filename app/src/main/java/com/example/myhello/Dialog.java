package com.example.myhello;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Dialog extends Common {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView view = new myView(this,
                getIntent().getStringExtra("eng"),
                getIntent().getStringExtra("kor"));
        setContentView(view);
    }

    protected class myView extends View{
        ArrayList<word> m_eng, m_kor;
        int sel = 3;

        public myView(Context context, String eng, String kor){
            super(context);
            m_eng = new ArrayList<word>();
            m_kor = new ArrayList<word>();

            int y;
            y = mkWord(eng, m_eng, 100, 0);
            Log.d("KGM", "y="+y);
            mkWord(kor, m_kor, 60, y+30);

            setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN :
                            onclickss((myView)v, event);
                            //Log.d("KGM", "ACTION_DOWN x="+event.getX()+" y="+event.getY());
                        case MotionEvent.ACTION_MOVE :
                        case MotionEvent.ACTION_UP   :
                            onclickup((myView)v, event);
                            // 이미지 뷰의 위치를 옮기기
                            //iv.setX(event.getX());
                            //iv.setY(event.getY());
                    }
                    return true;
                }
            });
        }

        //https://m.dic.daum.net/search.do?q=red

        protected void onclickup(myView v, MotionEvent e){
            String url ="https://translate.google.co.kr/?hl=ko#view=home&op=translate&sl=en&tl=ko&text=";
            //i%20love%20you
            if(v.sel==0)
                for(int i=0;i<v.m_eng.size();i++) url += (i==0?"":"%20") + v.m_eng.get(i).word;
            else
                url ="https://m.dic.daum.net/search.do?q=" + v.m_eng.get(v.sel).word;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            //startActivity(intent);
        }

        protected void onclickss(myView v, MotionEvent e){
            int i;
            for(i=0;i<v.m_eng.size();i++){
                if(v.m_eng.get(i).x >= e.getX() || e.getX() >= v.m_eng.get(i).x + v.m_eng.get(i).width) continue;
                if(v.m_eng.get(i).y - 100 >= e.getY() || e.getY() >= v.m_eng.get(i).y) continue;
                v.sel = i;
                v.invalidate();
                break;
            }
        }

        public void onDraw(Canvas canvas){
            Paint pnt = new Paint();
            pnt.setAntiAlias(true);
            pnt.setTextSize(100);

            int i;
            for(i=0;i<m_eng.size();i++) {
                pnt.setColor(i==sel ? Color.RED : Color.BLACK);
                //Log.d("KGM", "word="+words.get(i).word+" x="+words.get(i).x+" y="+words.get(i).y);
                canvas.drawText(m_eng.get(i).word, m_eng.get(i).x, m_eng.get(i).y, pnt);
            }

            pnt.setTextSize(60);
            pnt.setColor(Color.BLACK);
            for(i=0;i<m_kor.size();i++)
                canvas.drawText(m_kor.get(i).word, m_kor.get(i).x, m_kor.get(i).y, pnt);
        }

        class word{
            String word;
            int x;
            int y;
            float width;
        }

        public int mkWord(String str, ArrayList<word> words, int wHeight, int top){
            int i, j;
            int s = -1;
            for(i=0;i<str.length();i++){
                if(str.charAt(i) == ' '){
                    if(s != -1){
                        word w = new word();
                        w.word = str.substring(s,i);
                        words.add(w);
                        s = -1;
                    }
                }
                else{
                    if(s == -1) s = i;
                }
            }

            if(s != -1){
                word w = new word();
                w.word = str.substring(s,i);
                words.add(w);
            }

            //Log.d("KGM", "lens=" + words.size());

            //그리기
            Paint pnt = new Paint();
            pnt.setAntiAlias(true);
            pnt.setColor(Color.BLACK);
            pnt.setTextSize(wHeight);

            for(i=0;i<words.size();i++)
                words.get(i).width = pnt.measureText(words.get(i).word,0, words.get(i).word.length());

            //화면크기
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            //int height = size.y;

            float spc = pnt.measureText(" ",0, 1);
            s = 0;
            int n = 0;
            int y = top + wHeight;
            int x;
            for(i=0;i<words.size();i++){
                if(n==0){
                    n += words.get(i).width;
                    continue;
                }
                if(n + words.get(i).width + spc < width){
                    n += words.get(i).width + spc;
                    continue;
                }

                x = (width-n)/2;
                for(j=s;j<i;j++){
                    words.get(j).x = x;
                    words.get(j).y = y;
                    x += words.get(j).width + spc;
                }

                s=i;
                n = 0;
                n += words.get(i).width;
                y += wHeight;
            }

            x = (width-n)/2;
            for(j=s;j<i;j++){
                words.get(j).x = x;
                words.get(j).y = y;
                x += words.get(j).width + spc;
            }

            return y;
        }
    }
}



            /*Paint pnt = new Paint();
            pnt.setColor(Color.BLUE);
            canvas.drawColor(Color.GRAY);

            //안티 알리아스 적용
            pnt.setAntiAlias(true);
            pnt.setTextSize(100);
            //canvas.drawText("hello world! kgm pqxX", 3, 10, 200, 200, pnt);
            canvas.drawText("Hello World! Kgm pQxX", 200, 200, pnt);
            canvas.drawCircle(100, 100, 80, pnt);*/


//String text =  "Create-a-new-empty-Rect"; // "안녕하세요 hellow";


            /*String text =  "안녕하세요 Create a New"; //"Create a new empty Rect"; //

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setTextSize(100);
            //KGM: H=91 W=1070

            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);

            int textWidth = rect.width();
            int textHeight = rect.height();
            Log.d("KGM", "H="+textHeight+" W="+textWidth);

            textHeight = 100;
            int y = textHeight;
            canvas.drawText(text, 0, y, paint);

            y += textHeight;
            for(int i=0; i<text.length(); i++){
                //y += textHeight;
                //paint.getTextBounds(text, 0, i, rect);
                //canvas.drawText(text, i, i+1, 50 + rect.width() + 6, y, paint);
                //canvas.drawText(text, 0, i+1, 50, y, paint);

                canvas.drawText(text, i, i+1, paint.measureText(text,0, i), y, paint);
            }*/

//drawText(canvas, " [KB국민카드 1% 청구할인] [KB국민/신한/현대 100만원 이상 SK pay 결제 시 최대 22개월 무이자][아웃도어스/기타캠핑]코지쉘 차박텐트 SUV 차량용 텐트/캠핑/모기장텐트 ");
