package com.amedeospagnolo.hack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class ClientActivity extends AppCompatActivity {
    private EditText editText;
    private TextView clName;
    private TextView clIp;
    AutoCompleteTextView autocomplete;

    String[] arr = {"cd /home &nbsp;","cd ..​","cd ../..","cd ​","cd ~user","cd -​","cp file1 file2","cp dir/* .","cp -a /tmp/dir1 .","cp -a dir1 dir2","cp file file1 ","ln -s file1 lnk1","ln file1 lnk1","ls","ls -F","ls -l","ls -a","ls *[0-9]*","lstree","mkdir dir1","mkdir dir1 dir2","mkdir -p /tmp/dir1/dir2","mv dir/file /new_path","pwd","rm -f file1","rm -rf dir1","rm -rf dir1 dir2","rmdir dir1","touch -t 0712250000 file1","tree","top","htop","ps","pstree","who","kill","pkill &amp; killall","pgrep","nice","renice","pidof","df","free","chmod 755 Linux_Directory chmod 644 Linux_File","rwx rwx rwx = 111 111 111","​rw- rw- rw- = 110 110 110","rwx --- --- = 111 000 000​","rwx = 111 in binary = 7","rw- = 110 in binary ​= 6","r-x = 101 in binary = 5","r-- = 100 in binary = 4​","777 (rwxrwxrwx)","755 (rwxr-xr-x)","700 (rwx------)","666 (rw-rw-rw-)","644 (rw-r--r--)","600 (rw-------)","find -name 'File1'","find -iname 'File1'","find /path -type f -name '*.conf'","find /path -size 50c","find /path -size -50c","find / -size +700M","find / -mtime 1","find / -atime -1","find / -ctime +3","find / -mmin -1","find / -perm 644","find / -perm -644","wc -l file_name OR cat file_name | wc -l","wc -w","wc -c","wc -m","wc -L","tar -cvf compress.tar /path/directory","tar -tvf compress.tar","tar -xvf compress.tar","tar -xvf compress.tar -C /path/to diretory","tar -xvf compress.tar file1.txt","tar -xvf compress.tar 'file 1' 'file 2'","tar -xvf compress.tar --wildcards '*.txt' ","tar -rvf compress.tar file/dir","tar -cvzf compresstar.gz /path/directory","tar -tvf compress.tar.gz","tar -zxvf compress.tar.gz","tar -zxvf compress.tar.gz -C /path/to diretory","tar -zxvf compress.tar.gz file1.txt","tar -zxvf compress.tar.gz 'file 1' 'file 2'","tar -zxvf compress.tar.gz --wildcards '*.tzt'","tar -rvf compress.tar.gz file/dir","tar -cvfj compress.tar.bz2 /path/directory","tar -tvf compress.tar.bz2","tar -xvf compress.tar.bz2","tar -jxvf compress.tar.bz2 file1.txt","tar -jxvf compress.tar.bz2 'file 1' 'file 2'","tar -jxvf compress.tar.bz2 --wildcards '*.tzt'","tar -rvf compress.tar.bz2 file/dir","tar -tvfW cmpress.tar ","zip compress.zip file1 file2 folder1","zip compress.zip file1 file2 file3","zip compress.zip Folder/*","zip -r compress.zip Folder","unzip -l compress.zip","less compress.zip","zipinfo -1 compress.zip","zip -d compress.zip path/to/file","unzip compress.zip","unzip compress.zip -d /destination","unzip compress.zip test.sh","chattr +a file1","chattr +c file1","chattr +d file1","chattr +i file1","chattr +s file1","chattr +S file1","chattr +u file1","lsattr file/folder ","uname","uname -n","uname -v","uname -r","uname -r","uname -m","uname -a","cat /proc/version","cat /etc/*release*","fdisk -l","mount","lscpu","cat /proc/cpuinfo","lsblk","dmidecode -t memory","cat /proc/meminfo","free&nbsp;or free -mt&nbsp;or free -gt","dmidecode -t system","dmidecode -t bios","dmidecode -t processor","dmidecode | less","ping IP/hostname","ifconfig -a","traceroute http://website.com/","route","dig http://website.com/","whois http://website.com/","host hostname host 1.2.3.4","telnet http://website.com/","tracepath http://website.com/","nslookup http://website.com/","netstat","scp -r -P 22 (ssh port) user@source_hostname:/path/to/dir /destination/path","nmap hostname -p 80","ssh user@host","ssh -p port user@host","Ctrl+C","Ctrl+Z","Ctrl+D","Ctrl+W","Ctrl+U","Ctrl+R","exit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        editText = findViewById(R.id.myNewMessageBox);
        clName = findViewById(R.id.this_client_name);
        clIp = findViewById(R.id.this_client_ip);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            clName = null;
            clIp = null;

        } else {
            clName.setText(extras.getString("client_name"));
            clIp.setText(extras.getString("client_ip"));
        }

        // ADD TOOLBAR
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
            }
        });

        // autocomplete
        autocomplete = findViewById(R.id.myNewMessageBox);
        ArrayAdapter<String> adapter_autocomplete = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arr);
        autocomplete.setThreshold(2);
        autocomplete.setAdapter(adapter_autocomplete);

        // add fake data
        ArrayList<DataChat> myFakeDataset = new ArrayList<DataChat>();
        myChatAdapter_mine adapter = new myChatAdapter_mine(this, myFakeDataset);
//        myChatAdapter_their adapter = new myChatAdapter_their(this, myFakeDataset);
        adapter.add(new DataChat("Hi, Nathan!"));
        adapter.add(new DataChat("Hi, Bob!"));
        adapter.add(new DataChat("Hi, Francene!"));
        adapter.add(new DataChat("Hi, Cammie!"));
        adapter.add(new DataChat("Hi, Ines!"));
        adapter.add(new DataChat("Hi, Celeste!"));
        adapter.add(new DataChat("Hi, Renata!"));
        adapter.add(new DataChat("Hi, Stephan!"));
        adapter.add(new DataChat("Hi, Marcia!"));
        adapter.add(new DataChat("Hi, Romona!"));
        adapter.add(new DataChat("Hi, Carter!"));
        adapter.add(new DataChat("Hi, Roslyn!"));
        adapter.add(new DataChat("Hi, Marshall!"));
        adapter.add(new DataChat("Hi, Donnell!"));
        adapter.add(new DataChat("Hi, Sigrid!"));
        ListView listView = findViewById(R.id.messages_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
//                String selectedmovie=myFakeDataset.get(position);
//                Toast.makeText(getApplicationContext(), "Movie Selected : "+selectedmovie,   Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_client_details) {
            // do something
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class myChatAdapter_their extends ArrayAdapter<DataChat> {
        public myChatAdapter_their(Context context, ArrayList<DataChat> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataChat my_items = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_their_message, parent, false);
            }
            TextView chatMessage = (TextView) convertView.findViewById(R.id.chat_message);
            chatMessage.setText(my_items.message);
            return convertView;
        }
    }

    public class myChatAdapter_mine extends ArrayAdapter<DataChat> {
        public myChatAdapter_mine(Context context, ArrayList<DataChat> my_items) {
            super(context, 0, my_items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataChat my_items = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_my_message, parent, false);
            }
            TextView chatMessage = (TextView) convertView.findViewById(R.id.chat_message);
            chatMessage.setText(my_items.message);
            return convertView;
        }
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            editText.getText().clear();
            // do something
        }
    }

}
