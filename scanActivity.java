package com.example.androidproject3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class scanActivity extends AppCompatActivity {

    private ArrayList<Daily> dailyCollection2;

    public class ListViewAdapter2 extends BaseAdapter {
        ArrayList<View> itemViews;

        public ListViewAdapter2(ArrayList<Daily> goodsCollection,String str,String year) {
            itemViews = new ArrayList<View>(goodsCollection.size());
            if (str.equals("01") || str.equals("03") || str.equals("05") || str.equals("07") || str.equals("08") || str.equals("10") || str.equals("12")) {
                for(int j=1;j<32;j++) {
                    for (int i = 0; i < goodsCollection.size(); ++i) {
                        if (str.equals(dailyCollection2.get(i).getMonth()) && year.equals(dailyCollection2.get(i).getYear())&&j==Integer.parseInt(dailyCollection2.get(i).getDay())) {
                            itemViews.add(makeItemView(goodsCollection.get(i).getName(), goodsCollection.get(i).getMonth() + "/",
                                    goodsCollection.get(i).getDay() + " ", goodsCollection.get(i).getYear() + "/"
                                    )
                            );
                        }
                    }
                }
            }

            if (str.equals("04") || str.equals("06") || str.equals("09") || str.equals("11")) {
                for(int j=1;j<31;j++) {
                    for (int i = 0; i < goodsCollection.size(); ++i) {
                        if (str.equals(dailyCollection2.get(i).getMonth()) && year.equals(dailyCollection2.get(i).getYear())&&j==Integer.parseInt(dailyCollection2.get(i).getDay())) {
                            itemViews.add(makeItemView(goodsCollection.get(i).getName(), goodsCollection.get(i).getMonth() + "/",
                                    goodsCollection.get(i).getDay() + " ", goodsCollection.get(i).getYear() + "/"
                                    )
                            );
                        }
                    }
                }
            }

            if (str.equals("02")) {
                for(int j=1;j<29;j++) {
                    for (int i = 0; i < goodsCollection.size(); ++i) {
                        if (str.equals(dailyCollection2.get(i).getMonth()) && year.equals(dailyCollection2.get(i).getYear())&&j==Integer.parseInt(dailyCollection2.get(i).getDay())) {
                            itemViews.add(makeItemView(goodsCollection.get(i).getName(), goodsCollection.get(i).getMonth() + "/",
                                    goodsCollection.get(i).getDay() + " ", goodsCollection.get(i).getYear() + "/"
                                    )
                            );
                        }
                    }
                }
            }

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

        private View makeItemView(String strTitle, String mon,String Day,String Year) {
            LayoutInflater inflater = (LayoutInflater)scanActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.scan_list, null);

            // 通过findViewById()方法实例R.layout.item内各组件

            TextView title = itemView.findViewById(R.id.itemTitle);
            title.setText(strTitle);

            TextView year = itemView.findViewById(R.id.itemyear);
            year.setText(Year);

            TextView month = itemView.findViewById(R.id.itemmon);
            month.setText(mon);

            TextView day=itemView.findViewById(R.id.itemday);
            day.setText(Day);

            return itemView;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            //if (convertView == null)
            return itemViews.get(position);
            //return convertView;
        }
    }
    ListViewAdapter2 theListAdapter2;
    ListView listViewGoodsPrice;
    String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_scan);
        DailyCollectionOperater operater=new DailyCollectionOperater();
        dailyCollection2=operater.load(getBaseContext());
        if(dailyCollection2==null)
            dailyCollection2= new ArrayList<>();
        listViewGoodsPrice = (ListView) this.findViewById(R.id.daily_scan);

        Bundle bundle=this.getIntent().getExtras();
        final String date= bundle.getString("date");

        GridView gv=(GridView)findViewById(R.id.gridview);
        gv.setAdapter(new Myadapter(this));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1330,LinearLayout.LayoutParams.FILL_PARENT);
        gv.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gv.setColumnWidth(110); // 设置列表项宽
        gv.setHorizontalSpacing(0); // 设置列表项水平间距
        gv.setStretchMode(GridView.NO_STRETCH);
        gv.setNumColumns(12); // 设置列数量=列表集合数
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "1",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 1 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "2",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 2 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "3",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 3 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "4",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 4 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "5",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 5 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "6",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 6 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "7",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 7 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "8",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 8 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "9",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 9 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "10",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 10 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "11",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    } else if (position == 11 ) {
                        theListAdapter2 = new ListViewAdapter2(dailyCollection2, "12",date);
                        listViewGoodsPrice.setAdapter(theListAdapter2);
                    }
                }

        });

    }

    class Myadapter extends BaseAdapter {

        private Context context;
        private int[] imgs=
                {
                        R.drawable.jan,
                        R.drawable.feb,
                        R.drawable.mar,
                        R.drawable.apr,
                        R.drawable.may,
                        R.drawable.jun,
                        R.drawable.jul,
                        R.drawable.aug,
                        R.drawable.sep,
                        R.drawable.oct,
                        R.drawable.nov,
                        R.drawable.dec,
                };
        Myadapter(Context context){this.context=context;}

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public Object getItem(int item) {
            return item;
        }

        @Override
        public long getItemId(int id) {
            return id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView==null)
            {
                imageView=new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(100,100));
                imageView.setAdjustViewBounds(false);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }
            else
            {
                imageView=(ImageView)convertView;
            }
            imageView.setImageResource(imgs[position]);
            return imageView;
        }
    }
}


