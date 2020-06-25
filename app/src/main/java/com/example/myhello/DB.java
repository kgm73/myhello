package com.example.myhello;

import android.database.*;
import android.database.sqlite.*;
import android.content.*;

//sysdata
//EMAIL_SVR
//START_COUNT  앱실행횟수

class DB extends SQLiteOpenHelper{
    Context m_ctx;

    public DB(Context context){
        super(context, "word.db", null, 1);
        m_ctx = context;
    }

    public void onCreate(SQLiteDatabase db){
        String sql;

        /*sql = "CREATE TABLE sysdata( " +
                "	key	text 	not null, " +
                "	val	text, " +
                "	primary key(key))";
        db.execSQL(sql);

        sql = "CREATE TABLE word( " +
                "	gid	integer	NOT NULL default 0, " +
                "	seq integer	NOT NULL default 0, " +
                "	gbn integer	NOT NULL default 0 check(gbn in (0,1)), " +
                "	eng text 	NOT NULL, " +
                "	kor text 	NULL, " +
                "	primary key(gid, seq))";
        db.execSQL(sql);

        sql = "CREATE TABLE store( " +
                "	st		integer	NOT NULL, " +
                "	name		text	NOT NULL unique, " +
                "	lok		integer	NOT NULL default 0 check(lok in (0,1)), " +
                "	etc    text	    NULL, " +
                "	primary key(st))";
        db.execSQL(sql);

        sql = "CREATE TABLE custom( " +
                "	st		integer	NOT NULL, " +
                "	custom		integer	NOT NULL, " +
                "	name		text	NOT NULL, " +
                "	phone		text	NULL, " +
                "	email		text	NULL, " +
                "	zip		    text	NULL, " +
                "	addr		text	NULL, " +
                "	memo		text	NULL, " +
                "	primary key(st, custom), " +
                "	unique(st, name))";
        db.execSQL(sql);

        sql = "CREATE TABLE item( " +
                "	st		integer	NOT NULL, " +
                "	item		integer	NOT NULL, " +
                "	name		text	NOT NULL, " +
                "	barcode		text	NULL, " +
                "	safe		integer	NOT NULL default 0 check(safe>=0), " +
                "	iprice		integer	NOT NULL default 0 check(iprice>=0), " +
                "	oprice		integer	NOT NULL default 0 check(oprice>=0), " +
                "	memo		text	NULL, " +
                "	PRIMARY KEY (st, item), " +
                "	unique(st, name))";
        db.execSQL(sql);

        sql = "CREATE TABLE loc( " +
                "	st		integer	NOT NULL, " +
                "	loc		integer	NOT NULL, " +
                "	name		text	NOT NULL, " +
                "	PRIMARY KEY (st, loc), " +
                "	unique(st, name))";
        db.execSQL(sql);



        sql = "CREATE TABLE place( " +
                "	st		integer	NOT NULL, " +
                "	item		integer	NOT NULL, " +
                "	loc		integer	NOT NULL, " +
                "	PRIMARY KEY (st, item, loc))";
        db.execSQL(sql);

        sql = "CREATE TABLE page( " +
                "	st		integer	NOT NULL, " +
                "	page	integer	NOT NULL, " +
                "	time	text      NOT NULL, " +
                "	io  	text	   NOT NULL check(io in ('I','O')), " +
                "	custom	integer	NULL, " +
                "	cost	integer	NOT NULL default 0 check(cost>=0), " +
                "	memo	text	    NULL, " +
                "	amount	integer	NOT NULL default 0 check(amount>=0), " +
                "	PRIMARY KEY (st, page))";
        db.execSQL(sql);

        sql = "CREATE TABLE pageitem( " +
                "	st		integer	NOT NULL, " +
                "	page	integer	NOT NULL, " +
                "	item	integer	NOT NULL, " +
                "	cnt		integer	NOT NULL check(cnt>0), " +
                "	PRIMARY KEY (st, page, item))";
        db.execSQL(sql);


        //SAMPLE
        insert_store(db, 1, "sample store", 0);

        String items[] = m_ctx.getResources().getStringArray(R.array.sample_item);
        insert_item(db, 1, 1, items[0], "8803186200200", 100, 500, 800, "2L");
        insert_item(db, 1, 2, items[1], "9788994506159", 50,100000,110000,"");
        insert_item(db, 1, 3, items[2], "9788968480423", 80,20000,25000,"");
        insert_item(db, 1, 4, items[3], "1234567880423", 200,500,600,"");
        insert_item(db, 1, 5, items[4], "9788123456723", 60,55000,57000,"");
        insert_item(db, 1, 6, items[5], "9788967788542", 100,2000,2500,"");
        insert_item(db, 1, 7, items[6], "9542155412512", 70,5000,5800,"");
        insert_item(db, 1, 8, items[7], "8801133000484", 60,50000,55000,"");

        String customs[] = m_ctx.getResources().getStringArray(R.array.sample_custom);
        insert_custom(db, 1, 1, customs[0], "", "", "", "", "" );
        insert_custom(db, 1, 2, customs[1], "", "", "", "", "" );
        insert_custom(db, 1, 3, customs[2], "", "", "", "", "" );
        insert_custom(db, 1, 4, customs[3], "", "", "", "", "" );
        insert_custom(db, 1, 5, customs[4], "", "", "", "", "" );
        insert_custom(db, 1, 6, customs[5], "", "", "", "", "" );


        sql ="insert into page(st,page,time,io,custom,cost,memo,amount) " +
                "select 1, 1, '201501011205', 'I', null, 0, '#"+ m_ctx.getText(R.string.start_item)+"', 0 union all " +
                "select 1, 2, '201501021205', 'I', 1, 0, '', 510500 union all " +
                "select 1, 3, '201501031205', 'O', 2, 2500, '', 113000 union all " +
                "select 1, 4, '201501041205', 'I', 2, 0, '', 85000 union all " +
                "select 1, 5, '201501051205', 'O', 4, 0, '', 217600 union all " +
                "select 1, 6, '201501061205', 'O', 3, 2500, '', 695500 union all " +
                "select 1, 7, '201501071205', 'O', 3, 3000, '', 248600 union all " +
                "select 1, 8, '201501081205', 'O', 6, 2500, '', 789200";
        db.execSQL(sql);

        sql ="insert into pageitem(st,page,item,cnt) " +
                "select 1,1,1,304 union all " +
                "select 1,1,2,110 union all " +
                "select 1,1,3,154 union all " +
                "select 1,1,4,290 union all " +
                "select 1,1,5,124 union all " +
                "select 1,1,6,105 union all " +
                "select 1,1,7,354 union all " +
                "select 1,1,8,129 union all " +

                "select 1,2,2,4 union all " +
                "select 1,2,4,1 union all " +
                "select 1,2,6,5 union all " +
                "select 1,2,8,2 union all " +

                "select 1,3,3,2 union all " +
                "select 1,3,4,10 union all " +
                "select 1,3,5,1 union all " +

                "select 1,4,3,1 union all " +
                "select 1,4,4,5 union all " +
                "select 1,4,5,1 union all " +

                "select 1,5,2,1 union all " +
                "select 1,5,4,3 union all " +
                "select 1,5,7,1 union all " +
                "select 1,5,3,4 union all " +

                "select 1,6,8,4 union all " +
                "select 1,6,7,1 union all " +
                "select 1,6,6,5 union all " +
                "select 1,6,5,8 union all " +
                "select 1,6,4,2 union all " +

                "select 1,7,8,2 union all " +
                "select 1,7,2,1 union all " +
                "select 1,7,7,4 union all " +
                "select 1,7,4,9 union all " +

                "select 1,8,1,4 union all " +
                "select 1,8,2,2 union all " +
                "select 1,8,4,5 union all " +
                "select 1,8,6,20 union all " +
                "select 1,8,5,9";
        db.execSQL(sql);

        set_sysdata("EMAIL_SVR","naver.com,hanmail.net,daum.net,paran.com,gmail.com",db);
        */
    }

    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        String sql;

        /*if(old_version < 2){
            sql="ALTER TABLE item RENAME TO _item_old";
            db.execSQL(sql);
        }*/
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void sql_exec(String sql){	//예외가 발생하면 프로그램종료하게... 예외처리 안해줌
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        close();
    }

    /*
        FIELD_TYPE_NULL (0)
        FIELD_TYPE_INTEGER (1)
        FIELD_TYPE_FLOAT (2)
        FIELD_TYPE_STRING (3)
        FIELD_TYPE_BLOB (4)
    */

    public static String[] get_datas(String sql, SQLiteDatabase db){
        Cursor cur = db.rawQuery(sql, null);
        int c = cur.getColumnCount();
        String a[] = new String[c*cur.getCount()];

        int j=0;
        while(cur.moveToNext())
            for(int i=0;i<c;i++)
                a[j++]=Common.nvl(cur.getType(i)==Cursor.FIELD_TYPE_FLOAT ? cur.getDouble(i)+"" : cur.getString(i));

        cur.close();
        return a;
    }

    public static String[] getDatas(Cursor cur){
        int c = cur.getColumnCount();
        String a[] = new String[c];
        for(int i=0;i<c;i++)
            a[i]=Common.nvl(cur.getType(i)==Cursor.FIELD_TYPE_FLOAT ? cur.getDouble(i)+"" : cur.getString(i));
        return a;
    }

    /*
    public static String[] get_datas(String sql, SQLiteDatabase db){
        Cursor cur = db.rawQuery(sql, null);
        int c = cur.getColumnCount();
        String a[] = new String[c*cur.getCount()];

        int j=0;
        while(cur.moveToNext()) for(int i=0;i<c;i++) a[j++]=Common.nvl(cur.getString(i));

        cur.close();
        return a;
    }*/

    public String[] get_datas(String sql){
        String a[] = get_datas(sql, getReadableDatabase());
        close();
        return a;
    }

    public String get_data(String sql){
        String a[] = get_datas(sql);
        return a.length==0 ? "" : a[0];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public String get_sysdata(String key){
        return get_data("select val from sysdata where key=" + Common.c(key) + " limit 1");
    }

    public void set_sysdata(String key, String val, SQLiteDatabase db){
        db.execSQL("insert or replace into sysdata(key,val) values(" + Common.c(key) + "," + Common.c(Common.nvl(val)) + ")");
    }

    public void set_sysdata(String key, String val){
        SQLiteDatabase db = getWritableDatabase();
        set_sysdata(key, val, db);
        close();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // -1 리턴시 에러
    // http://kuroikuma.tistory.com/75
    // https://www.programcreek.com/java-api-examples/?class=android.database.sqlite.SQLiteDatabase&method=insert

    public static long insert_store(SQLiteDatabase db, int st, String name, int lok){
        ContentValues cv = new ContentValues();
        cv.put("st",st);
        cv.put("name",name);
        cv.put("lok",lok);
        return db.insert("store", null, cv);
    }

    public static long insert_item(SQLiteDatabase db, int st, int item, String name, String barcode, double safe, double iprice, double  oprice, String memo){
        ContentValues cv = new ContentValues();
        cv.put("st",st);
        cv.put("item",item);
        cv.put("name",name);
        cv.put("barcode",barcode);
        cv.put("safe",safe);
        cv.put("iprice",iprice);
        cv.put("oprice",oprice);
        cv.put("memo",memo);
        return db.insert("item", null, cv);
    }

    public static long insert_custom(SQLiteDatabase db, int st, int custom, String name, String phone, String email, String zip, String addr, String memo){
        ContentValues cv = new ContentValues();
        cv.put("st",st);
        cv.put("custom",custom);
        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("email",email);
        cv.put("zip",zip);
        cv.put("addr",addr);
        cv.put("memo",memo);
        return db.insert("custom", null, cv);
    }

    //custom null처리 해야함...
    public static long insert_page(SQLiteDatabase db, int st, int page, String time, String io, int custom, double cost, String memo, double amount){
        ContentValues cv = new ContentValues();
        cv.put("st",st);
        cv.put("page",page);
        cv.put("time",time);
        cv.put("io",io);
        cv.put("custom",custom);
        cv.put("cost",cost);
        cv.put("memo",memo);
        cv.put("amount",amount);
        return db.insert("page", null, cv);
    }

    public static long insert_pageitem(SQLiteDatabase db, int st, int page, int item, String cnt){
        ContentValues cv = new ContentValues();
        cv.put("st",st);
        cv.put("page",page);
        cv.put("item",item);
        //cv.put("cnt",Common.toDouble(cnt));
        return db.insert("pageitem", null, cv);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkNum(String s){  //정수, 실수
        if(Common.empty(s)) return true;
        //Double
        //
        // if(Common.compareTo(s,Common.toDouble(s)+"") != 0) return false;  //insert시 toDouble해서리...   향후 필요없으면 제가가능
        //SQLite  같은수가 나오면...
        if(Common.compareTo(s, get_data("select "+s)) != 0) return false;
        return true;
    }
}
