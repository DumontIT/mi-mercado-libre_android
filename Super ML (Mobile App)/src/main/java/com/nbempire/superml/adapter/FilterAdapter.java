package com.nbempire.superml.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nbempire.superml.R;
import com.nbempire.superml.domain.Filter;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 25/05/14, at 23:29.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class FilterAdapter extends ArrayAdapter<Filter> {

    private final LayoutInflater layoutInflater;

    public FilterAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Populate new items in the list.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.abc_activity_chooser_view_list_item, parent, false);
        } else {
            view = convertView;
        }

        Filter category = getItem(position);
        ((TextView) view.findViewById(R.id.title)).setText(category.getName());

        return view;
    }
}