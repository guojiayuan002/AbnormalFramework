package com.jiayuan.mainframework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiayuan.mainframework.R;
import com.jiayuan.mainframework.utils.ViewFindUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 散标适配器
 * Created by pengwl on 2017/2/23 0007.
 */
public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.ViewHolder> implements View.OnClickListener {

    private final Context mContext;
    private List<String> mProductEntryList;

    public MyRVAdapter(Context context, List<String> mProductEntryList) {
        if (mProductEntryList == null) {
            mProductEntryList = new ArrayList<String>();
        }
        this.mContext = context;
        this.mProductEntryList = mProductEntryList;

    }

    //刷新
    public void change(List<String> mProductEntryList) {

    }

    //加载更多
    public void add(List<String> mProductEntryList) {
        this.mProductEntryList.addAll(mProductEntryList);
        notifyDataSetChanged();
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_view, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        //TODO 1.将创建的View注册点击事件,获取不到position
        //2.在最外层布局设置setOnClickListener
        // view.setOnClickListener(this);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder stringHolder = (ViewHolder) holder;
        //将数据保存在itemView的Tag中，以便点击时进行获取
        // holder.itemView.setTag(mProductEntryList.get(position));
//        stringHolder.mItemView.setOnLongClickListener();
        //TODO 还可以设置，具某一个不见被点击
        stringHolder.itemView.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(v, position,true);
        });
        stringHolder.mRun.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(v, position,false);
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mProductEntryList.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mItemView;
        private TextView mRun;

        ViewHolder(View view) {
            super(view);
            mItemView = ViewFindUtils.find(view, R.id.item_view);
            mRun = ViewFindUtils.find(view, R.id.tv_run);
        }
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v, 0,true);
//        mOnItemClickListener.onItemClick(v, (String) v.getTag());
    }


    // =======start 设置RecyclerView ItemClick 事件
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position,boolean isView);
//        2.
//         mOnItemClickListener.onItemClick(v, (ProductEntry) v.getTag());
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
