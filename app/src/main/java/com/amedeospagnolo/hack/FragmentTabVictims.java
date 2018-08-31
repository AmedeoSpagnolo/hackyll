package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentTabVictims extends Fragment {
    public ArrayList<DataVictim> myFakeDataset = new ArrayList<DataVictim>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_victims, container, false);

        // add fake data
        VictimAdapterList adapter = new VictimAdapterList(this.getContext(), myFakeDataset);
        adapter.add(new DataVictim("Francene_mac", "10.20.30.40"));
        adapter.add(new DataVictim("Ines_iphone", "19.30.42.55"));
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
        return rootView;
    }

    public class VictimAdapterList extends ArrayAdapter<DataVictim> {
        public VictimAdapterList(Context context, ArrayList<DataVictim> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataVictim my_items = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.carditem_victim, parent, false);
            }
            TextView clName = convertView.findViewById(R.id.client_name);
            TextView clIp = convertView.findViewById(R.id.client_ip);
            clName.setText(my_items.name);
            clIp.setText(my_items.ip);
            return convertView;
        }
    }

}