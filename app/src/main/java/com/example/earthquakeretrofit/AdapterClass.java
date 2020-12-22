package com.example.earthquakeretrofit;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    Context ctxt;
    List<Properties> listData;
    int resource;

    public AdapterClass(Context ctxt, List<Properties> listData, int resource) {
        this.ctxt = ctxt;
        this.listData = listData;
        this.resource = resource;
    }

    @NonNull
    @Override
    public AdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctxt).inflate(resource,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {
        final Properties list = listData.get(position);

        Log.i("firstList",list.toString());
        GradientDrawable magnitudeCircle = (GradientDrawable) holder.magnitude.getBackground();
        //Log.i("magnitude Value",list.getProperties().getMagnitude().toString());
        magnitudeCircle.setColor(getMagnitudeColor(list.getProperties().getMag()));

        holder.magnitude.setText((list.getProperties().getMag()).toString());

        String locationData = list.getProperties().getPlace();
        holder.locationOffSet.setText(locationOffsetfn(locationData));
        holder.primaryLocation.setText(primaryLocationfn(locationData));

        Long dateTime = list.getProperties().getTime();
        Date dateObject = new Date(dateTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL DD, YYYY");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String dateToDisplay = dateFormat.format(dateObject);
        String timeToDisplay = timeFormat.format(dateObject);

        holder.date.setText(dateToDisplay);
        holder.time.setText(timeToDisplay);

    }

    private int getMagnitudeColor(Double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(ctxt, magnitudeColorResourceId);
    }
    private static String locationOffsetfn(String place) {
        int position_Of = place.indexOf("of");
        if(position_Of==-1){
            return "Near the";
        }
        return place.substring(0,position_Of+2);
    }

    private static String primaryLocationfn(String place) {
        int position_Of = place.indexOf("of");
        return place.substring(position_Of+2);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView magnitude;
        public TextView locationOffSet;
        public TextView primaryLocation;
        public TextView date;
        public TextView time;
        public RelativeLayout dataLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.magnitude = (TextView) itemView.findViewById(R.id.magnitude);
            this.locationOffSet = (TextView) itemView.findViewById(R.id.locationOffset);
            this.primaryLocation = (TextView) itemView.findViewById(R.id.primaryLocation);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.time = (TextView) itemView.findViewById(R.id.time);
            this.dataLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
