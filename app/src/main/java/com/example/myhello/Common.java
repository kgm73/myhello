package com.example.myhello;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.*;
import java.text.*;

import android.app.*;
import android.content.*;
import android.database.Cursor;
import android.net.*;
import android.os.*;
import android.provider.OpenableColumns;
import android.util.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;


public class Common extends AppCompatActivity {			//Activity
    final static String KO = "ko";

    final static int
            SEND_FILE = 100;

    static int s_dpi = 160;
    int m_book;
    String m_booknm;

    String send_txt = null;    //send_text() 파일저장용 임시 보관

    public Common(){}

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //m_book   = getIntent().getIntExtra("book",-1);
        //m_booknm = getIntent().getStringExtra("booknm");

        //s_dpi = get_dpi();
        //ViewGraph.dpi = get_dpi();
    }

    public static Intent put_extra_book(Intent intent, int book, String booknm){
        intent.putExtra("book", book);
        intent.putExtra("booknm", booknm);
        return intent;
    }

    public Intent get_book_intent(Class<?>cls){
        return put_extra_book(new Intent(Common.this, cls), m_book, m_booknm);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //공유
        if(requestCode == SEND_FILE){
            if(resultCode == RESULT_OK)
                saveTxtFile(data.getData(), send_txt);
            send_txt = null;
            return;
        }

        if(resultCode != RESULT_OK) return;
    }


    protected void msgbox(String msg){
        new AlertDialog.Builder(Common.this)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    protected void msgbox(String title, String msg){
        new AlertDialog.Builder(Common.this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    protected void alert(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void alerts(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void alert(int reId){ alert(s(reId)); }

    protected void alerts(int reId){ alerts(s(reId)); }




    /*protected void send_sms(String phone[], String msg){
        if(phone.length > 20){
            Intent intent = new Intent(this, SendSMS.class);
            intent.putExtra("phone", phone);
            intent.putExtra("msg", msg);
            startActivity(intent);
            return;
        }


        StringBuffer sb = new StringBuffer();
        for(int i=0;i<phone.length;i++) sb.append((i==0?"":";") + phone[i]);

        //SMS발송
        Uri uri = Uri.parse("smsto:" + sb.toString());
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        if(!msg.equals("")) it.putExtra("sms_body", msg);
        startActivity(it);
    }*/




    public static String get_today(){
        Calendar cal = new GregorianCalendar();
        return zntos(cal.get(Calendar.YEAR),4) + zntos(cal.get(Calendar.MONTH)+1,2) + zntos(cal.get(Calendar.DAY_OF_MONTH),2);
    }




    protected void call_phone(String phone){
        Uri uri = Uri.parse("tel:" + phone);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);	/*Intent.ACTION_CALL*/
        startActivity(it);
    }


    ///// 일반함수 /////////////////////////////////////////////////////////////////////////////////
    public static String[] splits(String str, char c){
        int i, s, j, len;

        j = 1;
        len = str.length();
        for(i=0;i<len;i++) if(str.charAt(i)==c) j++;
        String[] list = new String[j];

        s=j=0;
        for(i=0;i<len;i++)
            if(str.charAt(i)==c){
                list[j++] = str.substring(s, i);
                s = i+1;
            }

        list[j++] = str.substring(s, i);
        return list;
    }


    public static String zntos(int n, int len){
        String s = String.valueOf(n);
        while(s.length()<len) s="0"+s;
        return s;
    }


    public static boolean is_number(String s){
        int n = s.length();
        if(n==0) return false;	//공란
        for(int i=0;i<n;i++) if('0' > s.charAt(i) || s.charAt(i) > '9') return false;
        return true;
    }


    public static boolean is_null(String s){
        return s==null || s.equals("");
    }


    //public static String nvl(String s){
    //	return s==null ? "" : s;
    //}


    public static String get_name(String s){	//이름추출  가져오기(주소록) 에서만 쓰임...
        int n = s.length();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<n;i++)
            if(s.charAt(i)!=',' && s.charAt(i)!='/' && s.charAt(i)!='\n' && s.charAt(i)!='\r' && s.charAt(i)!='\t')
                sb.append(s.charAt(i));
        return sb.toString();
    }



    /*
    public static boolean chk_name(String s){	//이름check
		int n = s.length();
		for(int i=0;i<n;i++)
			if(s.charAt(i)==',' || s.charAt(i)=='/' || s.charAt(i)=='\n' || s.charAt(i)=='\r' || s.charAt(i)=='\t') return false;
		return true;
    }*/


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static boolean is_txt(String s, String c){
        int i, j, sn, cn;
        sn = s.length();
        cn = c.length();
        for(i=0;i<sn;i++)
            for(j=0;j<cn;j++)
                if(s.charAt(i)==c.charAt(j)) return true;
        return false;
    }


    public static String c(String s){
        return "'" + s.replace("'", "''") + "'";
    }



    public static void list_select(ListView list, int row){
        if(row<0 || row>=list.getCount()) return;
        list.setSelectionFromTop(row, 0);
    }

    protected void list_select(int id, int row){
        list_select((ListView)findViewById(id), row);
    }




    public static String get_number(String s){	//숫자만 추출  s=""값처리함 ""리턴
        int n = s.length();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<n;i++) if('0' <= s.charAt(i) && s.charAt(i) <= '9') sb.append(s.charAt(i));
        return sb.toString();
    }


    public static int div(int a, int b){
        return b==0 ? 0 : a/b;
    }


    public static String percent(int a, int b){
        return percent(b==0 ? 0f : (float)a/(float)b);
    }


    public static String percent(float f){
        if(f<=0f) return "0%";

        int i = (int)(f*1000f);
        if(i==0) i=1;

        DecimalFormat df = new DecimalFormat("0.#");
        return df.format((float)i/10f) + "%";
    }


    //https://developer.android.com/reference/java/text/SimpleDateFormat
	/*public static String DateFormat(Date d, String format){
    	if(d==null || format==null) return "";
    	SimpleDateFormat df = new SimpleDateFormat(format);
    	return df.format(d);
	}

	public static String DateFormat(String s, String format){
    	return DateFormat(toDate(s),format);
	}*/

    public String sMonth(String s){
        if(s==null || s.length() < 6) return "";
        //if(getLanguage().equals(KO))
        return s.substring(0,4)+"."+s.substring(4,6);
        //return DateFormat(s.substring(0,6)+"01", "MMM ''yy");
    }

    public String sDay(String d){		//YYYY.MM.DD  f_day
        if(d==null || d.length() < 8) return "";
        //if(getLanguage().equals(KO))
        return d.substring(0,4)+"."+d.substring(4,6)+"."+d.substring(6,8);
        //return DateFormat(d,"MMM d, ''yy");
    }


    public String sWeek(String d){   //get_week
        //String w[] = {"일","월","화","수","목","금","토"};
        /*String w[] = getResources().getStringArray(R.array.week);
        Calendar cc = Calendar.getInstance();
        cc.set(	Integer.parseInt(d.substring(0,4)),
                Integer.parseInt(d.substring(4,6))-1,
                Integer.parseInt(d.substring(6,8)));
        return w[cc.get(Calendar.DAY_OF_WEEK)-1];*/
        return "";
    }


    public String sDayWeek(String d){	//YYYY.MM.DD W  f_dayweek
        //if(getLanguage().equals(KO))
        return sDay(d) + " " + sWeek(d);
        //return DateFormat(d,"EEE, MMM d, ''yy");
    }


    public String sDayWeekGb(String d){	//YYYY.MM.DD W HH  f_page_dt
        return (sDayWeek(d.substring(0,8))+" "+d.substring(8)).trim();
    }


    public static Date toDate(String s){
        //https://code.i-harness.com/ko/q/3765e
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);
        try{
            return df.parse(s.substring(0,8));
        }
        catch(Exception e){
            return null;
        }
    }

    /*///////////////////////////////////////////
    protected void set_title(String s){
    	set_txt(R.id.title, s);
    }

    protected void set_btn_menu(){
		btn_click(R.id.menu, new Button.OnClickListener(){
			public void onClick(View v){
				openOptionsMenu();
			}
		});
    }*/


    /////////////////////////////////////////////////
    protected void btn_click(int id, Button.OnClickListener e){
        ((Button)findViewById(id)).setOnClickListener(e);
    }


    //View -> TextView -> Button, EditText 상속구조
    //TextView는 위젯중 가장 기본클레스
    protected String get_txt(int id){
        return ((TextView)findViewById(id)).getText().toString();
    }

    protected void set_txt(int id, String s){
        ((TextView)findViewById(id)).setText(s);
    }

    //View -> TextView -> Button, EditText 상속구조
    //TextView는 위젯중 가장 기본클레스
    protected String getTxt(int id) {
        return nvl(((TextView)findViewById(id)).getText().toString());
    }

    protected void setTxt(int id, int reId) {
        setTxt(id, s(reId));
    }

    protected void setTxt(int id, String s) {
        ((TextView)findViewById(id)).setText(nvl(s));
    }

    public static String getTxt(View v, int id) {
        return nvl(((TextView)v.findViewById(id)).getText().toString());
    }

    public static void setTxt(View v, int id, String s) {
        ((TextView)v.findViewById(id)).setText(nvl(s));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////



    protected LayoutInflater get_Inflater(){
        return (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    public static void log(String s){
        Log.d(s, "KGM_LOG");
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //https://developer.android.com/guide/topics/providers/document-provider?hl=ko
    protected void SaveFile(int flag, String fileName){
        Intent it = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        // Filter to only show results that can be "opened", such as
        // a file (as opposed to a list of contacts or timezones).
        it.addCategory(Intent.CATEGORY_OPENABLE);

        // Create a file with the requested MIME type.
        //it.setType("text/plain"); //바이너리: application/octet-stream  text: text/plain
        //it.setType("application/octet-stream");
        it.setType("*/*");
        it.putExtra(Intent.EXTRA_TITLE, fileName);		//m_adap.getc(m_pos,1) + ".dat"
        startActivityForResult(it, flag);		//STORE_FILE
    }

    //http://lectroid.tistory.com/8 ★ 읽어봐!!!
    protected void OpenFile(int flag){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_OPEN_DOCUMENT
        it.addCategory(Intent.CATEGORY_OPENABLE);
        it.setType("*/*");
        startActivityForResult(it, flag);	//STORE_READ
    }

    // 파일명 찾기
    protected String getName(Uri uri){
		/*
		String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
		cursor.moveToFirst();
		return cursor.getString(column_index);
		*/
        //return RealPathUtil.getRealPath(this, uri);

		/*Cursor c = getContentResolver().query(uri, null, null, null, null );
		c.moveToNext();
		String path =  c.getString( c.getColumnIndex( "_data" ) );
		c.close();
		return path;*/

        //출처: https://m.blog.naver.com/PostView.nhn?blogId=lys1900&logNo=220889642899&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F
        String result = null;
        if(uri.getScheme().equals("content")){
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try{
                if(cursor!=null && cursor.moveToFirst())
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            }
            finally{
                if(cursor!=null) cursor.close();
            }
        }

        if(result==null)
            result = uri.getLastPathSegment();

        return result;
    }



    protected void msgbox(int resId){
        msgbox((String) getText(resId));
    }

    protected void list_select(int row) {
        list_select(list(), row);
    }

    protected ListView list(){
        return (ListView)findViewById(R.id.list);
    }

    public static String nvl(String s, String v) {
        return s == null ? v : s;
    }

    public static String nvl(String s) {
        return nvl(s, "");
    }

    public static String evl(String s, String v) {
        return empty(s) ? v : s;
    }

    public static long toLong(String s) {
        return Long.parseLong(evl(s,"0").replaceAll(",",""));
    }

    public static int toInt(String s) {
        return Integer.parseInt(evl(s,"0").replaceAll(",",""));
    }

    public static float toFloat(String s) {
        return Float.parseFloat(evl(s,"0").replaceAll(",",""));
    }

    public static boolean empty(String s) {
        return nvl(s).equals("");
    }

    public static boolean equal(String a, String b){
        return nvl(a).equals(nvl(b));
    }

    //public static BufferedWriter
    //BufferedWriter f = new BufferedWriter(new OutputStreamWriter(openFileOutput("file:///sdcard/report.htm", Context.MODE_WORLD_READABLE),"MS949"));
	/*
	public static String getTempDir(){
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) return null;
		String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp";
		File dir = new File(path);
		if(!dir.exists() && !dir.mkdirs()) return null;	//상위 폴더가 없으면 상위 폴더까지 생성
		return path;
	}
	*/

    protected void setShow(int id, boolean show){
        findViewById(id).setVisibility(show ? View.VISIBLE : View.GONE);
    }

    protected boolean getShow(int id){
        return findViewById(id).getVisibility()==View.VISIBLE;
    }

/*
	//광고
	public static void setAdView(AdView adView){
    	final AdView f_adView = adView;
		//AdView adView = v.findViewById(resId);

		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("B25B5AC37D7108F7788A718E4341CD59")		//LG G2
				.addTestDevice("5FF9091396EEA5522DF317EC710608E8")		//Lenove Tab2A7-20F
				.build();

		adView.loadAd(adRequest);

		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// Code to be executed when an ad finishes loading.
				f_adView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				// Code to be executed when an ad request fails.
			}

			@Override
			public void onAdOpened() {
				// Code to be executed when an ad opens an overlay that
				// covers the screen.
			}

			@Override
			public void onAdLeftApplication() {
				// Code to be executed when the user has left the app.
			}

			@Override
			public void onAdClosed() {
				// Code to be executed when when the user is about to return
				// to the app after tapping on an ad.
			}
		});
	}

	protected void setAdView(int resId){
		setAdView((AdView)findViewById(resId));
	}
*/

    public static void trim(String a[]) {
        for(int i=0;i<a.length;i++) a[i]=a[i].trim();
    }

    public static void append(StringBuffer sb, String s){
        sb.append(s);
        sb.append('\n');
    }

    public String getLanguage(){
        return getApplicationContext().getResources().getConfiguration().locale.getLanguage();

    	/*Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
		String strDisplayCountry = systemLocale.getDisplayCountry(); // 대한민국
		String strCountry = systemLocale.getCountry(); // KR
		String strLanguage = systemLocale.getLanguage(); // ko*/
    }

    public String s(int reId){
        return (String)getText(reId);
    }

    public static String chars(char c, int n){
        StringBuffer sb = new StringBuffer();
        while(n-->0) sb.append(c);
        return sb.toString();
    }

    public static String strim(String s){
        return nvl(s).trim()
                .replace("\n", "")
                .replace("\r", "")	//윈도우 개행 \r\n
                .replace("\t", "");
    }

    public void send_text(String s){
        //send_txt = s;   //임시보관
        //startTxtFile(SEND_FILE, s(R.string.share) + "_" + get_time());

        try{
            Intent it = new Intent();
            it.setAction(Intent.ACTION_SEND);
            it.putExtra(Intent.EXTRA_TEXT, s);
            it.setType("text/plain");
            startActivity(it);
        }
        catch (Exception e){
            send_txt = s;   //임시보관
            startTxtFile(SEND_FILE, s(R.string.share) + "_" + get_time());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    protected int get_dpi(){
        DisplayMetrics dpm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dpm);
        return dpm.densityDpi;
    }

    public static int get_pixel(int i){
        return i*s_dpi/160;
    }


    public static String mkstr(String[] o){	//test ok
        int c = o.length;
        while(c>0 && empty(o[c-1].replace("/", ""))) c--;
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<c;i++){
            if(i!=0) sb.append('/');
            sb.append(o[i].replace("/", ""));
        }
        return sb.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    protected void ok_txt(int flag, String s){}

    protected void dlg_txt(int flag, String s, String title){
        dlg_txt(flag, s, title, -1);
    }

    protected void dlg_txt(int flag, String s, int title, int icon){
        dlg_txt(flag, s, s(title), icon);
    }

    protected void dlg_txt(int flag, String s, String title, int icon){
        final int fFlag = flag;
        final EditText eTxt = new EditText(this);
        eTxt.setText(s);

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        if(icon>0) dlg.setIcon(icon);
        if(!empty(title)) dlg.setTitle(title);

        dlg.setView(eTxt)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){  //확인
                    public void onClick(DialogInterface dialog, int arg1){
                        ok_txt(fFlag, eTxt.getText().toString());
                        hideKeyboard();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){  //취소
                    public void onClick(DialogInterface dialog, int arg1){
                        hideKeyboard();
                    }
                })
                .show();

        eTxt.setSelection(s.length());      // 커서를 제일 끝으로 보냄
        //ctxt.requestFocus();
        showKeyboard();                     //키보드 자동 보이기
    }

    public void showKeyboard(){
        //출처: http://kanais2.tistory.com/205 [飛上]
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.showSoftInput(ctxt, InputMethodManager.SHOW_FORCED);
    }

    public void hideKeyboard(){ //EditText ctxt
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);

        //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(ctxt.getWindowToken(), 0);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int compareTo(String a, String b){  //비교
        return new BigDecimal(evl(a,"0")).compareTo(new BigDecimal(evl(b,"0"))); //-1=작다, 0=같다, 1=크다
    }

    public static String get_time() {
        Calendar cal = new GregorianCalendar();
        return zntos(cal.get(Calendar.YEAR), 4) + zntos(cal.get(Calendar.MONTH) + 1, 2) + zntos(cal.get(Calendar.DAY_OF_MONTH), 2) +
                zntos(cal.get(Calendar.HOUR_OF_DAY), 2) + zntos(cal.get(Calendar.MINUTE), 2) + zntos(cal.get(Calendar.SECOND), 2);
    }


    protected void web(String url){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.android.chrome"));
        }
        catch (Exception e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static String clearChar(String s, String no){
        char c;
        StringBuffer sb = new StringBuffer();

        for(int i=0;i<s.length();i++){
            c = s.charAt(i);
            if(no.indexOf(c)==-1) sb.append(c);
        }
        return sb.toString();
    }

    public static String fileName(String s){
        return clearChar(s,"\\/:*?\"<>|");
    }

    protected void startTxtFile(int flag, String fName){
        Intent it = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        // Filter to only show results that can be "opened", such as
        // a file (as opposed to a list of contacts or timezones).
        it.addCategory(Intent.CATEGORY_OPENABLE);

        // Create a file with the requested MIME type.
        it.setType("text/plain");

        it.putExtra(Intent.EXTRA_TITLE,fileName(fName)+".txt");
        startActivityForResult(it, flag);
    }

    protected void saveTxtFile(Uri url, String txt){
        BufferedWriter f=null;
        ParcelFileDescriptor pfd=null;

        try {
            pfd = getContentResolver().openFileDescriptor(url, "w");
            f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pfd.getFileDescriptor())));
            f.write(txt);
        }
        catch(Exception e){
            msgbox(e.toString());
        }
        finally{
            if(f!=null) try{f.close();}catch(Exception e){}
            if(pfd!=null) try {pfd.close();}catch(Exception e){}
        }
    }

}


/*
 * http://www.acidzazz.com/2013/06/listview-scroll.html
현재 위치값을 알아 내려면, 아래 함수를 호출하여 저장.

ListView.getFirstVisiblePosition();

ListView를 다시 열었을때 onCreate에서 아래 함수 실행.

ListView.setSelection(저장된 값);

ListView.scrollTo 함수를 쓰게 되면 scroll thumb은 이동을 하나 ListView에 list item이 제대로 표시가 안됨.
*/

/*
protected void send_sms(String phone[], String msg){
	final String p[];
	final String m;

	if(phone.length > 20){
		p = new String[20];
		for(int i=0;i<20;i++) p[i]=phone[i];
		m = msg;

		new AlertDialog.Builder(Common.this)
		.setTitle("문자보내기")
		.setMessage("죄송합니다.\n현재 문자전송은 전화번호20개 까지만 지원하고 있습니다.\n차기 버젼에서는 이 부분 개선하겠습니다.\n감사합니다.")
		.setPositiveButton("확 인",
			new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which){
					send_sms(p,m);
				}
			}
		)
		.show();

		return;
	}


 	StringBuffer sb = new StringBuffer();
	for(int i=0;i<phone.length;i++) sb.append((i==0?"":";") + phone[i]);

	//SMS발송
	Uri uri = Uri.parse("smsto:" + sb.toString());
	Intent it = new Intent(Intent.ACTION_SENDTO, uri);
	if(!msg.equals("")) it.putExtra("sms_body", msg);
	startActivity(it);
}
*/

