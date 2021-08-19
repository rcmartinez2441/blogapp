import {initiateFetchRequest} from "../InitiateFetchRequest.js";

export default function Register(props) {
	return `
<div class="container border shadow">
	<head>
		<meta charset="UTF-8"/>
		<title>Log In</title>
	</head>
	<main>
	<h1>Register Account</h1>
	
	<form id="login-form">
		<label for="email">Email</label>
		<input id="email" name="username" type="email"/>
		
		<label for="username">Username</label>
		<input id="username" name="username" type="text"/>
	
		
		<label for="password">Password</label>
		<input id="password" name="password" type="password"/>
		
		<button id="newUser-submit" type="button">Submit</button>
	</form>
	
	</main>
</div>
`;

}

export function RegisterEvents (){
	console.log("Testing Function")
	$('#newUser-submit').click(function (){
		let request = {
			email:  $(this).siblings('#email').val(),
			username: $(this).siblings('#username').val(),
			password: $(this).siblings('#password').val()
		}

		initiateFetchRequest("http://localhost:8080/api/users", 'POST', request )

	})
}

// sample request = {
// 	method: 'POST'
// 	headers: {
// 		'Accept': 'application/json',
// 		'Document-Type': 'application/json'
// 	}
// 	body: {
//
// 	}
// }


