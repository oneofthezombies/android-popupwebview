package com.example.test_android2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    PopupWindow popupWindow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View view) {

                // 전체화면인 팝업윈도우 위에 올려질 웹뷰
                WebView webview = new WebView(MainActivity.this);
                webview.setWebViewClient(new WebViewClient());
                webview.loadUrl("https://google.com");

                // 전체화면인 팝업윈도우 위에 올려질 웹뷰용 레이아웃
                RelativeLayout layout = new RelativeLayout(MainActivity.this);
                layout.setLayoutParams(new ViewGroup.LayoutParams(500, 500));
                layout.setX(700);
                layout.setY(200);
                layout.addView(webview);

                // 팝업윈도우를 전체화면으로 만든다.
                // 팝업윈도우 밑 메인액티비티의 터치이벤트는 발생하지 않게 된다.
                // 팝업윈도우가 포커스 가능해야 하위 웹뷰 input 포커스시 팝업키보드가 뜬다.
                popupWindow = new PopupWindow(view);
                popupWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.setContentView(layout);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        // 팝업윈도우 터치이벤트가 발생했을 때, 메인 액티비티의 터치이벤트를 발생시킨다.
                        MainActivity.this.dispatchTouchEvent(event);
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}