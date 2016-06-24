package com.tdnet.andtm.warikan2;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import com.tdnet.andtm.warikan2.utils.Util;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    //  定数
    private final int ONE_HUNDRED = 100;
    private final int ZERO = 0;

    //  各オブジェクトのインスタンス
    private TextView hmPrc;
    private TextView prcDiff;

    private EditText totalCost;
    private EditText numOfPpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * Added by tsuda
         */
        setTitle("人数割勘");

//      オブジェクト情報を紐付け
        hmPrc = (TextView)findViewById(R.id.showHMPrc);
        prcDiff = (TextView)findViewById(R.id.showPrcDiff);
        totalCost = (EditText)findViewById(R.id.inputTotalCost);
        numOfPpl = (EditText)findViewById(R.id.inputNumOfPpl);

//      入力値を数値のみに限定
        totalCost.setInputType(InputType.TYPE_CLASS_NUMBER);
        numOfPpl.setInputType(InputType.TYPE_CLASS_NUMBER);

//      入力値が変更された際にイベントを発生させる
        totalCost.addTextChangedListener(watchHandlerTc);
        numOfPpl.addTextChangedListener(watchHandlerNop);

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * written by tsuda from here to under
     */
//      入力値が変更された際のイベント処理（totalCost）
    private TextWatcher watchHandlerTc = new TextWatcher() {

//      変更前のイベント
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            Log.d(TAG, "beforeTextChanged() s:" + s.toString() + " start:" + String.valueOf(start) + " count:" + String.valueOf(count) +
//                    " after:" + String.valueOf(after));
        }

//      変更中のイベント
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.d(TAG, "onTextChanged() s:" + s.toString() + " start:" + String.valueOf(start) + " before:" + String.valueOf(before) +
//                    " count:" + String.valueOf(count));

        }

//      変更後のイベント
        @Override
        public void afterTextChanged(Editable s) {
//            Log.d(TAG, "afterTextChanged()");

//          空白ではないか
            if(!totalCost.getText().toString().isEmpty() && !numOfPpl.getText().toString().isEmpty()) {
//              数値かどうか
                if(Util.isNum(totalCost.getText().toString()) && Util.isNum(numOfPpl.getText().toString())) {
//                  0以上か
                    if(Integer.parseInt(totalCost.getText().toString()) > 0 && Integer.parseInt(numOfPpl.getText().toString()) > 0) {
//                      計算実行
                        calcMainWari();
                    }
                }
            }
        }
    };

//      入力値が変更された際のイベント処理（NumOfPpl）
    private TextWatcher watchHandlerNop = new TextWatcher() {

//      変更前のイベント
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            Log.d(TAG, "beforeTextChanged() s:" + s.toString() + " start:" + String.valueOf(start) + " count:" + String.valueOf(count) +
//                    " after:" + String.valueOf(after));
        }

//      変更中のイベント
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.d(TAG, "onTextChanged() s:" + s.toString() + " start:" + String.valueOf(start) + " before:" + String.valueOf(before) +
//                    " count:" + String.valueOf(count));
        }

//      変更後のイベント
        @Override
        public void afterTextChanged(Editable s) {
//            Log.d(TAG, "afterTextChanged()");

            if(!totalCost.getText().toString().isEmpty() && !numOfPpl.getText().toString().isEmpty()) {
                calcMainWari();
            }
        }
    };

    private void calcMainWari(){
//      金額÷人数を算出
        int calcPrc = Integer.parseInt(totalCost.getText().toString()) / Integer.parseInt(numOfPpl.getText().toString());
//      余りを算出
        int remainder = Integer.parseInt(totalCost.getText().toString()) % Integer.parseInt(numOfPpl.getText().toString());
//      一人当たりの金額
        int calcResult;

//      端数の切り上げ処理
        if(calcPrc > remainder) {
            remainder += calcPrc % ONE_HUNDRED;
            calcResult = calcPrc / ONE_HUNDRED;
            calcResult = calcResult * ONE_HUNDRED;

            //余りの切り上げ処理（暫定100）
            while(remainder > ZERO){
                calcResult += ONE_HUNDRED;
                remainder -= ONE_HUNDRED;
            }
//      合計金額より人数が増えた場合（バグ対策）
        }else{
            calcResult = ONE_HUNDRED;
        }

//      Viewへ表示
        hmPrc.setText(String.valueOf(calcResult));
        prcDiff.setText(String.valueOf(calcResult * Integer.parseInt(numOfPpl.getText().toString()) - Integer.parseInt(totalCost.getText().toString())));
    }

}
