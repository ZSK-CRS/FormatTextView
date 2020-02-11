package cn.zsk.formattextview;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.widget.EditText;

import cn.zsk.textlibrary.FormatTextView;
import cn.zsk.textlibrary.Size;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FormatTextView formatTextView = findViewById(R.id.format_text);
        EditText editText = findViewById(R.id.input);
        findViewById(R.id.button).setOnClickListener(v -> {
            String content = editText.getText().toString();
            formatTextView.setContent(content)
                    .setSpecifyText("22")
                    .setSpecifyTextSize(30)
                    .setSpecifyImage(R.drawable.ic_launcher)
                    .setSpecifyImageRect(new Size(200,200))
                    .setSpecifyTextBackgroundColor(Color.RED)
                    .setSpecifyURL("https://www.baidu.com/")
                    .setSpecifyTextStyle(Typeface.ITALIC)
                    .refresh();
        });
    }
}
