<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<title>news web </title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>뉴스 목록</h2>
		<hr>
		<ul class="list-group">
			<c:forEach var="news" items="${newslist} varStatus="status">
				<li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
				<a href="news.nhn?action=getNews&aid=${news.aid}" class="text-decoration-none">[${status.count}] ${news.title},${nes.date}</a>
				<a href="news.nhn?action=deleteNews&aid=${news.aid}"><span class="badge bg-secondary">&times;</span></a>
				</li>
			</c:forEach>
		</ul>
		<hr>
		<c:if test="${error != null}">				
			<div class="alert alert-danger alert-dismissible fade show mt-3">
			에러발생: ${error}
			<button type ="button" class="btn-close" data-bs-dismiss="alert"></button>
		</div>
		</c:if>
		<button class="btn btn-outline-info mb-3" type ="button" data-bs-toggle="collapse" data-bs-target="#addForm" aria-expanded="false" aria-controls="addForm">뉴스등록</button>
		
		<div class="collapse" id="addForm">
			<div Class="card card-body">
			 <form method="post" action="/jhbook/news.nhn?action=addNews" enctype="multipart/form-data">
			 <label class="form-label">제목</label>
			 <input type="text" name="title" class="form-control">
			 <label class="form-label">이미지</label>
			  <input type="file" name="file" class="form-control">
			 <label class="form-label">기사 내용</label>
			 <textarea cols="50" rows="5" name="content" class="form-control"></textarea>
			 <button type="submit" class="btn btn-success" mt-3">저장</button>
			 </form>
			</div>
		</div>
</div>
</body>
</html>