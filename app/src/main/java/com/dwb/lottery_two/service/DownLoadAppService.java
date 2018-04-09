package com.dwb.lottery_two.service;


import android.content.Intent;
import android.os.IBinder;

import com.allenliu.versionchecklib.core.AVersionService;
import com.dwb.lottery_two.javabean.AppInfoModel;
import com.dwb.lottery_two.javabean.CheckInfoModel;
import com.dwb.lottery_two.utils.DSLConnections;

/**更新app service
 * Created by dwb on 2017/8/17.
 */

public class DownLoadAppService extends AVersionService {
    // 更新版本要用到的一些信息
    private CheckInfoModel checkInfoModel;
    private AppInfoModel appInfoModel;
    public DownLoadAppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onResponses(AVersionService service, String response) {
        service.showVersionDialog(DSLConnections.APP_ULR, "检测到新版本请及时更新","发现新的版本啦!");
//        Log.e("DemoService", response);
        //可以在判断版本之后在设置是否强制更新或者VersionParams
        //eg
        // versionParams.isForceUpdate=true;
/*        if(response!=null&&!"".equals(response)){
            try {
                Gson mgson=new Gson();
                checkInfoModel = mgson.fromJson(response, CheckInfoModel.class);
                String app_mag=Base64.decode(checkInfoModel.getData(),AppInfoModel.class);
                appInfoModel=mgson.fromJson(app_mag,AppInfoModel.class);
                if ("1".equals(appInfoModel.getShow_url())){
                    String name=appInfoModel.getUrl();
                    if ("apk".equals(name.substring(name.length()-3,name.length()))){
                        if (NetWorkUtil.checkPackInfo(this, Constant.GO_Package)) {
                            Intent intent =getPackageManager().getLaunchIntentForPackage(Constant.GO_Package);
                            startActivity(intent);
                        }else {
                            service.showVersionDialog(appInfoModel.getUrl(), "检测到新版本请及时更新","1.bug修复");
                        }
                    }else {
                        Intent intent=new Intent(this, WebviewActivity.class);
                        intent.putExtra("url",name);
                        startActivity(intent);
                    }
                }
            } catch (Exception e) {
                return;
            }

        }*/

    }


}
