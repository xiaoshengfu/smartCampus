package com.sdkdjn.smartcampus.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sdkdjn.smartcampus.utils.SmartCampusUtils;
import com.sdkdjn.smartcampus.wechat.util.CheckUtil;
import com.sdkdjn.smartcampus.wechat.util.MessageUtil;
import com.sdkdjn.smartcampus.wechat.util.TulingApiProcess;

@Controller
@Scope("prototype")
public class WeChatAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String WeChat() throws IOException {
		System.out.println(1);
		HttpServletRequest request = SmartCampusUtils.getRequest();
		HttpServletResponse response = SmartCampusUtils.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		// System.out.println(method);
		if (method.equals("GET")) {
			String signature = request.getParameter("signature");
			// System.out.println(signature);
			String timestamp = request.getParameter("timestamp");
			// System.out.println(timestamp);
			String nonce = request.getParameter("nonce");
			// System.out.println(nonce);
			String echostr = request.getParameter("echostr");
			// System.out.println(echostr);
			if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
			}
		} else if (method.equals("POST")) {
			PrintWriter out = response.getWriter();
			try {
				Map<String, String> map = MessageUtil.xmlToMap(request);
				String fromUserName = map.get("FromUserName");
				String toUserName = map.get("ToUserName");
				String msgType = map.get("MsgType");
				String content = map.get("Content");
				String message = null;
				if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
					if ("1".equals(content)) {
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
					} else if ("2".equals(content)) {
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
					} else if ("?".equals(content) || "？".equals(content)) {
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
					} else {
						System.out.println("1");
						message = MessageUtil.initText(toUserName, fromUserName,
								TulingApiProcess.getTulingResult(content));
					}
				} else if (MessageUtil.MESSAGE_EVNET.equals(msgType)) {
					String eventType = map.get("Event");
					if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
						message = MessageUtil.initNewsMessageFor33(toUserName, fromUserName);
					} else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
					} else if (MessageUtil.MESSAGE_VIEW.equals(eventType)) {
						String url = map.get("EventKey");
						message = MessageUtil.initText(toUserName, fromUserName, url);
					} else if (MessageUtil.MESSAGE_SCANCODE.equals(eventType)) {
						String key = map.get("EventKey");
						message = MessageUtil.initText(toUserName, fromUserName, key);
					}
				} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {
					String label = map.get("Label");
					message = MessageUtil.initText(toUserName, fromUserName, label);
				}
				// System.out.println(1);
				System.out.println(message);

				out.print(message);
			} catch (DocumentException e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		} else {
			System.out.println(2);
		}
		return null;
	}

}
