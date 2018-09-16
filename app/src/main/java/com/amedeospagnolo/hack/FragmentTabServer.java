package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.msfrpc.MsfServerList;

import java.util.ArrayList;

public class FragmentTabServer extends Fragment {
    public ArrayList<DataServer> myFakeDataset = new ArrayList<DataServer>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_server, container, false);


        MsfServerList msfServerList = MsfApplication.Msf().msfServerList;
        ServerListAdapter serverListAdapter = new ServerListAdapter(getActivity(), msfServerList.getServerList());
        // add fake data
//        FragmentTabServer.ServerAdapterList adapter = new FragmentTabServer.ServerAdapterList(this.getContext(), myFakeDataset);
//        adapter.add(new DataServer("My Server", "44.38.450.40", "", ContextCompat.getDrawable(this.getContext(), R.drawable.server_custom), false));
//        adapter.add(new DataServer("Test Server", "21.43.26.80", "", ContextCompat.getDrawable(this.getContext(), R.drawable.server_test),false));
//        adapter.add(new DataServer("Hackyll Server", "124.88.0.40", "00:00:00", ContextCompat.getDrawable(this.getContext(), R.drawable.server_hackyll),true));
        ListView listView = rootView.findViewById(R.id.list_servers);
        listView.setAdapter(serverListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
                String srName = myFakeDataset.get(position).name;
                String srIp = myFakeDataset.get(position).ip;
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

    /*
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

            TextView clName = convertView.findViewById(R.id.server_name);
            clName.setText(my_items.name);

            TextView clIp = convertView.findViewById(R.id.server_ip);
            clIp.setText(my_items.ip);

            TextView clTime = convertView.findViewById(R.id.server_time);
            clTime.setText(my_items.time);

            ImageView clImage = convertView.findViewById(R.id.server_image);
            clImage.setImageDrawable(my_items.avatar);

            // show shop
            LinearLayout shop = convertView.findViewById(R.id.server_shop);
            if (!my_items.has_shop) shop.setVisibility(View.GONE);

            return convertView;
        }
    }
    */

}