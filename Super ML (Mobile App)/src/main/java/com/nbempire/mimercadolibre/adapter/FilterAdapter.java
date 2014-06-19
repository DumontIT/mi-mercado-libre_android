package com.nbempire.mimercadolibre.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nbempire.mimercadolibre.R;
import com.nbempire.mimercadolibre.domain.AvailableFilter;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 25/05/14, at 23:29.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class FilterAdapter extends ArrayAdapter<AvailableFilter> {

    private final LayoutInflater layoutInflater;

    public FilterAdapter(Context context) {
        super(context, R.layout.my_list_item_1);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Populate new items in the list.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.my_list_item_1, parent, false);
        } else {
            view = convertView;
        }

        AvailableFilter availableFilter = getItem(position);
        ((TextView) view.findViewById(R.id.label)).setText(availableFilter.getName());

        if (availableFilter.getResults() != null) {
            String label = "(%s)";
            ((TextView) view.findViewById(R.id.secondLabel)).setText(String.format(label, availableFilter.getResults()));
        }

        return view;
    }
}