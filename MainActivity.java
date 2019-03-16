package com.example.androidproject3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Daily> dailyCollection;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("操作");
        menu.add(0, 1, 0, "删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String year = (String) year_text.getText();
        String mon = (String) mon_text.getText();
        int j;
        int flag = 0;
        for (j = 0; j < dailyCollection.size(); j++) {
            if (year.equals(dailyCollection.get(j).getYear()) && mon.equals(dailyCollection.get(j).getMonth()) && (itemInfo.position+1) == Integer.parseInt(dailyCollection.get(j).getDay())) {
                flag = 1;
                break;
            }
        }
        if(flag==1) {
            switch (item.getItemId()) {
                case 1:
                    theListAdapter.removeItem(itemInfo.position);
                    theListAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
            return super.onContextItemSelected(item);
        }
        else return true;
    }
    public class ListViewAdapter extends BaseAdapter {
        ArrayList<View> itemViews;

        public ListViewAdapter(ArrayList<Daily> goodsCollection,String year,String mon) {
            itemViews = new ArrayList<View>(goodsCollection.size());
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
            String today_year = sdf1.format(new Date());

            SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
            String today_mon = sdf2.format(new Date());

            if (year.equals(today_year) && mon.equals(today_mon)) {
                SimpleDateFormat sdf3 = new SimpleDateFormat("dd");
                String today_day = sdf3.format(new Date());
                int flag;
                int k = Integer.parseInt(today_day);

                for (int i = 1; i < k+1; ++i) {
                    flag = 0;
                    for (int j = 0; j < goodsCollection.size(); j++) {
                        if (i == Integer.parseInt(goodsCollection.get(j).getDay()) && year.equals(goodsCollection.get(j).getYear()) && mon.equals(goodsCollection.get(j).getMonth())) {
                            itemViews.add(makeItemView(goodsCollection.get(j).getName()
                                    , goodsCollection.get(j).getPictureId())
                            );
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        itemViews.add(makeItemView2());
                    }
                }
            } else {
                if (mon.equals("01") || mon.equals("03") || mon.equals("05") || mon.equals("07") || mon.equals("08") || mon.equals("10") || mon.equals("12")) {
                    int flag;
                    for (int i = 1; i < 32; ++i) {
                        flag = 0;
                        for (int j = 0; j < goodsCollection.size(); j++) {
                            if (i == Integer.parseInt(goodsCollection.get(j).getDay()) && year.equals(goodsCollection.get(j).getYear()) && mon.equals(goodsCollection.get(j).getMonth())) {
                                itemViews.add(makeItemView(goodsCollection.get(j).getName()
                                        , goodsCollection.get(j).getPictureId())
                                );
                                flag = 1;
                            }
                        }
                        if (flag == 0) {
                            itemViews.add(makeItemView2());
                        }
                    }
                }

                if (mon.equals("04") || mon.equals("06") || mon.equals("09") || mon.equals("11")) {
                    int flag;
                    for (int i = 1; i < 31; ++i) {
                        flag = 0;
                        for (int j = 0; j < goodsCollection.size(); j++) {
                            if (i == Integer.parseInt(goodsCollection.get(j).getDay()) && year.equals(dailyCollection.get(j).getYear()) && mon.equals(goodsCollection.get(j).getMonth())) {
                                itemViews.add(makeItemView(goodsCollection.get(j).getName()
                                        , goodsCollection.get(j).getPictureId())
                                );
                                flag = 1;
                            }
                        }
                        if (flag == 0) {
                            itemViews.add(makeItemView2());
                        }
                    }
                }

                if (mon.equals("02")) {
                    int flag;
                    for (int i = 1; i < 29; ++i) {
                        flag = 0;
                        for (int j = 0; j < goodsCollection.size(); j++) {
                            if (i == Integer.parseInt(goodsCollection.get(j).getDay()) && year.equals(goodsCollection.get(j).getYear()) && mon.equals(dailyCollection.get(j).getMonth())) {
                                itemViews.add(makeItemView(goodsCollection.get(j).getName()
                                        , goodsCollection.get(j).getPictureId())
                                );
                                flag = 1;
                            }
                        }
                        if (flag == 0) {
                            itemViews.add(makeItemView2());
                        }
                    }

                }
            }
        }
        private  String mWay;
        String mon,year;
        int mon_day[]={31,28,31,30,31,30,31,31,30,31,30,31};
        public void removeItem(int positon){
            String year=(String) year_text.getText();
            String mon=(String)mon_text.getText();
            int j;
            for(j=0;j<dailyCollection.size();j++)
            {
                if(year.equals(dailyCollection.get(j).getYear())&&mon.equals(dailyCollection.get(j).getMonth())&&(positon+1) == Integer.parseInt(dailyCollection.get(j).getDay()))
                {
                    break;
                }
            }
            itemViews.remove(positon);
            itemViews.add(positon,makeItemView2());
            dailyCollection.remove(j);
            DailyCollectionOperater operater=new DailyCollectionOperater();
            operater.save(MainActivity.this.getBaseContext(),dailyCollection);
        }

        public void removeItem2(int positon){
            itemViews.remove(positon);
        }

        public void addItem2(int i,String itemTitle){
            String year=(String) year_text.getText();
            String mon=(String)mon_text.getText();
            final Calendar c = Calendar.getInstance();
            Daily daily=new Daily();
            daily.setName(itemTitle);
            String date_day;
            if(i<10)
            {
                date_day=String.valueOf(i+1);
                date_day="0"+date_day;
                daily.setDay(date_day);
            }
            else
            {
                date_day=String.valueOf(i+1);
                daily.setDay(date_day);
            }

            SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
            String today_year=sdf1.format(new Date());

            SimpleDateFormat sdf2=new SimpleDateFormat("MM");
            String today_mon=sdf2.format(new Date());

            SimpleDateFormat sdf3=new SimpleDateFormat("dd");
            String today_day=sdf3.format(new Date());

            int ty=Integer.parseInt(today_year);
            int tm=Integer.parseInt(today_mon);
            int td=Integer.parseInt(today_day);

            int insert_year=Integer.parseInt(year);
            int insert_mon=Integer.parseInt(mon);
            int insert_day=i+1;

            int d=0;
            if(ty==insert_year)
            {
                int total=0;
                for(int k=(insert_mon-1);k<(tm-1);k++)
                {
                    total=total+mon_day[k];
                }
                d=total+td-insert_day;
            }

            if(ty>insert_year)
            {
                if(tm>insert_mon||tm==insert_mon)
                {
                    int total=0;
                    for(int k=(insert_mon-1);k<(tm-1);k++)
                    {
                        total=total+mon_day[k];
                    }
                    d=(ty-insert_year)*365+total+td-insert_day;
                }
                else if(tm<insert_mon)
                {
                    int total=0;
                    for(int k=(insert_mon-1);k<(tm-1);k=(k+1)%12)
                    {
                        total=total+mon_day[k];
                    }
                    d=(ty-insert_year-1)*365+total+td-insert_day;
                }
            }

            int last=d%7;

            mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

            int mway=Integer.parseInt(mWay);


            int last_mway;
            int temp=mway-last;
            if(temp<0)last_mway=temp+7;
            else last_mway=temp;

            if(last_mway==2)daily.setPictureId(R.drawable.mon);
            else if(last_mway==3)daily.setPictureId(R.drawable.z2);
            else if(last_mway==4)daily.setPictureId(R.drawable.wed);
            else if(last_mway==5)daily.setPictureId(R.drawable.a4);
            else if(last_mway==6)daily.setPictureId(R.drawable.fri);
            else if(last_mway==1)daily.setPictureId(R.drawable.sun);
            else daily.setPictureId(R.drawable.sat);

            daily.setMonth(mon);

            daily.setYear(year);


            int j;
            for(j=0;j<dailyCollection.size();j++)
            {
                if(year.equals(dailyCollection.get(j).getYear())&&mon.equals(dailyCollection.get(j).getMonth())&&(i+1) == Integer.parseInt(dailyCollection.get(j).getDay()))
                {
                    break;
                }
            }
            dailyCollection.add(j,daily);
            DailyCollectionOperater operater=new DailyCollectionOperater();
            operater.save(MainActivity.this.getBaseContext(),dailyCollection);

            View view=makeItemView(itemTitle,daily.getPictureId());
            itemViews.add(i,view);
        }

        public int getCount() {
            return itemViews.size();
        }

        public View getItem(int position) {
            return itemViews.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        private View makeItemView(String strTitle, int resId) {
            LayoutInflater inflater = (LayoutInflater)MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.daily_list, null);

            // 通过findViewById()方法实例R.layout.item内各组件
            TextView title = itemView.findViewById(R.id.itemTitle);
            title.setText(strTitle);
            ImageView image = itemView.findViewById(R.id.itemImage);
            image.setImageResource(resId);

            return itemView;
        }

        private View makeItemView2()
        {
            LayoutInflater inflater = (LayoutInflater)MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.point_list,null);

            return itemView;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //if (convertView == null)
            return itemViews.get(position);
            //return convertView;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(resultCode == RESULT_OK&&requestCode<32)
                {
                    String name = data.getStringExtra("name");
                    theListAdapter.addItem2(requestCode,name);
                    theListAdapter.notifyDataSetChanged();
                }
    }

    ListViewAdapter theListAdapter;

    PopupMenu popupMenu;
    PopupMenu popupMenu2;
    TextView year_text;
    TextView mon_text;
    ListView listViewGoodsPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat sdf=new SimpleDateFormat("MM");
        String date1=sdf.format(new Date());

        mon_text=findViewById(R.id.text_mon);
        mon_text.setText(date1);

        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy");
        String date2=sdf2.format(new Date());

        year_text=findViewById(R.id.text_year);
        year_text.setText(date2);

        mon_text.setOnClickListener(new textClick());
        year_text.setOnClickListener(new textClick2());
        popupMenu=new PopupMenu(this,mon_text);
        popupMenu.inflate(R.menu.menu);
        popupMenu.setOnMenuItemClickListener(new menuClick());

        popupMenu2=new PopupMenu(this,year_text);
        popupMenu2.inflate(R.menu.menu_year);
        popupMenu2.setOnMenuItemClickListener(new menuClick2());

        DailyCollectionOperater operater=new DailyCollectionOperater();
        dailyCollection=operater.load(getBaseContext());
        if(dailyCollection==null)
            dailyCollection= new ArrayList<>();


        listViewGoodsPrice = (ListView) this.findViewById(R.id.daily1);
        theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());

        listViewGoodsPrice.setAdapter(theListAdapter);
        listViewGoodsPrice.setOnItemClickListener(new mListViewItemClick2());
        registerForContextMenu(listViewGoodsPrice);

        SimpleDateFormat sdf3=new SimpleDateFormat("dd");
        String date3=sdf3.format(new Date());
        final int a=Integer.parseInt(date3);
        //final int b=a-1;
        Button btn=(Button)findViewById(R.id.button_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,addActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("name"," ");
                intent.putExtras(bundle);
                theListAdapter.removeItem2(a-1);
                startActivityForResult(intent,a-1);

    }
        });

        Button btn_scan=(Button)findViewById(R.id.button_list);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,scanActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("date", (String) year_text.getText());
                intent.putExtras(bundle);
                startActivityForResult(intent,100);

            }
        });
    }

    class menuClick implements PopupMenu.OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.jan:
                    mon_text.setText("01");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.feb:
                    mon_text.setText("02");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.mar:
                    mon_text.setText("03");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.apr:
                    mon_text.setText("04");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.may:
                    mon_text.setText("05");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.jun:
                    mon_text.setText("06");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.jul:
                    mon_text.setText("07");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.aug:
                    mon_text.setText("08");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.sep:
                    mon_text.setText("09");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.oct:
                    mon_text.setText("10");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.nov:
                    mon_text.setText("11");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.dec:
                    mon_text.setText("12");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;

            }
            return true;
        }
    }

    class textClick implements View.OnClickListener {
        public void onClick(View v) {
            if (v.getId() == R.id.text_mon) {
                popupMenu.show();
            }
        }
    }

    class menuClick2 implements PopupMenu.OnMenuItemClickListener {
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.eightteen:
                    year_text.setText("2018");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.sevseteen:
                    year_text.setText("2017");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
                case R.id.sixteen:
                    year_text.setText("2016");
                    theListAdapter= new ListViewAdapter(dailyCollection,(String) year_text.getText(),(String) mon_text.getText());
                    listViewGoodsPrice.setAdapter(theListAdapter);
                    break;
            }
            return true;
        }
    }

    class textClick2 implements View.OnClickListener {
        public void onClick(View v) {
            if (v.getId() == R.id.text_year) {
                popupMenu2.show();
            }
        }
    }


    class mListViewItemClick2 implements android.widget.AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int i, long l) {
            Intent intent2=new Intent(MainActivity.this,changeActivity.class);
            Bundle bundle=new Bundle();
            String year=(String) year_text.getText();
            String mon=(String)mon_text.getText();
            int j;
            int flag=0;
            for(j=0;j<dailyCollection.size();j++)
            {
                if(year.equals(dailyCollection.get(j).getYear())&&mon.equals(dailyCollection.get(j).getMonth())&&(i+1) == Integer.parseInt(dailyCollection.get(j).getDay()))
                {
                    flag=1;
                    break;
                }
            }
            if(flag==1) {
                bundle.putString("name", dailyCollection.get(j).getName());
                intent2.putExtras(bundle);
                theListAdapter.removeItem2(i);
                dailyCollection.remove(j);
                startActivityForResult(intent2, i);
            }
            else
            {
                Intent intent=new Intent(MainActivity.this,addActivity.class);
                Bundle bundle2=new Bundle();
                bundle2.putString("name"," ");
                intent.putExtras(bundle2);
                theListAdapter.removeItem2(i);
                startActivityForResult(intent,i);
            }
        }
    }
}

