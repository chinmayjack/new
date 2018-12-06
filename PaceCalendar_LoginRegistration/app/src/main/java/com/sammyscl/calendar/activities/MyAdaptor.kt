package com.sammyscl.calendar.activities

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.sammyscl.R
import java.util.HashMap


class MyAdapter internal constructor(private val ctx: Context, private val header_titles: List<String>, private val child_titles: HashMap<String, List<String>>) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return header_titles.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return child_titles[header_titles[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return header_titles[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return child_titles[header_titles[groupPosition]]!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val title = this.getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.activity_main_cal, null)
        }
        val textView = convertView!!.findViewById<View>(R.id.heading_item) as TextView
        textView.setTypeface(null, Typeface.BOLD)
        textView.text = title

        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val title = this.getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val layoutInflater = this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.team_childview, null)
        }

        val textView = convertView!!.findViewById<View>(R.id.child_item) as TextView
        textView.text = title
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
