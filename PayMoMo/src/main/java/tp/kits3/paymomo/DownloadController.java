package tp.kits3.paymomo;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {

	private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String callDownload() {
		return "download";
	}
	
	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	public ModelAndView callapiMomo() {
		
		ModelAndView mav = new ModelAndView();
		
		String partnerCode = "MOMOUEKG20200824";
		String accessKey = "9Q97F7BczH1re1Je";
		int requestId = (int) new Date().getTime();
		String amount = "10000";
		int orderId = (int) new Date().getTime();
		String orderInfo = "Payment";
		String returnUrl = "https://google.com.vn";
		String notifyUrl = "https://google.com.vn";
		String extraData = "Pay";

		String key = "KjNXK6Kg1pyzJaSxnrV2aLaGY8PjUGqO";
		String data = "partnerCode : " + partnerCode + ", accessKey : " + accessKey + ", requestId : " + requestId + ", amount : "
				+ amount + ", orderId : " + orderId + ", orderInfo : " + orderInfo + ", returnUrl : " + returnUrl + ", notifyUrl : "
				+ notifyUrl + ", extraData : " + extraData;
		String signature = "";
		try {
			signature = HMAC.encode(key, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String momo = data + ", signature : " + signature;
		System.out.println("Signature: " + signature);

		JSONObject json = new JSONObject();

		try {
			json.put("partnerCode", partnerCode);
			json.put("accessKey", accessKey);
			json.put("requestId", requestId);
			json.put("amount", amount);
			json.put("orderId", orderId);
			json.put("orderInfo", orderInfo);
			json.put("returnUrl", returnUrl);
			json.put("notifyUrl", notifyUrl);
			json.put("extraData", extraData);
			json.put("signature", signature);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mav.addObject("momo", json);
		mav.setViewName("callmomo");
		return mav;
	}

}
