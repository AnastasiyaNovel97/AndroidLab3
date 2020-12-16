package core.recycler_view;

        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import androidx.annotation.NonNull;
        //import androidx.fragment.app.FragmentActivity;
        import androidx.recyclerview.widget.RecyclerView;
        import core.activities.R;
        import lombok.AccessLevel;
        import lombok.AllArgsConstructor;
        import lombok.experimental.FieldDefaults;

        import java.util.Collection;
        import java.util.List;
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    List<ImageRecyclerItem> _dataset;
    Activity _activity;

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ImageViewHolder(view, _activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int i) {
        //holder.view().setText(_dataset.get(i));
        holder.bind(_dataset.get(i));
    }

    @Override
    public int getItemCount() {
        return _dataset.size();
    }

    public void setItems(Collection<ImageRecyclerItem> items){
        _dataset.addAll(items);
        notifyDataSetChanged();
    }

    public void clearItems(){
        _dataset.clear();
        notifyDataSetChanged();
    }
}
