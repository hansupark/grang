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
								<div class = "portlet-body chat-widget" style="overflow-y: auto; width : auto; height : 300px;">
									<div class = "row">
										<div class = "col-lg-12">
											<p class = "text-center text-muted small">2018 07 20</p>
										</div>
									</div>
									<div class = "row">
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
									</div>
									<hr>
									<div class = "row">
										<div class = "col-lg-12">
											<div class = "media">
												<a class = "pull-left" href = "#">
													<!-- <img class = "media-object img-circle" src = "images/1-1.jpg"> -->
												</a>
												<div class = "media-body">
													<h4 class = "media-heading">박정수
														<span class = "small pull-right">오전 12:35</span>
													</h4>
												</div>
												<p>안녕하세요 박정수입니다.</p>
											</div>
										</div>
									</div>					
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
												<button type = "button" class = "btn btn-default pull right" onclick = "submitFunction()">전송</button>
												<div class="clearfix"></div>
											</div>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>			
	</div>
</body>
</html>