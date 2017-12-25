package com.example.owner.yajudentaku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //

    public int answer = 0; //答え
    public int number = 0; //数字
    public int operation = 0;
    public TextView textView; //数字表示

    private SoundPool soundPool; //音鳴らす
    private int soundOne,soundTwo,soundThree,soundFour,soundFive,soundSix,soundSeven,soundEight,soundNine,soundTen,soundEqual; //音鳴らす
    //ここでのsoundOne...は後から指定するのでわかりやすい名前を付けておく

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView); //textViewというidを見つけて指定
        textView.setText("0"); //初期値

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
//                .setAudioAttributes(audioAttributes)
                // ストリーム数に応じて
                .setMaxStreams(1)
                .build();


        // 音楽をロードしておく
        soundOne = soundPool.load(this, R.raw.a, 1); //0
        soundTwo = soundPool.load(this, R.raw.b, 1);
        soundThree = soundPool.load(this, R.raw.c, 1);
        soundFour = soundPool.load(this, R.raw.d, 1);
        soundFive = soundPool.load(this, R.raw.e, 1);
        soundSix = soundPool.load(this, R.raw.f, 1);
        soundSeven = soundPool.load(this, R.raw.g, 1);
        soundEight = soundPool.load(this, R.raw.h, 1);
        soundNine = soundPool.load(this, R.raw.k, 1);
        soundTen = soundPool.load(this, R.raw.j, 1);
        soundEqual = soundPool.load(this, R.raw.x, 1); //10
        //ここは データ名 = soundPool.lead(this,R.raw.ファイル名,1)で覚える


        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        });


    }

    //zero~nineが押されたとき
    //number * 10 + 数字 は数字を追加する際
    // soundPool.play(soundOne,1.0f,1.0f,0,0,1);はテンプレート
    public void zero(View v){
        number = number * 10 +0;
        textView.setText(String.valueOf(number));
        soundPool.play(soundOne,1.0f,1.0f,0,0,1);

    }

    public void one(View v){
        number = number * 10 +1;
        textView.setText(String.valueOf(number));
        soundPool.play(soundTwo,1.0f,1.0f,0,0,1);

    }

    public void two(View v){
        number = number * 10 +2;
        textView.setText(String.valueOf(number));
        soundPool.play(soundThree,1.0f,1.0f,0,0,1);

    }

    public void three(View v){
        number = number * 10 +3;
        textView.setText(String.valueOf(number));
        soundPool.play(soundFour,1.0f,1.0f,0,0,1);

    }

    public void four(View v){
        number = number * 10 +4;
        textView.setText(String.valueOf(number));
        soundPool.play(soundFive,1.0f,1.0f,0,0,1);

    }

    public void five(View v){
        number = number * 10 +5;
        textView.setText(String.valueOf(number));
        soundPool.play(soundSix,1.0f,1.0f,0,0,1);

    }

    public void six(View v){
        number = number * 10 +6;
        textView.setText(String.valueOf(number));
        soundPool.play(soundSeven,1.0f,1.0f,0,0,1);

    }

    public void seven(View v){
        number = number * 10 +7;
        textView.setText(String.valueOf(number));
        soundPool.play(soundEight,1.0f,1.0f,0,0,1);

    }

    public void eight(View v){
        number = number * 10 +8;
        textView.setText(String.valueOf(number));
        soundPool.play(soundNine,1.0f,1.0f,0,0,1);

    }

    public void nine(View v){
        number = number * 10 +9;
        textView.setText(String.valueOf(number));
        soundPool.play(soundTen,1.0f,1.0f,0,0,1);

    }

    //足すとき処理
    public void plus(View v){
        calculate();
        operation = 1;
        textView.setText("+");
        soundPool.play(soundEqual,1.0f,1.0f,0,0,1);

    }

    //引くとき
    public void minus(View v){
        calculate();
        operation = 2;
        textView.setText("-");
        soundPool.play(soundEqual,1.0f,1.0f,0,0,1);
    }

    //かけるとき
    public void kakeru(View v){
        calculate();
        operation = 3;
        textView.setText("×");
        soundPool.play(soundEqual,1.0f,1.0f,0,0,1);
    }

    //割る時
    public void waru(View v){
        calculate();
        operation = 4;
        textView.setText("÷");
        soundPool.play(soundEqual,1.0f,1.0f,0,0,1);
    }

    //Clearコマンド
    public void allclear(){
        answer = 0;
        number = 0;
        operation = 0;
        soundPool.play(soundEqual,1.0f,1.0f,0,0,1);

    }

    //イコール処理
    public void equal(View v){
        calculate();
        textView.setText(String.valueOf(answer));
        allclear();
        /*
        要は
        answer = 0;
        number = 0;
        operation = 0;
        */
        soundPool.play(soundEqual,1.0f,1.0f,0,0,1);
    }

    public  void clear(View v){
        allclear();
        /*
        要は
        answer = 0;
        number = 0;
        operation = 0;
        */
        textView.setText(String.valueOf(answer));
        soundPool.play(soundEqual,1.0f,1.0f,0,0,1);

    }

    //計算方法によっての場合分け
    public void calculate(){
        switch (operation){

            //何もなし(本来エラー)
            case 0:
                answer = number;
                break;

            //足し算
            case 1:
                answer = answer + number;
                break;

            //引き算
            case 2:
                answer = answer - number;
                break;

            //掛け算
            case 3:
                answer = answer * number;
                break;

            //割り算
            case 4:
                if(answer == 0){
                    answer = 0;
                } else {
                    answer = answer / number;
                break;
                }
        }

        if(answer == 114514){
            Toast.makeText(this, "やりますねぇ", Toast.LENGTH_SHORT).show();
        }

        //計算が終わったので初期値は0にもどす
        number = 0;
    }
}
