package com.amedeospagnolo.hack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientActivity extends AppCompatActivity {
    private EditText editText;
    private TextView clName;
    private TextView clIp;
    AutoCompleteTextView autocomplete;

    Integer IMAGE  = 10;
    Integer IMAGE1 = 11;
    Integer IMAGE2 = 12;
    Integer IMAGE3 = 13;

    Integer SOUND  = 20;
    Integer SOUND1 = 21;
    Integer SOUND2 = 22;
    Integer SOUND3 = 23;

    String[] arr = {"play [sound1] [sound2]", "play [sound]", "send [image]", "send [image1] [image2]", "asd [asdqwe]","cd /home &nbsp;","cd ..​","cd ../..","cd ​","cd ~user","cd -​","cp file1 file2","cp dir/* .","cp -a /tmp/dir1 .","cp -a dir1 dir2","cp file file1 ","ln -s file1 lnk1","ln file1 lnk1","ls","ls -F","ls -l","ls -a","ls *[0-9]*","lstree","mkdir dir1","mkdir dir1 dir2","mkdir -p /tmp/dir1/dir2","mv dir/file /new_path","pwd","rm -f file1","rm -rf dir1","rm -rf dir1 dir2","rmdir dir1","touch -t 0712250000 file1","tree","top","htop","ps","pstree","who","kill","pkill &amp; killall","pgrep","nice","renice","pidof","df","free","chmod 755 Linux_Directory chmod 644 Linux_File","rwx rwx rwx = 111 111 111","​rw- rw- rw- = 110 110 110","rwx --- --- = 111 000 000​","rwx = 111 in binary = 7","rw- = 110 in binary ​= 6","r-x = 101 in binary = 5","r-- = 100 in binary = 4​","777 (rwxrwxrwx)","755 (rwxr-xr-x)","700 (rwx------)","666 (rw-rw-rw-)","644 (rw-r--r--)","600 (rw-------)","find -name 'File1'","find -iname 'File1'","find /path -type f -name '*.conf'","find /path -size 50c","find /path -size -50c","find / -size +700M","find / -mtime 1","find / -atime -1","find / -ctime +3","find / -mmin -1","find / -perm 644","find / -perm -644","wc -l file_name OR cat file_name | wc -l","wc -w","wc -c","wc -m","wc -L","tar -cvf compress.tar /path/directory","tar -tvf compress.tar","tar -xvf compress.tar","tar -xvf compress.tar -C /path/to diretory","tar -xvf compress.tar file1.txt","tar -xvf compress.tar 'file 1' 'file 2'","tar -xvf compress.tar --wildcards '*.txt' ","tar -rvf compress.tar file/dir","tar -cvzf compresstar.gz /path/directory","tar -tvf compress.tar.gz","tar -zxvf compress.tar.gz","tar -zxvf compress.tar.gz -C /path/to diretory","tar -zxvf compress.tar.gz file1.txt","tar -zxvf compress.tar.gz 'file 1' 'file 2'","tar -zxvf compress.tar.gz --wildcards '*.tzt'","tar -rvf compress.tar.gz file/dir","tar -cvfj compress.tar.bz2 /path/directory","tar -tvf compress.tar.bz2","tar -xvf compress.tar.bz2","tar -jxvf compress.tar.bz2 file1.txt","tar -jxvf compress.tar.bz2 'file 1' 'file 2'","tar -jxvf compress.tar.bz2 --wildcards '*.tzt'","tar -rvf compress.tar.bz2 file/dir","tar -tvfW cmpress.tar ","zip compress.zip file1 file2 folder1","zip compress.zip file1 file2 file3","zip compress.zip Folder/*","zip -r compress.zip Folder","unzip -l compress.zip","less compress.zip","zipinfo -1 compress.zip","zip -d compress.zip path/to/file","unzip compress.zip","unzip compress.zip -d /destination","unzip compress.zip test.sh","chattr +a file1","chattr +c file1","chattr +d file1","chattr +i file1","chattr +s file1","chattr +S file1","chattr +u file1","lsattr file/folder ","uname","uname -n","uname -v","uname -r","uname -r","uname -m","uname -a","cat /proc/version","cat /etc/*release*","fdisk -l","mount","lscpu","cat /proc/cpuinfo","lsblk","dmidecode -t memory","cat /proc/meminfo","free&nbsp;or free -mt&nbsp;or free -gt","dmidecode -t system","dmidecode -t bios","dmidecode -t processor","dmidecode | less","ping IP/hostname","ifconfig -a","traceroute http://website.com/","route","dig http://website.com/","whois http://website.com/","host hostname host 1.2.3.4","telnet http://website.com/","tracepath http://website.com/","nslookup http://website.com/","netstat","scp -r -P 22 (ssh port) user@source_hostname:/path/to/dir /destination/path","nmap hostname -p 80","ssh user@host","ssh -p port user@host","Ctrl+C","Ctrl+Z","Ctrl+D","Ctrl+W","Ctrl+U","Ctrl+R","exit"};

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
        ArrayAdapter<String> adapter_autocomplete = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, arr);
        autocomplete.setThreshold(2);
        autocomplete.setAdapter(adapter_autocomplete);

        // SpannableString
        autocomplete.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                autocomplete.removeTextChangedListener(this);
                Integer cursor = autocomplete.getSelectionStart();
                setTags(autocomplete,s.toString());
                autocomplete.setSelection(cursor);
                autocomplete.addTextChangedListener(this);
            }
        });

        // add fake data
        ArrayList<DataChat> myFakeDataset = new ArrayList<>();
        myChatAdapter_mine adapter = new myChatAdapter_mine(this, myFakeDataset);
        // myChatAdapter_their adapter = new myChatAdapter_their(this, myFakeDataset);
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
            TextView chatMessage = convertView.findViewById(R.id.chat_message);
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
            TextView chatMessage = convertView.findViewById(R.id.chat_message);
            chatMessage.setText(my_items.message);
            return convertView;
        }
    }

    private void setTags(TextView pTextView, final String pTagString) {
        SpannableString string = new SpannableString(pTagString);
        int start = -1;
        for (int i = 0; i < pTagString.length(); i++) {
            if (pTagString.charAt(i) == '[') {
                start = i;
            } else if (pTagString.charAt(i) == ']' && start != -1) {
                final String tag = pTagString.substring(start+1, i);
                if (    tag.equals("image")  ||
                        tag.equals("image1") ||
                        tag.equals("image2") ||
                        tag.equals("image3") ||
                        tag.equals("sound")  ||
                        tag.equals("sound1") ||
                        tag.equals("sound2") ||
                        tag.equals("sound3")){
                    string.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            Intent intent = new Intent(
                                    Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            setResult(Activity.RESULT_OK, intent);
                            switch (tag) {
                                case "image" : { startActivityForResult(intent, IMAGE);  break; }
                                case "image1": { startActivityForResult(intent, IMAGE1); break; }
                                case "image2": { startActivityForResult(intent, IMAGE2); break; }
                                case "image3": { startActivityForResult(intent, IMAGE3); break; }
                                case "sound" : { startActivityForResult(intent, SOUND);  break; }
                                case "sound1": { startActivityForResult(intent, SOUND1); break; }
                                case "sound2": { startActivityForResult(intent, SOUND2); break; }
                                case "sound3": { startActivityForResult(intent, SOUND3); break; }
                            }
                        }
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            ds.setColor(Color.parseColor("#33b5e5"));
                            ds.setUnderlineText(true);
                        }
                    }, start, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                start = -1;
            }
        }
        pTextView.setMovementMethod(LinkMovementMethod.getInstance());
        pTextView.setText(string);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            editText = findViewById(R.id.myNewMessageBox);
            String modify_text = editText.getText().toString();
            if (requestCode == IMAGE)  { modify_text = editText.getText().toString().replace("[image]", picturePath);  }
            if (requestCode == IMAGE1) { modify_text = editText.getText().toString().replace("[image1]", picturePath); }
            if (requestCode == IMAGE2) { modify_text = editText.getText().toString().replace("[image2]", picturePath); }
            if (requestCode == IMAGE3) { modify_text = editText.getText().toString().replace("[image3]", picturePath); }
            if (requestCode == SOUND)  { modify_text = editText.getText().toString().replace("[sound]", picturePath);  }
            if (requestCode == SOUND1) { modify_text = editText.getText().toString().replace("[sound1]", picturePath); }
            if (requestCode == SOUND2) { modify_text = editText.getText().toString().replace("[sound2]", picturePath); }
            if (requestCode == SOUND3) { modify_text = editText.getText().toString().replace("[sound3]", picturePath); }
            editText.setText(modify_text);
        }

    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            editText.getText().clear();
            // do something
        }
    }

    public void open_help(View view) {
        Context context = getApplicationContext();
        Intent intent = new Intent(context, HelpActivity.class);
        context.startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
    }

}
