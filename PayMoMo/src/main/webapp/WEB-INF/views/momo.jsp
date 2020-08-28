<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/paymomo/momo" method="POST" enctype="application/x-www-form-urlencoded">
        partnerCode: <input type="text" name="partnerCode" value="MOMOUEKG20200824" /><br/>
        accessKey: <input type="text" name="accessKey" value="9Q97F7BczH1re1Je" /><br/>
        requestId: <input type="text" name="requestId" /><br/>
        amount: <input type="text" name="amount" /><br/>
        orderId: <input type="text" name="orderId" /><br/>
        orderInfo: <input type="text" name="orderInfo" /><br/>
        returnUrl: <input type="text" name="returnUrl" /><br/>
        notifyUrl: <input type="text" name="notifyUrl" /><br/>
        extraData: <input type="text" name="extraData" /><br/>
        <input type="submit" value="SUBMIT" />
    </form>
</body>
</html>