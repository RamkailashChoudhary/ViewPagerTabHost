/**
 * 
 */
package com.app.gco.webrequest;

import com.app.gco.delegates.BaseResponse;
import com.google.gson.Gson;

/**
 * @author new
 * 
 */
public class JsonParser {

	public static BaseResponse parseJson(String jsonString, Class t) {
		if (jsonString == null)
			return null;
		Gson gson = new Gson();

		BaseResponse response = gson.fromJson(jsonString, t);

		return response;

	}

}
