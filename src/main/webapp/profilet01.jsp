<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		
		
		

<!-- 유저 조회하기 -->	
<hr>
<div>
	<div class="search_block">
		<input placeholder="keyfield" />
		<button class=search_btn>조회</button>
		<button class=search_btn>삭제</button>
	</div>
	<div class="show_block" style="width:300px;height:300px;border:1px solid;overflow:auto;">
	
	</div>
</div>





<hr/>
<div class=msg>
${msg}
</div>

EL's Project PATH : ${pageContext.request.contextPath}<br/>
EL's Project ServerPort :${pageContext.request.serverPort}<br/>
<%
	int port = request.getServerPort();
%>

</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	const search_btn_el=document.querySelector('.search_btn');
	search_btn_el.addEventListener('click',function(){
		const projectPath='${pageContext.request.contextPath}';
		const ServerPort = '${pageContext.request.serverPort}';
		console.log("search_btn_el click..",projectPath);
		
		
		const show_block_el = document.querySelector('.show_block');
		//ajax , fetch , promise , axios 선택해서 비동기 요청
		//axios Cdn 연결이후에 작업하세요(https://cdnjs.com/libraries/axios)	
		axios.get("http://localhost:"+ ServerPort + projectPath +"/profile/search.do")
		.then(response=>{ 
			console.log("success!",response.data); 
			const list = response.data;
			
		
			
			list.forEach((dto) => {
			
				console.log('dto',dto);
				const dto_el = document.createElement('div');
				dto_el.classList.add("item");
				
				dto_el.innerHTML+="<span>"+dto.userid+"</span> ";
				dto_el.innerHTML+="<span>"+dto.pw+"</span> ";
				dto_el.innerHTML+="<span>"+dto.addr+"</span> ";
				dto_el.innerHTML+="<span>"+dto.role+"</span><br/>";
				show_block_el.appendChild(dto_el);
				
			})
			
			
		})
		.catch(error=>{ console.log("fail.."); })	//실패시 처리로직
		
	})
	</script>



</body>
</html>