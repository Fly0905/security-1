/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;

/**
 * @author zhailiang
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		return true;
	}

	/**
	 * @Title: QQ的用户信息，封装到ConnectionValues
	 * @MethodName:  setConnectionValues
	 * @param api
	 * @param values
	 * @Return void
	 * @Exception
	 * @Description:
	 *
	 * @author: 王延飞
	 * @date:  2019-06-10 9:05
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {

		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		//do noting
	}

}
