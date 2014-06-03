package com.nbempire.superml.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.nbempire.superml.R;
import com.nbempire.superml.domain.AvailableFilter;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 31/05/14, at 16:55.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class FilterExpandableListAdapter extends BaseExpandableListAdapter {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "FilterExpandableListAdapter";

    private List<String> groups;

    private List<List<AvailableFilter>> children;

    private LayoutInflater layoutInflater;

    public FilterExpandableListAdapter(Context context, List<String> groups, List<List<AvailableFilter>> children) {
        this.groups = groups;
        this.children = children;
        layoutInflater = LayoutInflater.from(context);

        Log.i(TAG, "Created adapter with filters: " + groups.size());
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return (groupPosition * 1024);//    To be consistent with getChildId
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return (groupPosition * 1024 + childPosition);//    Max 1024 children per group?
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Log.i(TAG, "Using custom layout for groups because Android version code is: " + Build.VERSION.SDK_INT);
            view = layoutInflater.inflate(R.layout.list_view_group, parent, false);
        } else {
            view = layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        }

        String filterName = (String) getGroup(groupPosition);
        TextView filterLabel = (TextView) view.findViewById(android.R.id.text1);
        if (filterName != null) {
            filterLabel.setText(filterName);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View itemLayout = layoutInflater.inflate(R.layout.list_item_checkbox, parent, false);

        final AvailableFilter availableFilter = (AvailableFilter) getChild(groupPosition, childPosition);

        CheckBox checkBox = (CheckBox) itemLayout.findViewById(R.id.list_item_checkbox);
        checkBox.setId((int) getChildId(groupPosition, childPosition));
        checkBox.setText(availableFilter.getName());
        checkBox.setChecked(availableFilter.isChecked());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                availableFilter.setChecked(isChecked);
            }
        });

        if (availableFilter.getResults() != null) {
            String label = "(%s)";
            ((TextView) itemLayout.findViewById(R.id.list_item_second_label)).setText(String.format(label, availableFilter.getResults()));
        }

        return itemLayout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
