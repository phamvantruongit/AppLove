package vn.com.it.truongpham.applove;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterDSChuong extends PagerAdapter {
    private Context context;
    private List<String> dataObjectList;
    private LayoutInflater layoutInflater;
    public AdapterDSChuong(Context context, List<String> dataObjectList){
        this.context = context;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.dataObjectList = dataObjectList;
    }

    @Override
    public int getCount() {
        return dataObjectList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.item_ds_chuong, container, false);
        WebView webView=view.findViewById(R.id.webview);
        String data= "<body style=\"text-align: justify;text-indent: 5px ;font-size:20px\">"+dataObjectList.get(position) +"</body";
        webView.loadData(data, "text/html", "UTF-8");
        container.addView(webView);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
