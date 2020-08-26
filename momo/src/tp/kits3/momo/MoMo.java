package tp.kits3.momo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/momo")
public class MoMo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String partnerCode = req.getParameter("partnerCode");
		String accessKey = req.getParameter("accessKey");
		int requestId = (int) new Date().getTime();
		String amount = req.getParameter("amount");
		int orderId = (int) new Date().getTime();
		String orderInfo = req.getParameter("orderInfo");
		String returnUrl = req.getParameter("returnUrl");
		String notifyUrl = req.getParameter("notifyUrl");
		String extraData = req.getParameter("extraData");
		
		String key = "KjNXK6Kg1pyzJaSxnrV2aLaGY8PjUGqO";
		String data = "partnerCode=" + partnerCode + "&accessKey=" + accessKey + "&requestId=" + requestId + "&amount=" + amount + "&orderId=" + orderId +"&orderInfo=" + orderInfo + "&returnUrl=" + returnUrl + "&notifyUrl=" + notifyUrl + "&extraData=" + extraData;
		String signature = "";
		try {
			signature = encode(key, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String momo = data + "&signature=" + signature;
		System.out.println(momo);
		
		
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
		
	}
	
	public String encode(String key, String data) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	     SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
	     sha256_HMAC.init(secret_key);
	     return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	}
	
}
