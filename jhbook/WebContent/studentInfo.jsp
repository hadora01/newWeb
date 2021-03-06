<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 한글처리를 위해서  -->
<meta charset="UTF-8">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<title>학생정보 사이트 </title>
</head>

<body>
	<div class = "container bg-success text-white shadow mx-auto mt-5 p-5 w-75">

	<h3>학생정보 등록 </h3>
	<hr>
	<form method ="post" action ="/jhbook/Controller?action=insert" >
		<label>학번</label> <br>
		<input type ="text" name ="no"> <br>
		
		<label>이름</label> <br>
		<input type ="text" name ="name"><br>
		
		<label>학과</label> <br>
		<input type ="text"  name ="depart"><br>
		
		<label>메모</label> <br>
		<input type ="text"  name ="memo"><br>
		
		<hr>
		<input type ="submit"  class = "btn btn-warning " value ="등록" > 
		
		<input  type="button" class="btn btn-light" value="조회">
			
		</form>
	</div>

</body>
</html>