package com.gjjx.carvideo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gjjx.carvideo.db.TBClass;
import com.gjjx.carvideo.db.TBClassDao;
import com.socks.library.KLog;
import com.ylbf.appbase.base.BaseRecyclerAdapter;
import com.ylbf.appbase.base.BaseRecyclerViewHolder;
import com.ylbf.appbase.base.callback.OnItemClickAdapter;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ylbf_ on 2016/6/29.
 */
public class ClassActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private BaseRecyclerAdapter<TBClass> adapter;
    private List<TBClass> tbClassList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        tbClassList = App.getDaoMaster(this)
                .newSession().getTBClassDao().queryBuilder().where(TBClassDao.Properties.C_id.eq(id)).list();
        if (tbClassList.size() != 0) {
            toolbar.setTitle(tbClassList.get(0).getTBCourse().getName() + tbClassList.get(0).getTBCourse().getSummary());
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseRecyclerAdapter<TBClass>(this, tbClassList) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_class;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, TBClass item) {
                holder.getTextView(R.id.text_course).setText(item.getName() + ":" + item.getSummary());
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickAdapter() {
            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
                TBClass item = tbClassList.get(position);
                String url = App.BASE_URL + item.getTBCourse().getUrl() + File.separator + item.getFilename();
                String title = item.getName() + ":" + item.getSummary();
                KLog.d(title + "\t" + url);
                if (App.IS_INSIDE_PLAYER) {
                    JCFullScreenActivity.startActivity(ClassActivity.this, url, title);
                } else {
                    Uri uri = Uri.parse(url);
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(it);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                super.onItemLongClick(view, position);
            }
        });

//        for (TBClass tbClass : tbClassList) {
//            TBCourse tbCourse = tbClass.getTBCourse();
//            KLog.d(tbCourse.getTBSubject().getSummary() + ":" + tbCourse.getTBSubject().getName());
//            KLog.d(tbCourse.getName() + ":" + tbCourse.getSummary());
//            KLog.d(tbClass.getName() + ":" + tbClass.getSummary());
//            KLog.d(App.BASE_URL + tbClass.getTBCourse().getUrl() + File.separator + tbClass.getFilename());
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
