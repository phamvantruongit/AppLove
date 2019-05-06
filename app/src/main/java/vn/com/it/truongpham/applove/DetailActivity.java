package vn.com.it.truongpham.applove;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ViewPager viewPager;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String href = getIntent().getStringExtra("href");
        viewPager = findViewById(R.id.rvData);


        DowloadData dowloadData = new DowloadData();
        dowloadData.execute(href);






    }

    private class DowloadData extends AsyncTask<String, Void, List<DSTruyen>> {

        @Override
        protected List<DSTruyen> doInBackground(String... url) {
            List<DSTruyen> list = new ArrayList<>();
            try {
                org.jsoup.nodes.Document document = Jsoup.connect(url[0]).get();
                Elements elements = document.select("div[class=ebook_row]");

                Elements li = elements.select("li");

                for(int i=0 ;i<li.size();i++) {

                    Elements a = li.get(i).select("a");
                    String href = a.attr("href");
                    String title = a.text();

                    DSTruyen dsTruyen = new DSTruyen(title, href);
                    list.add(dsTruyen);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(final List<DSTruyen> list) {
            super.onPostExecute(list);
            final DowloadDetail dowloadDetail=new DowloadDetail();
            dowloadDetail.execute(list.get(0).href);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

        }
    }

    public class DowloadDetail extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... url) {
            org.jsoup.nodes.Document document = null;
            List<String>  data = new ArrayList<>();
            try {
                document = Jsoup.connect(url[1]).get();
                Elements elements = document.select("div[class=chapter-c]");
                data.add(elements.html());
            } catch (IOException e) {
                e.printStackTrace();
            }


            return data;
        }

        @Override
        protected void onPostExecute(List<String> data) {
            super.onPostExecute(data);
            Log.d("Data",data.get(0));
            AdapterDSChuong adapterDSChuong=new AdapterDSChuong(DetailActivity.this,data);
            viewPager.setAdapter(adapterDSChuong);

        }
    }
}
