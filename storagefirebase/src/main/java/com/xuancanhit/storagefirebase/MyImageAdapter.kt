//package com.xuancanhit.storagefirebase
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import android.widget.ImageView
//import android.widget.TextView
//
//class MyImageAdapter : BaseAdapter() {
//
//    val myLayout: Int = 0
//    var arrayImage: List<MyImage> = emptyList()
//
//
//    override fun getCount(): Int = arrayImage.size
//
//    override fun getItem(p0: Int): Any {
//        return arrayImage[p0]
//    }
//
//    override fun getItemId(p0: Int): Long {
//        return p0.toLong()
//    }
//
//
//    class ViewHolder(val ivImg: ImageView? = null, val tvName: TextView? = null)
//
//    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View {
//        val context: Context = p2.context
//        val inflater:LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        var rowView = p1
//        val holder = ViewHolder()
//        if (rowView == null) {
//            rowView = inflater.inflate(myLayout, null)
//            holder.tvName = rowView.findViewByI as TextView
//        }
//
//
//        val view = p1 ?: LayoutInflater.from(p2.context).inflate(R.layout.list_item, p2, false)
//        val heroText: TextView = view.findViewById(R.id.textHeroView)
//        val hero = heroes[position]
//        heroText.text = hero.keys.first()
//        return view
//    }
//}