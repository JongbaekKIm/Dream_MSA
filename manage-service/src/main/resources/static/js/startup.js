function register() {
	var proName = $('#proName').val();
	var proLimit = $('#proLimit').val();
	var description = $('#description').val();
	var term = $('#term').val();
	console.log(proName);
	console.log(term);
	
	var arr = {
		"proName": proName,
		"proLimit": proLimit,
		"description": description,
		"term": term
	};
	
	console.log(arr);

	$.ajax({
		type: 'POST',
		url: '/manage/register',
		data: JSON.stringify(arr),
		contentType: "application/json; charset=utf-8;",
		dataType: "text",
		success: function(data) {
			alert("상품이 등록되었습니다. 메인화면으로 돌아갑니다.");
			location.href = "/menu/list";
		},
		error: function(request, status, error) {
			alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		}
	});
}