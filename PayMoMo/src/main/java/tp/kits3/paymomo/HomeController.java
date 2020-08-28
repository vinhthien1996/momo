package tp.kits3.paymomo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/momo", method = RequestMethod.GET)
	public String momoGet() {
		return "momo";
	}

	@RequestMapping(value = "/momo", method = RequestMethod.POST)
	public String momoPost() {
		String partnerCode = "MOMOUEKG20200824";
		String accessKey = "9Q97F7BczH1re1Je";
		String requestId = String.valueOf((int) new Date().getTime());
		String amount = "10000";
		String orderId = String.valueOf((int) new Date().getTime());
		String orderInfo = "Payment";
		String returnUrl = "http://localhost:8080/paymomo/payment/success";
		String notifyUrl = "https://google.com.vn";
		String extraData = "OK";

		String secretKey = "KjNXK6Kg1pyzJaSxnrV2aLaGY8PjUGqO";
		String data = "partnerCode=" + partnerCode + "&accessKey=" + accessKey + "&requestId=" + requestId + "&amount=" + amount + "&orderId=" + orderId + "&orderInfo=" + orderInfo + "&returnUrl=" + returnUrl + "&notifyUrl=" + notifyUrl + "&extraData=" + extraData;
		String signature = "";
		try {
			signature = HMAC.encode(secretKey, "partnerCode=" + partnerCode + "&accessKey=" + accessKey + "&requestId=" + requestId + "&amount=" + amount + "&orderId=" + orderId + "&orderInfo=" + orderInfo + "&returnUrl=" + returnUrl + "&notifyUrl=" + notifyUrl + "&extraData=" + extraData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(signature);
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
			json.put("requestType", "captureMoMoWallet");
			json.put("signature", signature);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			URL url = new URL("https://test-payment.momo.vn/gw_payment/transactionProcessor");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(json.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject jsonObj = new JSONObject(sb.toString());
			System.out.println(jsonObj.getString("payUrl"));
			in.close();
			return "redirect:" + jsonObj.getString("payUrl");
		} catch (Exception e) {
			System.out.println("\nError while calling Crunchify REST Service");
			System.out.println(e);
		}

		return "momo";
	}
	
	@RequestMapping(value = "/payment/success", method = RequestMethod.GET)
	@ResponseBody
	public String addMoney(HttpServletRequest req) {
		String partnerCode = req.getParameter("partnerCode");
		String accessKey = req.getParameter("accessKey");
		String responseTime = req.getParameter("responseTime");
		String orderInfo = req.getParameter("orderInfo");
		String amount = req.getParameter("amount");
		System.out.println(partnerCode);
		System.out.println(accessKey);
		if(partnerCode.equalsIgnoreCase("MOMOUEKG20200824") && accessKey.equalsIgnoreCase("9Q97F7BczH1re1Je")) {
			System.out.println("Thanh toan thanh cong!");
			System.out.println("Thong tin: " + orderInfo);
			System.out.println("Tai khoan: " + amount);
		} else {
			System.out.println("OK");
		}
		return "OK";
	}
}
