package com.thankjava.wqq.core.request.http;

import com.thankjava.toolkit3d.http.async.consts.HeaderName;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.entity.Headers;
import com.thankjava.toolkit3d.http.async.entity.Parameters;
import com.thankjava.toolkit3d.http.async.entity.RequestParams;
import com.thankjava.toolkit3d.http.async.entity.ResponseParams;
import com.thankjava.wqq.consts.ConstsParams;
import com.thankjava.wqq.consts.RequestUrls;
import com.thankjava.wqq.entity.msg.SendMsg;
import com.thankjava.wqq.extend.CallBackListener;
import com.thankjava.wqq.extend.ListenerAction;

import com.alibaba.fastjson.JSONObject;

public class SendDiscuMsg2 extends BaseHttpService{

	private Object param;
	
	public SendDiscuMsg2(Object param) {
		this.param = param;
	}
	
	@Override
	public ResponseParams doRequest(CallBackListener listener) {
		if(listener != null){
			ListenerAction listenerAction = new ListenerAction();
			listenerAction.setData(asyncHttpClient.syncRequestWithSession(buildRequestParams()));
			listener.onListener(listenerAction);
			return null;
		}else{
			return asyncHttpClient.syncRequestWithSession(buildRequestParams());
		}
	}

	@Override
	protected RequestParams buildRequestParams() {
		SendMsg sendMsg = (SendMsg)param;
		JSONObject jsonObject = new JSONObject(); 
		jsonObject.put("did", sendMsg.getDid()); //
		jsonObject.put("content", sendMsg.getContent().toSendMsg()); //
		jsonObject.put("face", 546); // 这个其实没啥用
		jsonObject.put("clientid", ConstsParams.CLIENT_ID);
		jsonObject.put("msg_id", msgId.incrementAndGet());
		jsonObject.put("psessionid", session.getPsessionid());
		Parameters params = new Parameters("r", jsonObject.toJSONString());
		Headers headers = new Headers(HeaderName.referer.name, RequestUrls.referer_about_msg.url);
		return new RequestParams(RequestUrls.send_discu_msg2.url, HttpMethod.post, params, headers);
	}

}
