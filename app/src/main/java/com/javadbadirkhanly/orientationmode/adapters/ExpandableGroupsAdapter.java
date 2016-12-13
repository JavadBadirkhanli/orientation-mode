package com.javadbadirkhanly.orientationmode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.javadbadirkhanly.orientationmode.R;
import com.javadbadirkhanly.orientationmode.activities.MainActivity;
import com.javadbadirkhanly.orientationmode.models.Group;
import com.javadbadirkhanly.orientationmode.models.GroupItem;

import java.util.List;

/**
 * Created by javadbadirkhanly on 12/13/16.
 */

public class ExpandableGroupsAdapter extends ExpandableRecyclerAdapter<ExpandableGroupsAdapter.ParentViewHolder, ExpandableGroupsAdapter.ChildViewHolder> {

    private final String TAG = getClass().getSimpleName();

    private MainActivity activity;

    public ExpandableGroupsAdapter(Context context, List<Group> groupList) {
        super(groupList);
        if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        }
    }

    @Override
    public ParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.adapter_row_group, parentViewGroup, false);
        return new ParentViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = LayoutInflater.from(childViewGroup.getContext()).inflate(R.layout.adapter_row_item, childViewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(ParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        final Group group = (Group) parentListItem;
        parentViewHolder.tvGroupName.setText(group.getName() + " " + group.getId());
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int position, Object childListItem) {
        final GroupItem groupItem = (GroupItem) childListItem;
        childViewHolder.tvItemName.setText(groupItem.getName() + " " + groupItem.getId());
    }

    class ParentViewHolder extends com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder {

        private TextView tvGroupName;

        ParentViewHolder(View itemView) {
            super(itemView);
            tvGroupName = (TextView) itemView.findViewById(R.id.tvGroupName);
        }
    }

    class ChildViewHolder extends com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder {

        private TextView tvItemName;

        ChildViewHolder(View itemView) {
            super(itemView);
            tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
        }
    }
}
