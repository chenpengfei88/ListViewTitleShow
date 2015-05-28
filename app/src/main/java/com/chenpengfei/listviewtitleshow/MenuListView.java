package com.chenpengfei.listviewtitleshow;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenpengfei.listviewtitleshow.R;


/**
 * Created by Chenpengfei on 2015/3/27.
 */
public class MenuListView extends RelativeLayout{

    private View headTextView = null;
    public ExpandableListView listview;
    //偏移量
    private int offset;
    private int mWidthMode;

    public MenuListView(Context context) {
        super(context);
    }

    public MenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.content, null);
        listview= (ExpandableListView) view.findViewById(R.id.listview_id);
        listview.setGroupIndicator(null);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                offset = 0;
                int headWidth = 0;
                View titleView = null;
                if(headTextView!=null){
                    headWidth = headTextView.getMeasuredHeight();
                }
                for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
                     titleView = listview.getChildAt(i - firstVisibleItem);
                    //如果是父类，并且距离top小于headTextView的宽高，代表这个父类和head展示的view对接上了
                    if(((String)titleView.getTag()).equals("parent") && titleView.getTop() <=headWidth){
                        if(titleView.getTop() <= 0){
                            if(headTextView == null)
                                 headTextView = LayoutInflater.from(getContext()).inflate(R.layout.title, null);
                            ((TextView)headTextView.findViewById(R.id.tid)).setText(((TextView)titleView.findViewById(R.id.tid)).getText().toString());
                            //设置headtextview的布局
                            ensurePinnedHeaderLayout(headTextView);
                            offset = 0;
                        } else
                            offset = titleView.getTop() - headWidth;
                        break;
                    } else {
                        if(!((String)titleView.getTag()).equals("parent")){
                            ((TextView)headTextView.findViewById(R.id.tid)).setText(((String)titleView.getTag()).split("=")[1]);
                        } else {
                            break;
                        }
                    }
                }
                invalidate();
            }
        });
        this.addView(view);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidthMode = MeasureSpec.getMode(widthMeasureSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(headTextView == null)
            return;
        canvas.save();
        canvas.translate(0, offset);
        canvas.clipRect(0, 0, headTextView.getMeasuredWidth(), headTextView.getMeasuredHeight()); // needed
        headTextView.draw(canvas);
        canvas.restore();
    }

    private void ensurePinnedHeaderLayout(View header) {
        if (header.isLayoutRequested()) {
            int widthSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), mWidthMode);
            int heightSpec;
            ViewGroup.LayoutParams layoutParams = header.getLayoutParams();
            if (layoutParams != null && layoutParams.height > 0) {
                heightSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY);
            } else {
                heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            }
            header.measure(widthSpec, heightSpec);
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        }
    }
}
