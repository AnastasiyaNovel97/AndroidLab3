package core.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import core.shared.Traceable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Accessors(fluent = true, prefix = "_")
public class ImageLoader implements Callable<Bitmap>, Traceable {
    URL imageUrl;

    @Override
    public Bitmap call() throws Exception {
        URLConnection conn = imageUrl.openConnection();

        return BitmapFactory.decodeStream(conn.getInputStream());
    }
}
