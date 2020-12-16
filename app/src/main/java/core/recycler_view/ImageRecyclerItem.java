package core.recycler_view;

import java.net.URL;

import lombok.SneakyThrows;

public class ImageRecyclerItem {

    private String caption;
    private URL url;

    @SneakyThrows
    public ImageRecyclerItem(String caption, String url){
        this.caption = caption;
        this.url = new URL(url);
    }

    public String getCaption() {
        return caption;
    }

    public URL getUrl() {
        return url;
    }
}
