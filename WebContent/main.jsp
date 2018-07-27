<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name = "viewport" content = "witdh=device-width, initial-scale = 1">
	<link rel = "stylesheet" href = "css/bootstrap.css">
	<link rel = "stylesheet" href = "css/custom.css">
	<title>AJAX JSP 실시간 채팅 TEST</title>
	<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src = "js/bootstrap.js"></script>
	<script type = "text/javascript">
		function submitFunction()
		{
			var chatName = $("#chatName").val(); // id가 chatName인 값을 가져옴
			var chatContent = $("#chatContent").val();
			$.ajax
			({
				type : "POST",
				url : "./chatSubmitServlet",
				data : {
					chatName : chatName, //chatName이라는 변수에 위의 chatName의 값을 집어넣음
					chatContent : chatContent
				},
				success : function(result){
					if(result == 1)
						{
							autoClosingAlert("#successMessage",2000); //div의 id값이 successMessage의 안의 내용을 가져옴 
						}
					else if (result == 0)
						{
							autoClosingAlert("#dangerMessage",2000);
						}
					else
						{
							autoClosingAlert("#warningMessage",2000);
						}
				}
				
			});
			$('#chatContent').val('');
		}
		function autoClosingAlert(selector, delay)
		{
			var alert = $(selector).alert();
			alert.show();
			window.setTimeout(function(){alert.hide()},delay);
		}
		function chatListFunction(type)
		{
			$.ajax
			({
				type : "POST",
				url : "./chatListServlet",
				data : {
					listType : type,					
				},
				success : function(data){
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for(var i = 0 ; i < result.length ; i++)
						{
						addChat(result[i][0].value, result[i][1].value, result[i][2].value);
						}
				}				
			});
		}
		function addChat(chatName,chatContent,chatTime)
		{
			$('#chatList').append(
				'<div class = "row">' + 
				'<div class = "col-lg-12">' + 
				'<div class = "media">' + 
				'<a class = "pull-left" href = "#">' +
				'</a>' +
				'<div class = "media-body">' + 
				'<h4 class = "media-heading">' + 
				chatName + 
				'<span class = "small pull-right">' + 
				chatTime + 
				'</span>' + 
				'</h4>' + 
				'</div>' +
				'<p>' +
				chatContent + 
				'</p>' + 
				'</div>' + 
				'</div>' +
				'</div>' +
				'<hr>'				
			);
		}
		/* <div class = "row">
		<div class = "col-lg-12">
			<div class = "media">
				<a class = "pull-left" href = "#">
					<!-- <img class = "media-object img-circle" src = "images/1-1.jpg"> -->
				</a>
				<div class = "media-body">
					<h4 class = "media-heading">박한수
						<span class = "small pull-right">오전 12:35</span>
					</h4>
				</div>
				<p>안녕하세요 박한수입니다.</p>
			</div>
		</div>
	</div> */
	</script>
</head>
<body>
	<div class = "container">
		<div class = "container bootstrap snippet">
			<div class = "row">
				<div class = "col-xs-12">
					<div class = "portlet portlet-default">
						<div class = "portlet-heading">
								<div class ="portlet-title">
									<h4><i class = "fa fa-circle text-green"></i>실시간 채팅방</h4>
								</div>
								<div class = "clearfix"></div>
						</div>
						<div id = "chat" class = "panel-collapse collapse in">
								<div id ="chatList" class = "portlet-body chat-widget" style="overflow-y: auto; width : auto; height : 300px;">												
								
								</div>
								<div class = "portlet-footer">
										<div class = "row">
											<div class = "form-group col-xs-4">
												<input style ="height: 40px;" type = "text" id = "chatName" class = "form-control" placeholder = "이름" maxlength = "20">
											</div>
										</div>
										<div class = "row" style="height : 90px">
											<div class = "form-group col-xs-10">
												<textarea style = "height: 80px;" id = "chatContent" class = "form-control" placeholder = "메세지를 입력하세요" maxlength = "100">
												</textarea>
											</div>
											<div class = "form-group col-xs-2">
												<button type = "button" class = "btn btn-default pull right" onclick = "submitFunction();">전송</button>
												<div class="clearfix"></div>
											</div>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		<div class = "alert alert-success" id = "successMessage" style = "display : none;">
		<strong>메세지 전송 성공</strong>
		</div>
		<div class = "alert alert-danger" id = "dangerMessage" style = "display : none;">
			<strong>빈칸이 존재합니다.</strong>
		</div>
		<div class = "alert alert-warning" id = "warningMessage" style = "display : none;">
			<strong>데이터베이스 오류 </strong>
		</div>			
	</div>
	<button type = "button" class = "btn btn-default pull left" onclick="chatListFunction('today');">추가</button>
</body>
</html>