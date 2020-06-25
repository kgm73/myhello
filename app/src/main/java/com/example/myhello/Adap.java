package com.example.myhello;

import java.io.Serializable;
import java.util.*;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Adap extends BaseAdapter {
    final static int ROWS = 9;
    ArrayList<row> m_rows;

    public Adap(){
        m_rows = new ArrayList<row>();
    }

    public int getCount(){
        return m_rows.size();
    }

    public String getItem(int y){
        return getc(y,0);
    }

    public long getItemId(int y){
        return y;
    }

    protected View inflate(int resource, ViewGroup root){
        final Context context = root.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        return inflater.inflate(resource, root, false);
    }

    protected static void set_txt(View v, int id, String s){
        ((TextView)v.findViewById(id)).setText(s);
    }

    protected row create_row(String... strs){
        String c[] = new String[ROWS];

        int i=0;
        for(String s : strs){
            c[i++]=s;
            if(i==ROWS) break;
        }

        while(i<ROWS) c[i++]="";

        return new row(c);
    }

    public void add(String... strs){
        m_rows.add(create_row(strs));
    }

    public void add(int y, String... strs){
        m_rows.add(y, create_row(strs));
    }

    public void add(String arr[], int start, int len){
        String a[] = new String[len];
        System.arraycopy(arr, start, a, 0, len);
        add(a);
    }

    public void remove(int y){
        m_rows.remove(y);
    }

    public void clear(){
        m_rows.clear();
    }

    public String getc(int y, int x){
        return m_rows.get(y).c[x];
    }

    public void setc(int y, int x, String s){
        m_rows.get(y).c[x] = s;
    }

    public boolean getchk(int y){
        return m_rows.get(y).chk;
    }

    public void setchk(int y, boolean chk){
        m_rows.get(y).chk = chk;
    }

    /*public String[] outs(){
        int cnt = getCount();
        String s[] = new String[cnt*(ROWS+1)];

        for(int i=0;i<cnt;i++){
            System.arraycopy(m_rows.get(i).c, 0, s, i*(ROWS+1), ROWS);
            s[i*(ROWS+1)+ROWS] = m_rows.get(i).chk?"1":"0";
        }
        return s;
    }

    public void ins(String s[]){
        int cnt = s.length / (ROWS+1);
        String c[];
        for(int i=0;i<cnt;i++){
            c = new String[ROWS];
            System.arraycopy(s, i*(ROWS+1), c, 0, ROWS);
            m_rows.add(new row(c, s[i*(ROWS+1)+ROWS].equals("1")));
        }
    }*/

    public void outs(Bundle outState, String key){
        outState.putSerializable(key,m_rows);
    }

    public void ins(Bundle inState, String key){
        m_rows = (ArrayList<row>)inState.getSerializable(key);
    }

    public void nochk(){
        int cnt = getCount();
        for(int i=0;i<cnt;i++) setchk(i,false);
    }

    public View getView(int y, View v, ViewGroup parent){
        if(v==null)
            v = new TextView(parent.getContext());

        //v.setBackgroundColor(getchk(y) ? v.getResources().getColor(R.color.list_selector) : 0x00000000); //0x00000000 투명색

        TextView tv = (TextView)v;

        tv.setText(getc(y,0));
        tv.setTextSize(20);
        tv.setHeight(Common.get_pixel(40));
        tv.setGravity(Gravity.CENTER_VERTICAL); //| Gravity.CENTER_HORIZONTAL
        tv.setPadding(Common.get_pixel(2), 0, Common.get_pixel(8), 0);

        return v;
    }

    static class row implements Serializable {
        private static final long serialVersionUID = 100L;  //Serializable위해 그냥 넣어줌

        String c[];
        boolean chk;

        public row(String c[], boolean chk){
            this.c = c;
            this.chk = chk;
        }

        public row(String c[]){
            this.c = c;
            this.chk = false;
        }
    }

    public void sort(int i, boolean asc){
        sort(i, asc, true);
    }

    public void sort(int i, boolean asc, boolean str){
        Collections.sort(m_rows, new ItemCompare(i, asc, str));
    }

    class ItemCompare implements Comparator<row> {
        int i;
        boolean asc;
        boolean str;

        public ItemCompare(int i, boolean asc, boolean str){
            this.i = i;
            this.asc = asc;
            this.str = str;
        }

        public int compare(row a, row b){
            String as = Common.nvl(a.c[i]);
            String bs = Common.nvl(b.c[i]);

            return (asc ? 1 : -1) * (str ? as.compareTo(bs) : Common.compareTo(as,bs));
        }
    }

    //선택//////////////////////////////////////////////////////////////////////////////////////////
    int select_pos_ = -1;
    long select_time_ = 0;

    public void select(int pos){
        select_pos_ = pos;
        select_time_ = System.currentTimeMillis();
    }

    /*protected void viewSelect(View v, int pos){
        if(System.currentTimeMillis() - select_time_ < 1000 && select_pos_==pos)
            v.setBackgroundColor(v.getResources().getColor(R.color.list_selector));
        else
            v.setBackgroundColor(0x00000000); //#00000000 은 투명색
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static TextView txtView(View v, int id){
        return (TextView)v.findViewById(id);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] get(int y){
        return m_rows.get(y).c;
    }
}
