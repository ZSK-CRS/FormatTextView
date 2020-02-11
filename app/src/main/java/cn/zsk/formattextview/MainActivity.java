package cn.zsk.formattextview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;

import cn.zsk.textlibrary.FormatTextView;

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
                    .setSpecifyText("知是谁")
                    .setSpecifyTextColor(Color.RED)
                    .setSpecifyTextSize(30)
                    .setSpecifyTextStyle(Typeface.ITALIC)
                    .refresh();
        });
    }
}
