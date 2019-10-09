package com.sdkdjn.smartcampus.wechat.test;

import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.wechat.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
//			AccessToken token = WeixinUtil.getAccessToken();
//			System.out.println("票据"+token.getToken());
//			System.out.println("有效时间"+token.getExpiresIn());
			
			//String path = "D:/imooc.jpg";
			//String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			//System.out.println(mediaId);
			
			//String result = WeixinUtil.translate("my name is laobi");
			//String result = WeixinUtil.translateFull("");
			//System.out.println(result);
			
//			String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
//			int result = WeixinUtil.createMenu(token.getToken(), menu);
//			if (result==0) {
//				System.out.println("创建成功");
//			}else {
//				System.out.println("错误码："+result);
//			}
			//-----------------------------------
			
			SigninActivity activity = new SigninActivity();
			activity.setId("1");
			activity.setDuration(5);
			activity.setActivityType(1);
			System.out.println(WeixinUtil.getCodeTicket(activity).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
