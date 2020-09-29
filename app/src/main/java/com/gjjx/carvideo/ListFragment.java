package com.gjjx.carvideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjjx.carvideo.db.TBCourse;
import com.gjjx.carvideo.db.TBCourseDao;
import com.ylbf.appbase.base.BaseRecyclerAdapter;
import com.ylbf.appbase.base.BaseRecyclerViewHolder;
import com.ylbf.appbase.base.callback.OnItemClickAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ylbf_ on 2016/6/29.
 */
public class ListFragment extends Fragment implements ListFragView {
    RecyclerView recyclerView;
    private BaseRecyclerAdapter<TBCourse> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        return recyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        List<TBCourse> courses_list = App.getDaoMaster(recyclerView.getContext())
                .newSession().getTBCourseDao()
                .queryBuilder().where(TBCourseDao.Properties.Sub_id.eq(1)).list();
        loadData(courses_list);
    }

    @Override
    public void loadData(final List<TBCourse> courses_list) {
//        for (TBCourse tbCourse : courses_list) {
//            KLog.d(tbCourse.getTBSubject().getSummary() + ":" + tbCourse.getTBSubject().getName());
//            KLog.d(tbCourse.getName() + ":" + tbCourse.getSummary());
//            KLog.d(tbCourse.getId());
//        }
        if (getContext() == null) {
            return;
        }
        adapter = new BaseRecyclerAdapter<TBCourse>(getContext(), courses_list) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_course;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, TBCourse item) {
//                holder.getTextView(R.id.text_subject).setText(item.getTBSubject().getSummary() + ":" + item.getTBSubject().getName());
                holder.getTextView(R.id.text_course).setText(item.getName() + ":" + item.getSummary());
            }
        };
        adapter.setOnItemClickListener(new OnItemClickAdapter() {
            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
                Intent intent = new Intent(getContext(), ClassActivity.class);
                intent.putExtra("id", courses_list.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                super.onItemLongClick(view, position);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
