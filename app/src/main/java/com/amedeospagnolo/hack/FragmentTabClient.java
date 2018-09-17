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

import com.msfrpc.adapter.ModelPresenter;
import com.msfrpc.model.RpcSession;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabClient extends Fragment implements ModelPresenter.UpdateListener {

    ModelPresenter modelPresenter = new ModelPresenter();
    List<RpcSession> rpcSessionList  = new ArrayList<>();
    ClientAdapterList adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_client, container, false);

        adapter = new ClientAdapterList(this.getContext(), rpcSessionList);

        ListView listView = rootView.findViewById(R.id.list_clients);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3){
//                String clName = myFakeDataset.get(position).name;
//                String clIp = myFakeDataset.get(position).ip;
//                Context context = getContext();
//                Intent intent = new Intent(context, ClientActivity.class);
//                intent.putExtra("client_name", clName);
//                intent.putExtra("client_ip", clIp);
//                if (context != null) context.startActivity(intent);
                // overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        modelPresenter.addListener(this);
        modelPresenter.update();
        super.onResume();
    }

    @Override
    public void onPause() {
        modelPresenter.removeListener(this);
        super.onPause();
    }

    @Override
    public void onUpdated() {
       List<RpcSession> newSessionList = MsfApplication.Msf().getAllSessions();
       rpcSessionList.clear();
       rpcSessionList.addAll(newSessionList);
       getActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               adapter.notifyDataSetChanged();
           }
       });
    }

    public class ClientAdapterList extends ArrayAdapter<RpcSession> {
        public ClientAdapterList(Context context, List<RpcSession> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RpcSession rpcSession = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.carditem_client, parent, false);
            }

            TextView clName = convertView.findViewById(R.id.client_name);
            clName.setText(rpcSession.session.id);

            String ip = (String) rpcSession.session.fields.get("tunnel_peer");
            TextView clIp = convertView.findViewById(R.id.client_ip);
            clIp.setText(ip);


//            TextView clTime = convertView.findViewById(R.id.client_time);
//            clTime.setText(type);
//
//            ImageView clImage = convertView.findViewById(R.id.client_image);
//            clImage.setImageDrawable(my_items.avatar);

            return convertView;
        }
    }

}