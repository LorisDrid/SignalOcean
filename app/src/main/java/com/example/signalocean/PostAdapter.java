package com.example.signalocean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PostAdapter<T extends AbstractPost> extends ArrayAdapter<T> {

    private Class<T> itemType;

    public PostAdapter(Context context, List<T> posts, Class<T> itemType) {
        super(context, 0, posts);
        this.itemType = itemType;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        T post = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_post, parent, false);
        }

        TextView postTextView = convertView.findViewById(R.id.postTextView);

        postTextView.setText(post.toString());

        return convertView;
    }
    public T getTypedItem(int position) {
        return itemType.cast(getItem(position));
    }
}
