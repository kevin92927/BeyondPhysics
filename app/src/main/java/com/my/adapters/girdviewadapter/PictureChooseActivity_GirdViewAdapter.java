package com.my.adapters.girdviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.beyondphysics.ui.BaseActivity;
import com.beyondphysics.ui.utils.NetworkImageViewHelp;
import com.beyondphysics.ui.views.NetworkImageView;
import com.my.beyondphysicsapplication.PictureChooseActivity;
import com.my.beyondphysicsapplication.R;

import java.util.ArrayList;
import java.util.List;


public class PictureChooseActivity_GirdViewAdapter extends BaseAdapter {
    private BaseActivity baseActivity;
    private List<String> paths;
    private LayoutInflater layoutInflater;
    private OnFeedBackListener onFeedBackListener;


    public PictureChooseActivity_GirdViewAdapter(BaseActivity baseActivity, List<String> paths, OnFeedBackListener onFeedBackListener) {
        this.baseActivity = baseActivity;
        if (paths == null) {
            paths = new ArrayList<String>();
        }
        this.paths = paths;
        layoutInflater = this.baseActivity.getLayoutInflater();
        this.onFeedBackListener = onFeedBackListener;

    }

    public void addPaths(List<String> paths) {
        if (paths != null) {
            this.paths.addAll(paths);
        }
    }

    @Override
    public int getCount() {
        if (paths.size() >= PictureChooseActivity.CHOOSECOUNT) {
            return PictureChooseActivity.CHOOSECOUNT;
        } else {
            return paths.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        return paths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.activity_picture_choose_item, parent, false);
            viewHolder.networkImageView = (NetworkImageView) convertView.findViewById(R.id.networkImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == paths.size()) {
            viewHolder.networkImageView.cancelRequest();
            viewHolder.networkImageView.setImageResource(R.mipmap.activity_picture_choose_add);
            viewHolder.networkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onFeedBackListener != null) {
                        onFeedBackListener.addClick();
                    }
                }
            });
        } else {
            final String path = paths.get(position);
            NetworkImageViewHelp.getImageFromDiskCache(viewHolder.networkImageView, path, 1, baseActivity.activityKey, 0, 0);
            viewHolder.networkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onFeedBackListener != null) {
                        onFeedBackListener.chooseClick(path);
                    }
                }
            });
        }

        return convertView;
    }

    public class ViewHolder {
        private NetworkImageView networkImageView;
    }

    public interface OnFeedBackListener {

        void addClick();

        void chooseClick(String path);
    }

}
