package vn.com.it.truongpham.applove;

public class DSTruyen {
    public String title,src,href;

    public DSTruyen(String title, String src, String href) {
        this.title = title;
        this.src = src;
        this.href = href;
    }

    public DSTruyen(String title, String href) {
        this.title = title;
        this.href = href;
    }
}
