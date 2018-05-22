package com.gjjx.carvideo;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gjjx.carvideo.databinding.ActivityMainBinding;
import com.gjjx.carvideo.db.TBCourse;
import com.gjjx.carvideo.db.TBCourseDao;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    private Context context;

    private ListFragView view;
    List<ListFragment> fragments;
    private TBCourseDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this,R.layout.activity_main);
        context = MainActivity.this;
        ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        } else {
            KLog.e("toolbar is null");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "本应用视频归北京公交驾校所有未经许可请勿作他用！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        binding.navView.setNavigationItemSelectedListener(this);
//        navigationView.setNavigationItemSelectedListener(this);
        initialData();
    }

    private void initialData() {
        dao = App.getDaoMaster(context).newSession().getTBCourseDao();
        fragments = new ArrayList<>();
        Resources res = getResources();
        String[] subject_titles = res.getStringArray(R.array.subject_titles);
        List<String> titles_list = Arrays.asList(subject_titles);
        for (String title : titles_list) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
            fragments.add(new ListFragment());
        }

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles_list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                List<TBCourse> courses_list = dao.queryBuilder().where(TBCourseDao.Properties.Sub_id.eq(position + 1)).list();
                fragments.get(position).loadData(courses_list);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(viewPager, "本应用视频归北京公交驾校所有未经许可请勿作他用！", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_gallery) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_slideshow) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_manage) {
            viewPager.setCurrentItem(3);
        } else if (id == R.id.nav_share) {
            App.IS_INSIDE_PLAYER = false;
            Snackbar.make(viewPager, "切换外部播放器成功！", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.nav_send) {
            Snackbar.make(viewPager, "切换内部播放器成功！", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            App.IS_INSIDE_PLAYER = true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
