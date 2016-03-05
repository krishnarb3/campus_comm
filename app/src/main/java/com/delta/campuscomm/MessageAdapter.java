package com.delta.campuscomm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class MessageAdapter extends ArrayAdapter<String> {
    private Context cont;
    ArrayList<String> tags;
    Random random = new Random();

    public MessageAdapter(Context context, ArrayList<String> resource) {
        super(context, R.layout.layout_message_adapter, resource);
        cont = context;
        this.tags = tags;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customview = layoutInflater.inflate(R.layout.layout_message_adapter, parent, false);

        String item = getItem(position);
        Calendar calendar = Calendar.getInstance();

        //Generate the post for the list view
        TextView textViewUsername = (TextView) customview.findViewById(R.id.textViewUsernameMessageAdapter);
        TextView textViewMessage = (TextView) customview.findViewById(R.id.textViewMessageMessageAdapter);
        TextView textViewTime = (TextView) customview.findViewById(R.id.textViewTimeMessageAdapter);
        ImageView imageViewStrip = (ImageView) customview.findViewById(R.id.imageViewStripMessageAdapter);

        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        imageViewStrip.setBackgroundColor(color);
        try {
            JSONObject jsonItem = new JSONObject(item);
            textViewUsername.setText(jsonItem.getString("Sender") + " posted");
            textViewMessage.setText(jsonItem.getString("Message"));

            //Processing timestamp
            String[] timestamp = jsonItem.getString("created_at").split(" ");
            String[] date = timestamp[0].split("-");
            String[] time = timestamp[1].split(":");
            if (calendar.get(Calendar.YEAR) != Integer.parseInt(date[0])) {
                int t = calendar.get(Calendar.YEAR) - Integer.parseInt(date[0]);
                textViewTime.setText(t + " years ago");
            } else if (1 + calendar.get(Calendar.MONTH) != Integer.parseInt(date[1])) {
                int t = 1 + calendar.get(Calendar.MONTH) - Integer.parseInt(date[1]);
                textViewTime.setText(t + " months ago");
            } else if (calendar.get(Calendar.DAY_OF_MONTH) != Integer.parseInt(date[2])) {
                int t = calendar.get(Calendar.DAY_OF_MONTH) - Integer.parseInt(date[2]);
                if (t == 1) textViewTime.setText("yesterday");
                else textViewTime.setText(t + " days ago");
            } else {
                textViewTime.setText("today at " + time[0] + ":" + time[1]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return customview;
    }

}