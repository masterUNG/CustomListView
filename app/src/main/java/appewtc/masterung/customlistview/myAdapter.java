package appewtc.masterung.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by masterUNG on 2/19/15 AD.
 */
public class myAdapter extends BaseAdapter{

    //Explicit
    private Context mContext;
    private String[] strNameFood;
    private int[] intMyTarget;


    public myAdapter(Context context, String[] strName, int[] targetID) {

        this.mContext = context;
        this.strNameFood = strName;
        this.intMyTarget = targetID;

    }   // Constructor

    @Override
    public int getCount() {
        return strNameFood.length;
    }   // getCount

    @Override
    public Object getItem(int position) {
        return null;
    }   // getItem

    @Override
    public long getItemId(int position) {
        return 0;
    }   // getItemId

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = mInflater.inflate(R.layout.list_view_row, parent, false);

        //TextView Show ListFood
        TextView objTextView = (TextView) view.findViewById(R.id.textView);
        objTextView.setText(strNameFood[position]);

        //ImageView Show Food
        ImageView objImageView = (ImageView) view.findViewById(R.id.imvIconList);
        objImageView.setBackgroundResource(intMyTarget[position]);

        return view;
    }   // getView

}   // Main Class
