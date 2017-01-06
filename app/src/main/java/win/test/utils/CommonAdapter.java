package win.test.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * 万能适配器
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int layoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HashMap<Integer,View> lmap = new HashMap<Integer,View>();
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(layoutId, null);
//        }
//        T t = getItem(position);
//        convertView(convertView, t,position);
        if(lmap.get(position)==null){             //...2、
            convertView = LayoutInflater.from(context).inflate(layoutId, null);

            lmap.put(position, convertView);      //...3、使用lmap.put(position, convertView)把他们一一对应

        }else{
            convertView = lmap.get(position);     //...4、使用 conVertView=lmap.get(position)来取，不会重复

        }

        T t = getItem(position);
        convertView(convertView, t,position);

        return convertView;
    }

    /**
     * 需要去实现的对item中的view的设置操作
     * @param item
     * @param t
     */
    protected abstract void convertView(View item, T t, int position);

}