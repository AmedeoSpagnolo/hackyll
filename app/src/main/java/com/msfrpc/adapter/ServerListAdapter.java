//package com.msfrpc.adapter;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//
//import com.msfrpc.Msf;
//import com.msfrpc.MsfServerList;
//import com.msfrpc.R;
//import com.msfrpc.activities.ServerDetailActivity;
//import com.msfrpc.model.MsfModel;
//import com.msfrpc.model.RpcServer;
//import com.msfrpc.view.RpcServerView;
//
//import java.util.List;
//
//public class ServerListAdapter extends ArrayAdapter<RpcServer> {
//
//    private Activity activity;
//    private List<RpcServer> innerList;
//    private View.OnClickListener onItemClicked = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            int position = (int) view.getTag();
//            if (view.getId() == R.id.imageview_delete) {
//                innerList.remove(position);
//                notifyDataSetChanged();
//                Msf.get().msfServerList.saveServerList();
//            } else if (view.getId() == R.id.imageview_edit) {
//                Intent intent = new Intent(activity, ServerDetailActivity.class);
//                intent.putExtra(MsfServerList.RPC_SERVER_ID, position);
//                activity.startActivity(intent);
//            }
//        }
//    };
//
//    public ServerListAdapter(Activity context, List<RpcServer> innerList) {
//        super(context, 0, innerList);
//        activity = context;
//        updateView();
//    }
//
//    public void updateView() {
//        this.innerList = Msf.get().msfServerList.getServerList();
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View rowView = convertView;
//        if (rowView == null) {
//            LayoutInflater inflater = activity.getLayoutInflater();
//            rowView = inflater.inflate(R.layout.item_server, parent, false);
//            ViewHolder viewHolder = new ViewHolder();
//            viewHolder.rpcServerView = (RpcServerView) rowView.findViewById(R.id.rpcserverview_server);
//            viewHolder.imageviewEdit = (ImageView) rowView.findViewById(R.id.imageview_edit);
//            viewHolder.imageviewDelete = (ImageView) rowView.findViewById(R.id.imageview_delete);
//            viewHolder.imageviewEdit.setOnClickListener(onItemClicked);
//            viewHolder.imageviewDelete.setOnClickListener(onItemClicked);
//            rowView.setTag(viewHolder);
//        }
//
//        ViewHolder holder = (ViewHolder) rowView.getTag();
//        RpcServer item = getItem(position);
//        holder.rpcServerView.updateView(item);
//        holder.imageviewDelete.setTag(position);
//        holder.imageviewEdit.setTag(position);
//        return rowView;
//    }
//
//    static class ViewHolder {
//        RpcServerView rpcServerView;
//        ImageView imageviewEdit;
//        ImageView imageviewDelete;
//    }
//}
