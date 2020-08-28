<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/forge/0.8.2/forge.all.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<input type="text" value="${ momo }" id="momo" readonly/>
		<div id="content"></div>
	</div>
	
	<script>
        $(document).ready(function(){
            var json = ${ momo };
            console.log(JSON.stringify(json));
            fetch("https://test-payment.momo.vn/gw_payment/transactionProcessor", {
                method: 'POST', 
                body: JSON.stringify(json), 
                headers:{
                    Accept: 'application/json'
                }
            })
            .then(res => res.json())
            .then((response) =>{
                console.log(response);
            } )
            .catch(error => console.error('Error:', error))
        });
    </script>
</body>
</html>