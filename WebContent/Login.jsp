<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="robots" content="noindex, nofollow" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>ログイン - SuyaSuya</title>
</head>
<body>
	<main>
	<h1>SuyaSuya</h1>
	<h2>睡眠記録システム</h2>
		<form action="/SuyaSuya/Login" method="post">
			<ul>
				<li>
					<label for="login-id">ID</label>
					<input type="text" name="id" id="login-id" value="${input_id}">
				</li>
				<li>
					<label for="login-password">パスワード</label>
					<input type="password" name="password" id="login-password">
				</li>
				<li>
					<input type="submit" value="ログイン">
				</li>				
			</ul>
		</form>
	</main>
</body>
</html>