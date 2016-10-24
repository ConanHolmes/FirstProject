package com.ningjiahao.firstproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ningjiahao.firstproject.IntroduceFragment.IntroduceFragment;
import com.ningjiahao.firstproject.QueryFragment.QueryFragment;
import com.ningjiahao.firstproject.R;
import com.ningjiahao.firstproject.WeatherFragment.WeatherFragment;
import com.yztc.zxinglibrary.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ningjiahao.firstproject.R.id.img;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.navigation)
    NavigationView navigation;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private TabLayout tab;
    private ViewPager vp;
    private List<Fragment> lst;
    public static final String[] TAB_NAME = {
            "主页", "查票", "天气"
    };
    private Myadapter my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //实例化Fragment
        initFragment();
        //对控件实例化
        initView();


    }

    private void initFragment() {
        lst = new ArrayList<>();
        IntroduceFragment introduce = new IntroduceFragment();
        lst.add(introduce);
        QueryFragment query = new QueryFragment();
        lst.add(query);
        WeatherFragment weather = new WeatherFragment();
        lst.add(weather);
    }

    private void initView() {

        toolbar.setNavigationIcon(R.drawable.cehua);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent ii=new Intent();
                switch (item.getItemId()){
                    case R.id.xiaohua:
                        ii.setClass(MainActivity.this,XiaohuaActivity.class);
                        startActivity(ii);
                        break;
                    case R.id.star:
                        ii.setClass(MainActivity.this,StarActivity.class);
                        startActivity(ii);
                        break;
                    case R.id.movie:
                        ii.setClass(MainActivity.this,MovieActivity.class);
                        startActivity(ii);
                        break;
                    case R.id.xiexie:
                        ii.setClass(MainActivity.this,XieActivity.class);
                        startActivity(ii);
                        break;
                    case R.id.gaungguang:
                        ii.setClass(MainActivity.this,CaptureActivity.class);
                        startActivityForResult(ii, REQUEST_CODE_SCAN);
                        break;
                    case R.id.shoucang:
                        break;
                    case R.id.finish:
                        MainActivity.this.finish();
                        break;
                }
                return false;
            }
        });
        //setSupportActionBar(toolbar);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(4);
        my = new Myadapter(getSupportFragmentManager());
        vp.setAdapter(my);
        tab.setupWithViewPager(vp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return lst.get(position);
        }

        @Override
        public int getCount() {
            return lst.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_NAME[position];
        }
    } @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Intent intent=new Intent(MainActivity.this,WebActivity.class);
                intent.putExtra("Key",content);
                startActivity(intent);
            }
        }
    }

}
