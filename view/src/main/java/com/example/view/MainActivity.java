package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.view.view.BezierView.BezierMultilevelViewTest;
import com.example.view.view.BezierView.BezierViewDemoOne;
import com.example.view.view.BezierView.PathMeasureView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(new BezierViewTest(this));
//        setContentView(new BezierView(this));
//        setContentView(new BezierMultilevelViewTest(this));
//        setContentView(new BezierViewDemoOne(this));
        setContentView(new PathMeasureView(this));

    }
}
