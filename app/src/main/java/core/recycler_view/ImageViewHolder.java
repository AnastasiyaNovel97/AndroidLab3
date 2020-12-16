package core.recycler_view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import core.async.ImageLoader;
import core.async.TaskRunner;
import core.async.ImageLoader;
import core.shared.Traceable;
import core.activities.R;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static android.app.PendingIntent.getActivity;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Accessors(fluent = true, prefix = "_")
public class ImageViewHolder extends RecyclerView.ViewHolder implements Traceable {
    TextView _textView;
    ImageView _imageView;
    ProgressBar _progressBar;
    Activity _activity;

    public ImageViewHolder(@NonNull View itemView, @NonNull final Activity activity) {
        super(itemView);
        _textView = itemView.findViewById(R.id.textView);
        _imageView = itemView.findViewById(R.id.imageView2);
        _progressBar = itemView.findViewById(R.id.progressBar);
        _activity = activity;
            }

    public void bind(ImageRecyclerItem item){
        _textView.setText(item.getCaption());
        _progressBar.setVisibility(View.INVISIBLE);
        _imageView.setVisibility(View.VISIBLE);
      //  _imageView.setImageResource( R.drawable.loading);
        Glide.with(_activity).load(R.drawable.progressbar_cat).into(_imageView);
        //_imageView.setVisibility(View.GONE);
        _imageView.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
        _imageView.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new TaskRunner().executeAsync(new ImageLoader(item.getUrl()),
                        (image) -> _activity.runOnUiThread(() -> onImageLoaded(image)));
            }
        }, 1000);
//        new TaskRunner().executeAsync(new ImageLoader(item.getUrl()),
//                (image) -> _activity.runOnUiThread(() -> onImageLoaded(image)));
    }

    private void onImageLoaded(Bitmap image) {
        Glide.with(_activity).load(image).into(_imageView);
        //_imageView.setImageBitmap(image);
        _progressBar.setVisibility(View.GONE);
        //_imageView.setVisibility(View.VISIBLE);
    }

}

