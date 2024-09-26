package com.example.java;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1; // Hằng số yêu cầu bật Bluetooth
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bước 1: Lấy BluetoothAdapter
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        if (bluetoothAdapter == null) {
            // Thiết bị không hỗ trợ Bluetooth
            Toast.makeText(this, "Thiết bị không hỗ trợ Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            // Bước 2: Kiểm tra xem Bluetooth có được bật không
            Button btnON = findViewById(R.id.btn1);
            btnON.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!bluetoothAdapter.isEnabled()) {
                        // Bluetooth bị tắt, yêu cầu người dùng bật
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    }else {
                        bluetoothAdapter.disable();
                        Toast.makeText(MainActivity.this, "Đã tắt", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    // Xử lý kết quả trả về từ yêu cầu bật Bluetooth
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                // Bluetooth đã được bật
                Toast.makeText(this, "Bluetooth đã được bật", Toast.LENGTH_SHORT).show();
            } else {
                // Người dùng từ chối bật Bluetooth
                Toast.makeText(this, "Bluetooth chưa được bật", Toast.LENGTH_SHORT).show();
            }
        }
    }
}