package com.dwb.lottery_two.style;

import android.app.Dialog;
import android.content.Context;

/** app 检测更新
 * Created by dwb on 2017/8/17.
 */

public class BaseDialog extends Dialog {
    private int res;

    public BaseDialog(Context context, int theme, int res) {
        super(context, theme);
        // TODO 自动生成的构造函数存根
        setContentView(res);
        this.res = res;

        setCanceledOnTouchOutside(false);
    }
}
