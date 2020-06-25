package com.example.myhello;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Common {
    myAdap m_adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_adap = new myAdap();

        m_adap.add("It’s a long story.", "말하자면 길어요");
        m_adap.add("How did it go?", "어떻게 됐어요?");
        m_adap.add("If everything goes according to plan…", "모든 일이 다 계획대로 된다면…");
        m_adap.add("How’s your food?", "음식이 어때요?");
        m_adap.add("What did you get?", "뭐 주문했어요? / 뭐 샀어요?");
        m_adap.add("The service here is terrible!", "여기 서비스가 진짜 최악이네요");
        m_adap.add("His Korean really sucks.", "그는 한국어 진짜 못해요.");
        m_adap.add("That movie really sucked.", "그 영화 진짜 별로였어요");
        m_adap.add("I come from a large family.", "우리는 대가족이에요");
        m_adap.add("That’s my dream job!", "내 꿈의 직업이에요 / 내가 생각하는 이상적인 직업이에요");
        m_adap.add("He’s got a chip on his shoulder.", "그는 억울해서 한을 품고 있어요");
        m_adap.add("Let’s all chip in and buy him a nice present.", "우리 돈을 모아서 그 사람한테 좋은 선물 사주자.");
        m_adap.add("I don’t hold a grudge.", "나 뒤끝 없는 사람이에요");
        m_adap.add("Get over it!", "잊어버리고 넘어가");
        m_adap.add("It’s time for you to move on.", "잊을 때가 됐어요");
        m_adap.add("You really hurt my feelings.", "나한테 상처 줬어요");
        m_adap.add("I’ll never forget what you said.", "나한테 한 말을 절대 잊지 않을 거예요");
        m_adap.add("That was a close call!", "큰일날 뻔했어요");
        m_adap.add("We just missed it.", "아깝게 놓쳤어요");
        m_adap.add("Why aren’t you taking my calls?", "왜 내 전화 안 받아요?");
        m_adap.add("She stood me up.", "그 여자한테 바람 맞았어요");
        m_adap.add("Don’t keep me waiting.", "기다리게 하지 말아요");
        m_adap.add("Don’t string him along like that.", "희망 고문하지 말아요");
        m_adap.add("What do you usually do in your free time?", "한가할 때 보통 뭐 해요?");
        m_adap.add("I didn’t have the heart to tell him.", "상처받을까 봐 차마 말을 못했어요");
        m_adap.add("How did he take the news?", "그 소식 듣고 반응이 어땠어요?");
        m_adap.add("He took it well.", "잘 받아들였어요");
        m_adap.add("He didn’t bat an eye.", "눈 하나 깜짝 안 했어요");
        m_adap.add("Just my luck!", "역시 난 운이 없어요");
        m_adap.add("Serves you right!", "자업자득이에요");
        m_adap.add("That’s what you get!", "자업자득이에요");
        m_adap.add("It’s your fault.", "당신 탓이에요");
        m_adap.add("I told you so!", "내가 말했잖아요 / 그럴 줄 알았어요");
        m_adap.add("I really blew it this time.", "이번에 제대로 망쳤네요");
        m_adap.add("Epic fail!", "폭망");
        m_adap.add("I heard you were quitting.", "그만둔다면서요");
        m_adap.add("It looks like you’re finally getting the hang of it.", "이제 손에 익었나 봐요");
        m_adap.add("Everyone’s jumping on the bandwagon.", "모두 다 그 유행을 타네요");
        m_adap.add("Looks like you’re off the wagon, huh?", "작심삼일이었나 봐요, 그죠?");
        m_adap.add("I quit cold turkey.", "단번에 끊었어요");
        m_adap.add("I’m turning over a new leaf.", "새로운 장을 열었어요/ 새롭게 시작하고 있어요");
        m_adap.add("That’s what I’m saying.", "내 말이…");
        m_adap.add("That’s what I’m talking about.", "내 말이…");
        m_adap.add("You took the words right out of my mouth!", "그 말 하려던 참이었어요!");
        m_adap.add("It’s a fresh start.", "새로운 시작이에요");
        m_adap.add("That’s not a good sign.", "좋은 징조는 아니에요");
        m_adap.add("Third time’s the charm!", "삼세번에 이루다");
        m_adap.add("I’m all ears!", "경청하겠습니다!");
        m_adap.add("Don’t interrupt!", "끼어들지 말아요!");
        m_adap.add("The thing is…", "내 요점은 / 문제는");
        m_adap.add("You’re not very understanding.", "남을 이해하려고 하지도 않아요");
        m_adap.add("Thanks for your understanding.", "이해/양해해 주셔서 감사합니다");
        m_adap.add("The damage is done!", "이미 난 손해/상처를 돌이킬 수 없다");
        m_adap.add("That ship has sailed.", "버스는 떠났어요");
        m_adap.add("She’s very high maintenance.", "손 많이 가는 스타일이에요");
        m_adap.add("They worked it out.", "그들은 잘 화해했어요 / 문제 잘 풀었어요");
        m_adap.add("Stay focused!", "집중해요!");
        m_adap.add("I’ve been trying to stay out of trouble.", "바르게 살려고 노력하고 있어요");
        m_adap.add("Stay out of my business.", "너나 잘해! / 내 일에 참견하지 마");
        m_adap.add("Mind your own business.", "간섭하지 말아요");
        m_adap.add("Do you mind if I use this?", "이거 써도 돼요?");
        m_adap.add("No wonder you speak English so well.", "그래서 영어 그렇게 잘하는 거였군요");
        m_adap.add("I’ve got a lot on my mind right now.", "요새 골칫거리 많아요");
        m_adap.add("I’ve got a lot on my plate.", "요새 하는 일이 많아요");
        m_adap.add("That’s what I love about Korea.", "한국을 사랑하는 이유가 바로 그거예요");
        m_adap.add("That’s what I hate about working here.", "여기서 일하는 게 싫은 이유가 바로 그거예요!");
        m_adap.add("That’s just the way it is.", "원래 그래요 / 사는 게 원래 그래요");
        m_adap.add("Don’t badmouth me!", "내 욕 하지 마");
        m_adap.add("I heard you were talking trash.", "내 뒷담화했다면서");
        m_adap.add("Don’t talk about me behind my back!", "나 없을 때 내 이야기 하지 말아요!");
        m_adap.add("Don’t spread rumors!", "소문 퍼뜨리지 말아요!");
        m_adap.add("The copier is acting up again.", "복사기가 또 말썽을 부리네요");
        m_adap.add("Stop fiddling with it.", "만지작거리지 말아요");
        m_adap.add("I’m bad with electronics.", "나 기계치예요");
        m_adap.add("He’s bad with directions.", "그는 방향 감각이 없어요");
        m_adap.add("Sorry. I’m not very good with children.", "미안한테 아이를 잘 돌볼 줄 몰라요");
        m_adap.add("I’m not really a people person.", "나는 사교적인 사람이 아니에요");
        m_adap.add("I’m more of a dog person.", "나 개과예요");
        m_adap.add("I think we got off on the wrong foot.", "처음부터 우리 서로한테 열 받게 한 것 같아요");
        m_adap.add("We really hit it off.", "우리는 금방 친해졌어요");
        m_adap.add("What was your impression of him?", "그 사람 인상이 어땠어요?");
        m_adap.add("You never get a second chance to make a first impression.", "첫 인상에 두 번째 기회는 없어요");
        m_adap.add("I’m looking forward to it.", "기대하고 있어요");
        m_adap.add("I can’t sit still.", "안전부절 못해요");
        m_adap.add("Don’t blame me!", "화살 나한테 돌리려고 하지 말아요 / 내 탓하지 말아요");
        m_adap.add("It’s your responsibility. ", "당신 책임이잖아요");
        m_adap.add("I’m running late!", "늦어요 / 늦을 것 같아요");
        m_adap.add("Things have been hectic at work.", "요새 회사에서 정신이 하나도 없어요");
        m_adap.add("Work has been crazy lately.", "요새 회사에서 정신이 하나도 없어요");
        m_adap.add("What’s with the getup?", "옷차림이 왜 그래요?");
        m_adap.add("What’s up with him?", "그 사람은 왜 그래요?");
        m_adap.add("How are you holding up?", "어떻게 견디고 있어요?");
        m_adap.add("What’s your take on it?", "당신은 어떻게 생각해요?");
        m_adap.add("What’s your best guess?", "대략적으로 짐작해보세요");
        m_adap.add("Give me a ballpark figure.", "대략적인 숫자 알려주세요");
        m_adap.add("I need to cool my head.", "머리 좀 식혀야 되겠어요");
        m_adap.add("You’re tuning me out again, aren’t you?", "또 나를 무시하는 거죠?");
        m_adap.add("Get with the program.", "정신 차려요!");
        m_adap.add("Speak of the devil.", "호랑이도 제 말하면 온다");
        m_adap.add("Not the sharpest tool in the shed, are we?", "우리 형광등 같네요/둔하네요");

        ListView list = findViewById(R.id.list);
        list.setAdapter(m_adap);


        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
                        Intent it = new Intent(MainActivity.this, Dialog.class);
                        it.putExtra("eng", m_adap.getc(pos,0));
                        it.putExtra("kor", m_adap.getc(pos,1));
                        startActivity(it);

                        //Intent intent = new Intent(MainActivity.this, Dialog.class);
                        //startActivity(intent);

                    }
                }
        );


        /*list().setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                        m_pos = position;

                        new AlertDialog.Builder(Book.this)
                                .setTitle(m_adap.getc(m_pos,1))
                                .setIcon(R.drawable.book)
                                .setItems(new String[] {s(R.string.edit), s(R.string.del), / *"파일저장(구버젼)",* / s(R.string.exports)},
                                        new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog, int which){
                                                if(which==0){			//수정
                                                    dlg_txt(BOOK_EDIT, m_adap.getc(m_pos,1), R.string.msg_edit_name, R.drawable.book);
                                                }
                                                else if(which==1){		//삭제
                                                    book_del();
                                                }
                                                else if(which==2){		//파일저장
                                                    dlgFileType();
                                                }
                                            }
                                        }
                                )
                                .show();

                        return true;
                    }
                }
        );*/



        /*view.setOnTouchListener(new View.OnTouchListener() {
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
                                });*/

    }
//https://m.dic.daum.net/search.do?q=red




    protected void onclickup(myView v, MotionEvent e){
        String url ="https://translate.google.co.kr/?hl=ko#view=home&op=translate&sl=en&tl=ko&text=";
        //i%20love%20you
        if(v.sel==0)
            for(int i=0;i<v.words.size();i++) url += (i==0?"":"%20") + v.words.get(i).word;
        else
            url ="https://m.dic.daum.net/search.do?q=" + v.words.get(v.sel).word;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    protected void onclickss(myView v, MotionEvent e){
        int i;
        for(i=0;i<v.words.size();i++){
            if(v.words.get(i).x >= e.getX() || e.getX() >= v.words.get(i).x + v.words.get(i).width) continue;
            if(v.words.get(i).y - 100 >= e.getY() || e.getY() >= v.words.get(i).y) continue;
            v.sel = i;
            v.invalidate();
            break;
        }
    }

    protected class myView extends View{
        ArrayList<word> words;
        int sel = 3;

        public myView(Context context){
            super(context);
            words = new ArrayList<word>();
            drawText("  I have a view, I'm drawing with the Canvas object in the onDraw(Canvas canvas) method. My code is  ");
        }

        public void onDraw(Canvas canvas){
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

            Paint pnt = new Paint();
            pnt.setAntiAlias(true);
            pnt.setTextSize(100);

            int i;
            for(i=0;i<words.size();i++) {
                pnt.setColor(i==sel ? Color.RED : Color.BLACK);
                //Log.d("KGM", "word="+words.get(i).word+" x="+words.get(i).x+" y="+words.get(i).y);
                canvas.drawText(words.get(i).word, words.get(i).x, words.get(i).y, pnt);
            }
        }

        class word{
            String word;
            int x;
            int y;
            float width;
        }

        public void drawText(String str){
            int th = 100;

            int i, j;
            //ArrayList<word> words = new ArrayList<word>();
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

            Log.d("KGM", "lens=" + words.size());

            //그리기
            Paint pnt = new Paint();
            pnt.setAntiAlias(true);
            pnt.setColor(Color.BLACK);
            pnt.setTextSize(100);

            for(i=0;i<words.size();i++)
                words.get(i).width = pnt.measureText(words.get(i).word,0, words.get(i).word.length());

            //화면크기
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            float spc = pnt.measureText(" ",0, 1);
            s=0;
            int n=0;
            int y=100;
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
                y+=100;
            }

            x = (width-n)/2;
            for(j=s;j<i;j++){
                words.get(j).x = x;
                words.get(j).y = y;
                x += words.get(j).width + spc;
            }
        }
    }
}


class myAdap extends Adap{
    public myAdap(){
        super();
    }

    //page, time, io, custom, cost, memo, amount, name
    public View getView(int pos, View v, ViewGroup parent){
        if(v==null)
            v = inflate(R.layout.row_txt3, parent);

        set_txt(v, R.id.txt1, getc(pos,0));
        set_txt(v, R.id.txt2, getc(pos,1));
        return v;
    }
}
