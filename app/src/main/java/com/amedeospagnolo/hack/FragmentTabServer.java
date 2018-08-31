package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentTabServer extends Fragment {
    public ArrayList<DataServer> myFakeDataset = new ArrayList<DataServer>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_server, container, false);

        // add fake data
        FragmentTabServer.ServerAdapterList adapter = new FragmentTabServer.ServerAdapterList(this.getContext(), myFakeDataset);
        adapter.add(new DataServer("server_Francene", "0.0.0.40"));
        adapter.add(new DataServer("server_Tre", "0.1.1.10"));
        adapter.add(new DataServer("pi_Ines", "0.30.0.55"));
        ListView listView = rootView.findViewById(R.id.list_servers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
                String srName = myFakeDataset.get(position).server_name;
                String srIp = myFakeDataset.get(position).server_ip;
                Context context = getContext();
                Intent intent = new Intent(context, ServerActivity.class);
                intent.putExtra("server_name", srName);
                intent.putExtra("server_ip", srIp);
                if (context != null) context.startActivity(intent);
                // overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            }
        });
        return rootView;
    }

    public class ServerAdapterList extends ArrayAdapter<DataServer> {
        public ServerAdapterList(Context context, ArrayList<DataServer> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataServer my_items = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.carditem_server, parent, false);
            }
            TextView srName = convertView.findViewById(R.id.server_name);
            TextView srIp = convertView.findViewById(R.id.server_ip);
            srName.setText(my_items.server_name);
            srIp.setText(my_items.server_ip);
            return convertView;
        }
    }

}