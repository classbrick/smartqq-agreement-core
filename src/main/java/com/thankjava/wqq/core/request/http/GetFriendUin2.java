package com.thankjava.wqq.core.request.http;

import com.thankjava.toolkit3d.http.async.consts.HeaderName;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.entity.Headers;
import com.thankjava.toolkit3d.http.async.entity.Parameters;
import com.thankjava.toolkit3d.http.async.entity.RequestParams;
import com.thankjava.toolkit3d.http.async.entity.ResponseParams;
import com.thankjava.wqq.consts.RequestUrls;
import com.thankjava.wqq.extend.CallBackListener;
import com.thankjava.wqq.extend.ListenerAction;

public class GetFriendUin2 extends BaseHttpService {

	private long uin;
	
	public GetFriendUin2(long uin){
		this.uin = uin;
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
		Parameters params = new Parameters("t", String.valueOf(System.currentTimeMillis() / 1000));
		params.append("tuin", String.valueOf(uin));
		params.append("type", "1");
		params.append("vfwebqq", session.getVfwebqq());
		Headers headers = new Headers(HeaderName.referer.name, RequestUrls.referer_getvfwebqq.url);
		return new RequestParams(
				RequestUrls.get_friend_uin2.url, 
				HttpMethod.get, 
				params,
				headers
			);
	}

}
