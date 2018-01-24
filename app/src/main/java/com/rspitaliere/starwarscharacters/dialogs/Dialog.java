package com.rspitaliere.starwarscharacters.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rspitaliere.starwarscharacters.R;
import com.rspitaliere.starwarscharacters.utils.FontUtils;

/**
 * Created by rspitaliere on 23/01/18.
 */

public class Dialog {

    private Context context;
    private static  final String message = "parabéns você descobriu: ";

    public Dialog(Context context, String message){
        this.context = context;
        dialog(message);
    }

    private void dialog(String str){
        final android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.setContentView(R.layout.dialog_ok);
        Button buttonOk = dialog.findViewById(R.id.button_ok);
        TextView msg = dialog.findViewById(R.id.message);
        msg.setTypeface(FontUtils.fontRegular(context));
        buttonOk.setTypeface(FontUtils.fontRegular(context));
        msg.setText(message + str);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
