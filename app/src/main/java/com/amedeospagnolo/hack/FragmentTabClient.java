package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentTabClient extends Fragment {
    public ArrayList<DataClient> myFakeDataset = new ArrayList<DataClient>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_client, container, false);

        // add fake data
        ClientAdapterList adapter = new ClientAdapterList(this.getContext(), myFakeDataset);

        adapter.add(new DataClient("Francene_mac", "10.20.30.40", "12.35", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon01)));
        adapter.add(new DataClient("Ines_iphone", "19.30.42.55", "4:21", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon02)));
        adapter.add(new DataClient("THIS", "55.55.42.55", "14:51", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon03)));
        adapter.add(new DataClient("OLD", "49.30.42.55", "7:48", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon04)));
        adapter.add(new DataClient("that", "889.30.42.55", "4:02", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon05)));
        adapter.add(new DataClient("Francene_mac", "10.20.30.40", "12.35", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon01)));
        adapter.add(new DataClient("Ines_iphone", "19.30.42.55", "4:21", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon02)));
        adapter.add(new DataClient("THIS", "55.55.42.55", "14:51", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon03)));
        adapter.add(new DataClient("OLD", "49.30.42.55", "7:48", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon04)));
        adapter.add(new DataClient("that", "889.30.42.55", "4:02", ContextCompat.getDrawable(this.getContext(), R.drawable.client_icon05)));

        ListView listView = rootView.findViewById(R.id.list_clients);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
                String clName = myFakeDataset.get(position).name;
                String clIp = myFakeDataset.get(position).ip;
                Context context = getContext();
                Intent intent = new Intent(context, ClientActivity.class);
                intent.putExtra("client_name", clName);
                intent.putExtra("client_ip", clIp);
                if (context != null) context.startActivity(intent);
                // overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            }
        });

        // hide text if it's not empty'
        TextView noClientsText = rootView.findViewById(R.id.no_clients);
        if (!myFakeDataset.isEmpty()) noClientsText.setVisibility(View.GONE);
        return rootView;
    }

    public class ClientAdapterList extends ArrayAdapter<DataClient> {
        public ClientAdapterList(Context context, ArrayList<DataClient> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataClient my_items = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.carditem_client, parent, false);
            }

            TextView clName = convertView.findViewById(R.id.client_name);
            clName.setText(my_items.name);

            TextView clIp = convertView.findViewById(R.id.client_ip);
            clIp.setText(my_items.ip);

            TextView clTime = convertView.findViewById(R.id.client_time);
            clTime.setText(my_items.time);

            ImageView clImage = convertView.findViewById(R.id.client_image);
            clImage.setImageDrawable(my_items.avatar);

            return convertView;
        }
    }

}