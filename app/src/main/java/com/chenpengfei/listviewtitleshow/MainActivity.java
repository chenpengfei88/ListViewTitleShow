package com.chenpengfei.listviewtitleshow;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import java.util.ArrayList;

/**
 * author  chenpengfei
 *
 */
public class MainActivity extends Activity {

    View cv = null;
    int h;
    ViewGroup.LayoutParams lf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuListView mlist = (MenuListView)findViewById(R.id.refreshable_grid_view_id);
        MenuAdapter expandableListAdapter = new MenuAdapter(getLayoutInflater(), initList());
        mlist.listview.setAdapter(expandableListAdapter);
        for(int i = 0; i < expandableListAdapter.getGroupCount(); i++){
            mlist.listview.expandGroup(i);
        }
        mlist.listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                System.out.println("==============================ca");
                cv = v;
                lf = cv.getLayoutParams();
                h = cv.getHeight();
                new RefreshingTask().execute();
                return false;
            }
        });
    }

    /**
     * 初始化数据
     * @return
     */
    private ArrayList<Parent> initList(){
        ArrayList<Parent> parentArrayList = new ArrayList<Parent>();
        Parent parentOne = new Parent();
        parentOne.setName("米饭");
        ArrayList<Child> childArrayList = parentOne.getChildArrayList();
        childArrayList.add(new Child("酸辣白菜盖浇饭"));
        childArrayList.add(new Child("土豆丝盖浇饭"));
        childArrayList.add(new Child("蘑菇盖浇饭"));
        childArrayList.add(new Child("炒肉盖浇饭"));
        childArrayList.add(new Child("木耳盖浇饭"));
        childArrayList.add(new Child("韭菜炒鸡蛋盖浇饭"));
        childArrayList.add(new Child("腐竹盖浇饭"));
        parentArrayList.add(parentOne);

        Parent parentTwo= new Parent();
        parentTwo.setName("面");
        ArrayList<Child> childArrayListMain = parentTwo.getChildArrayList();
        childArrayListMain.add(new Child("牛肉拉面"));
        childArrayListMain.add(new Child("锅盖面"));
        childArrayListMain.add(new Child("刀削面"));
        childArrayListMain.add(new Child("手擀面"));
        childArrayListMain.add(new Child("打卤面"));
        parentArrayList.add(parentTwo);

        Parent parentThree = new Parent();
        parentThree.setName("早餐");
        ArrayList<Child> childArrayListThree = parentThree.getChildArrayList();
        childArrayListThree.add(new Child("粉丝包"));
        childArrayListThree.add(new Child("鸡蛋"));
        childArrayListThree.add(new Child("蛋糕"));
        childArrayListThree.add(new Child("牛奶"));
        childArrayListThree.add(new Child("稀饭"));
        childArrayListThree.add(new Child("山东煎饼"));
        parentArrayList.add(parentThree);

        Parent parentFour = new Parent();
        parentFour.setName("夜宵");
        ArrayList<Child> childArrayListFour = parentFour.getChildArrayList();
        childArrayListFour.add(new Child("小龙虾"));
        childArrayListFour.add(new Child("烧烤"));
        childArrayListFour.add(new Child("啤酒"));
        childArrayListFour.add(new Child("自助餐"));
        childArrayListFour.add(new Child("海鲜"));
        childArrayListFour.add(new Child("夜市麻辣烫"));
        childArrayListFour.add(new Child("羊肉串"));
        parentArrayList.add(parentFour);
        return parentArrayList;
    };

    class RefreshingTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            while (true) {
                System.out.println("==============================hahah");
                h = h -30;
                if (h <= 0) {
                    h = 1;
                    break;
                }
                publishProgress(h);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("==============================tttt");
            publishProgress(1);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            lf.height = h;
            cv.setLayoutParams(lf);
        }
    }

}
