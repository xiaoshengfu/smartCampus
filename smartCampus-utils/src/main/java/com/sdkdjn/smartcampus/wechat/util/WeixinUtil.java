package com.sdkdjn.smartcampus.wechat.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sdkdjn.smartcampus.entity.SigninActivity;
import com.sdkdjn.smartcampus.wechat.menu.Button;
import com.sdkdjn.smartcampus.wechat.menu.ClickButton;
import com.sdkdjn.smartcampus.wechat.menu.Menu;
import com.sdkdjn.smartcampus.wechat.menu.ViewButton;
import com.sdkdjn.smartcampus.wechat.trans.Data;
import com.sdkdjn.smartcampus.wechat.trans.Parts;
import com.sdkdjn.smartcampus.wechat.trans.Symbols;
import com.sdkdjn.smartcampus.wechat.trans.TransResult;
import com.sdkdjn.smartcampus.wechat.vo.AccessToken;
import com.sdkdjn.smartcampus.wechat.vo.CodeTicket;
import com.sdkdjn.smartcampus.wechat.vo.UserInfo;

import net.sf.json.JSONObject;

/**
 * 微信工具类
 * 
 * @author Stephen
 *
 */
@SuppressWarnings({ "deprecation", "unused", "resource", "unchecked", "rawtypes" })
public class WeixinUtil {
	private static final String APPID = "wx5b5b63505a9930a9";
	private static final String APPSECRET = "d40b0a92c3b24e1c6131d2a63d9a0ebb";

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	private static final String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	private static final String GET_CODE_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST";

	private static final String GET_CODE_STRING = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

	/**
	 * 获取openid
	 */
	public static String getUrl(String url) {
		String result = null;
		try {
			// 根据地址获取请求
			HttpGet request = new HttpGet(url);
			// 获取当前客户端对象
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(request);

			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String getUrlZH(String method) {
		return getRequestURL(UrlEncodeUTF8(getURLAction(method)));
	}

	public static String getOpenId(String code) {
		String get_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=APPID"
				+ "&secret=SECRET&" + "code=CODE&grant_type=authorization_code";
		get_access_token_url = get_access_token_url.replace("APPID", "wx5b5b63505a9930a9");
		get_access_token_url = get_access_token_url.replace("SECRET", "d40b0a92c3b24e1c6131d2a63d9a0ebb");
		get_access_token_url = get_access_token_url.replace("CODE", code);
		String json = getUrl(get_access_token_url);
		JSONObject jsonObject = JSONObject.fromObject(json);
		String openid = jsonObject.getString("openid");
		return openid;
	}

	/**
	 * 输入方法判断跳转
	 */
	public static String getURLAction(String method) {
		return "http://160716c6.nat123.net/smartCampus-web/weXinAction_" + method + ".action";
	}

	/**
	 * 第三方授权服务,拼接requestURL
	 */
	public static String getRequestURL(String url) {
		String requesturl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5b5b63505a9930a9&redirect_uri="
				+ url + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		return requesturl;
	}

	/**
	 * URLnetencoding
	 */
	public static String UrlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url) throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpPost);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	public static JSONObject doPostStr(String url, String outStr) throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr, "UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}

	/**
	 * 文件上传
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken, String type)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if (!"image".equals(type)) {
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}

	/**
	 * 获取accessToken
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getAccessToken() throws ParseException, IOException {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}

	public static UserInfo getUserInfo(String token, String openid) throws ParseException, IOException {
		UserInfo userInfo = new UserInfo();
		String url = GET_USER_INFO.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			userInfo.setNickname(jsonObject.getString("nickname"));
		}
		return userInfo;
	}

	/**
	 * 组装菜单
	 * 
	 * @return
	 */
	public static Menu initMenu() {
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("基础菜单");
		button11.setType("click");
		button11.setKey("11");

		/*
		 * ViewButton button21 = new ViewButton(); button21.setName("view菜单");
		 * button21.setType("view"); button21.setUrl("http://www.imooc.com");
		 */

		Button button2 = new Button();
		ViewButton button21 = new ViewButton();
		button21.setName("物业报修");
		button21.setType("view");
		button21.setUrl(getRequestURL(UrlEncodeUTF8(getURLAction("button33"))));
		ViewButton button22 = new ViewButton();
		button22.setName("保修信息查询");
		button22.setType("view");
		button22.setUrl("http://www.baidu.com");
		ViewButton button23 = new ViewButton();
		button23.setName("上课签到");
		button23.setType("view");
		button23.setUrl(getRequestURL(UrlEncodeUTF8(getURLAction("button23"))));
		button2.setName("校园服务");
		button2.setSub_button(new Button[] { button21, button22, button23 });

		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");

		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");

		ViewButton button33 = new ViewButton();
		button33.setName("用户信息填写");
		button33.setType("view");
		button33.setUrl(getUrlZH("button33"));

		Button button = new Button();
		button.setName("其他功能");
		button.setSub_button(new Button[] { button31, button32, button33 });

		menu.setButton(new Button[] { button11, button2, button });
		return menu;
	}

	public static int createMenu(String token, String menu) throws ParseException, IOException {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	public static JSONObject queryMenu(String token) throws ParseException, IOException {
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}

	public static int deleteMenu(String token) throws ParseException, IOException {
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		int result = 0;
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	public static String translate(String source) throws ParseException, IOException {
		String url = "http://openapi.baidu.com/public/2.0/translate/dict/simple?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
		url = url.replace("KEYWORD", URLEncoder.encode(source, "UTF-8"));
		JSONObject jsonObject = doGetStr(url);
		String errno = jsonObject.getString("errno");
		Object obj = jsonObject.get("data");
		StringBuffer dst = new StringBuffer();
		if ("0".equals(errno) && !"[]".equals(obj.toString())) {
			TransResult transResult = (TransResult) JSONObject.toBean(jsonObject, TransResult.class);
			Data data = transResult.getData();
			Symbols symbols = data.getSymbols()[0];
			String phzh = symbols.getPh_zh() == null ? "" : "中文拼音：" + symbols.getPh_zh() + "\n";
			String phen = symbols.getPh_en() == null ? "" : "英式英标：" + symbols.getPh_en() + "\n";
			String pham = symbols.getPh_am() == null ? "" : "美式英标：" + symbols.getPh_am() + "\n";
			dst.append(phzh + phen + pham);

			Parts[] parts = symbols.getParts();
			String pat = null;
			for (Parts part : parts) {
				pat = (part.getPart() != null && !"".equals(part.getPart())) ? "[" + part.getPart() + "]" : "";
				String[] means = part.getMeans();
				dst.append(pat);
				for (String mean : means) {
					dst.append(mean + ";");
				}
			}
		} else {
			dst.append(translateFull(source));
		}
		return dst.toString();
	}

	public static String translateFull(String source) throws ParseException, IOException {
		String url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
		url = url.replace("KEYWORD", URLEncoder.encode(source, "UTF-8"));
		JSONObject jsonObject = doGetStr(url);
		StringBuffer dst = new StringBuffer();
		List<Map> list = (List<Map>) jsonObject.get("trans_result");
		for (Map map : list) {
			dst.append(map.get("dst"));
		}
		return dst.toString();
	}

	public static void main(String[] args) {
		System.out.println(getRequestURL(UrlEncodeUTF8(getURLAction("button21"))));
	}

	/**
	 * 获取临时二维码
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static CodeTicket getCodeTicket(SigninActivity signinActivity) throws ParseException, IOException {
		CodeTicket codeTicket = new CodeTicket();
		String tjsonString = "{\"expire_seconds\":" + signinActivity.getDuration() * 60
				+ ", \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""
				+ signinActivity.getId() + "_" + signinActivity.getActivityType() + "\"}}}";
		String url = GET_CODE_TICKET.replace("TOKENPOST", getAccessToken().getToken());
		JSONObject jsonObject = doPostStr(url, tjsonString);
		if (jsonObject != null) {
			codeTicket.setTicket(jsonObject.getString("ticket"));
			codeTicket.setExpire_seconds(jsonObject.getString("expire_seconds"));
			codeTicket.setUrl(jsonObject.getString("url"));
		}
		return codeTicket;
	}

}
