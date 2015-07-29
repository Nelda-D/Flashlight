package org.nelda.flashlight;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/7 0007.
 */
public class MorseActivity extends WarningLightActivity {


    private Map<Character,String> mMorseCodeMap = new HashMap<>();
    private String mMorseCode;

    private final long DOT_TIME = 200;                 // 点停留的时间，单位：毫秒
    private final long LINE_TIME = DOT_TIME * 3;       // 线停留的时间
    private final long DOT_LINE_TIME = DOT_TIME;       // 点到线的时间间隔
    private final long CHAR_CHAR_TIME = DOT_TIME * 3;  // 字符到字符的时间间隔
    private final long WORD_WORD_TIME = DOT_TIME * 7 ; // 单词到单词的时间间隔

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //英文字母的摩尔斯码
        mMorseCodeMap.put('a', ".-");
        mMorseCodeMap.put('b',"-...");
        mMorseCodeMap.put('c',"-.-.");
        mMorseCodeMap.put('d',"-..");
        mMorseCodeMap.put('e',".");
        mMorseCodeMap.put('f',"..-.");
        mMorseCodeMap.put('g',"--.");
        mMorseCodeMap.put('h',"....");
        mMorseCodeMap.put('i',"..");
        mMorseCodeMap.put('j',".---");
        mMorseCodeMap.put('k',"-.-");
        mMorseCodeMap.put('l',".-..");
        mMorseCodeMap.put('m',"--");
        mMorseCodeMap.put('n',"-.");
        mMorseCodeMap.put('o',"---");
        mMorseCodeMap.put('p',".--.");
        mMorseCodeMap.put('q',"--.-");
        mMorseCodeMap.put('r',".-.");
        mMorseCodeMap.put('s',"...");
        mMorseCodeMap.put('t',"-");
        mMorseCodeMap.put('u',"..-");
        mMorseCodeMap.put('v',"...-");
        mMorseCodeMap.put('w',".--");
        mMorseCodeMap.put('x',"-..-");
        mMorseCodeMap.put('y',"-.--");
        mMorseCodeMap.put('z',"--..");
        //数字的摩尔斯码
        mMorseCodeMap.put('1',".----");
        mMorseCodeMap.put('2',"..---");
        mMorseCodeMap.put('3',"...--");
        mMorseCodeMap.put('4',"....-");
        mMorseCodeMap.put('5',".....");
        mMorseCodeMap.put('6',"-....");
        mMorseCodeMap.put('7',"--...");
        mMorseCodeMap.put('8',"---..");
        mMorseCodeMap.put('9',"----.");
        mMorseCodeMap.put('0',"-----");
    }

    public void onClick_Morse_Send(View view){
        if(verifyMorseCode()){
            sendSentence(mMorseCode);
        }
    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //发送点
    private void sendDot(){
        openFlashlight();
        sleep(DOT_TIME);
        closeFlashlight();
    }
    //发送线
    private void sendLine(){
        openFlashlight();
        sleep(LINE_TIME);
        closeFlashlight();
    }
    //发送字符
    private void sendChar(char c){
        String morseCode = mMorseCodeMap.get(c);
        char lastChar = ' ';
        if(morseCode != null){
            for(int i = 0;i<morseCode.length();i++){
                char dotLine = morseCode.charAt(i);
                if(i>0){
                    if(lastChar == '.' && dotLine == '-'){
                        sleep(DOT_LINE_TIME);
                    }
                }
                if(dotLine == '.'){
                    sendDot();
                }else if(dotLine == '-'){
                    sendLine();
                }
                lastChar = dotLine ;
            }
        }
    }
    //发送单词
    private void sendWord(String s){
        for (int i =0;i<s.length();i++){
            char wordChar = s.charAt(i);
            sendChar(wordChar);
            if(i<s.length()-1){
                sleep(CHAR_CHAR_TIME);
            }
        }
    }
    //发送句子
    private void sendSentence(String s){
        String[] words = s.split(" ");
        for(int i=0;i<words.length;i++){
            sendWord(words[i]);
            if(i<words.length-1){
                sleep(WORD_WORD_TIME);
            }
        }
        Toast.makeText(this,"摩尔斯电码已经发送完毕",Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断EditText中的数据是否符合规格
     */
    private boolean verifyMorseCode(){
        mMorseCode = mEditText_UIMorse_MorseChar.getText().toString().toLowerCase();
        if(" ".equals(mMorseCode)){
            Toast.makeText(this,"请输入摩尔斯电码",Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i=0;i<mMorseCode.length();i++){
            char c = mMorseCode.charAt(i);
            if(!(c >='a' && c<='z')&&!(c>='0' && c<='9') && c != ' '){
                Toast.makeText(this,"摩尔斯电码只能输入字母和数字！",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
