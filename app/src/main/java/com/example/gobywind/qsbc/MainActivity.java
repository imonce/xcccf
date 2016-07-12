package com.example.gobywind.qsbc;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v4.view.PagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v4.view.ViewPager.OnPageChangeListener;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String EXTRA_MESSAGE = "com.example.gobywind.qsbc.MESSAGE";

    private ViewPager viewPager;//页卡内容
    //private ImageView imageView;// 动画图片
    private TextView textView1, textView2, textView3;
    private List<View> views;// Tab页面列表
    //private int offset = 0;// 动画图片偏移量
    static public int currIndex = 0;// 当前页卡编号
    //private int bmpW;// 动画图片宽度
    private View view1, view2, view3;//各个页卡

    private int label;

    private ListView listView;
    SimpleAdapter adapter;
    private int[] Zcid = new int[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        label = intent.getIntExtra(login.EXTRA_MESSAGE, -1);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        InitTextView();
        InitViewPager();
        InitListView();
    }

    private void InitViewPager()
    {
        viewPager = (ViewPager) findViewById(R.id.vPager);
        views = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.lay1, null);
        view2 = inflater.inflate(R.layout.lay2, null);
        view3 = inflater.inflate(R.layout.lay3, null);

        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(1);
        textView2.setTextColor(Color.YELLOW);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void InitTextView() {
        textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
    }

    private void InitListView() {
        listView = (ListView)view1.findViewById(R.id.list);
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Toast.makeText(MainActivity.this, String.valueOf(Zcid[position]), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, found_detail.class);
            intent.putExtra(EXTRA_MESSAGE, Zcid[position]);
            startActivity(intent);
        }
    }


    private class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            viewPager.setCurrentItem(index);

            textView1.setTextColor(Color.WHITE);
            textView2.setTextColor(Color.WHITE);
            textView3.setTextColor(Color.WHITE);

            switch (index) {
                case 0:
                    textView1.setTextColor(Color.YELLOW);
                    setPage1();
                    break;
                case 1:
                    textView2.setTextColor(Color.YELLOW);
                    setPage2();
                    break;
                case 2:
                    textView3.setTextColor(Color.YELLOW);
                    setPage3();
                    break;
            }
        }
    }

    public class MyOnPageChangeListener implements OnPageChangeListener
    {

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int arg0) {
          //  Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);//显然这个比较简洁，只有一行代码。
            currIndex = arg0;
            textView1.setTextColor(Color.WHITE);
            textView2.setTextColor(Color.WHITE);
            textView3.setTextColor(Color.WHITE);
            switch (currIndex) {
                case 0:
                    textView1.setTextColor(Color.YELLOW);
                    setPage1();
                    break;
                case 1:
                    textView2.setTextColor(Color.YELLOW);
                    setPage2();
                    break;
                case 2:
                    textView3.setTextColor(Color.YELLOW);
                    setPage3();
                    break;
            }
            //   animation.setFillAfter(true);// True:图片停在动画结束位置
            //   animation.setDuration(300);
            //   imageView.startAnimation(animation);
            //   Toast.makeText(MainActivity.this, "您选择了" + viewPager.getCurrentItem() + "页卡", Toast.LENGTH_SHORT).show();
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    public void setPage1()
    {
        adapter = new SimpleAdapter(this,GetListData(),R.layout.listview,new String[]{"title","info","crash"},new int[]{R.id.title,R.id.info,R.id.crash});
        listView.setAdapter(adapter);
    }

    public void setPage2()
    {

    }

    public void setPage3()
    {

    }

    private List<Map<String, Object>> GetListData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        //Database--------------------------------------------------------------------------------------

        for(int i = 0; i < 7; i++)
        {
            Zcid[i] = i * 2;
        }


        map.put("title", "G1");
        map.put("info", "google 1");
        map.put("crash", "10000");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("info", "google 2");
        map.put("crash", "10000");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("crash", "10000");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G4");
        map.put("info", "google 4");
        map.put("crash", "10000");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G5");
        map.put("info", "google 5");
        map.put("crash", "10000");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G6");
        map.put("info", "google 6");
        map.put("crash", "10000");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G7");
        map.put("info", "google 7");
        map.put("crash", "10000");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G8");
        map.put("info", "google 8");
        map.put("crash", "10000");
        list.add(map);

        return list;
    }

    public void Start_CF(View view)
    {
        Intent intent = new Intent(MainActivity.this, start_cf.class);
        startActivity(intent);
    }

    public void My_Cf(View view)
    {
        Intent intent = new Intent(MainActivity.this, my_cf.class);
        startActivity(intent);
    }

    public void password_change(View view)
    {

    }

    public void nickname_change(View view)
    {

    }

    public void head_change(View view)
    {

    }

    public void help(View view)
    {

    }

    public void feedback(View view)
    {

    }

    public void update(View view)
    {

    }

    public void about(View view)
    {
        Toast.makeText(MainActivity.this, "We are XCCCF Group", Toast.LENGTH_SHORT).show();
    }

    public void logout(View view)
    {
        Toast.makeText(MainActivity.this, "Bye", Toast.LENGTH_SHORT).show();
    }

}