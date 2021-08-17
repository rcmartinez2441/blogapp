export default function User(props){
	return `
		<!DOCTYPE html>
		<html lang="en">
		<head>
			<meta charset="UTF-8"/>
			<title>Search</title>
		</head>
		<body>
			<div class="searchBar">
				<input type="text" placeholder="Search Here">
				<select name="findBy" id="findBy">
					<option value="id">ID</option>
					<option value="email">EMAIL</option>
					<option value="username">USERNAME</option>
				</select>
				<button id="searchBtn" type="button">Search</button>
			</div>
		</body>	
	`
}

export function SearchEvent(){
	console.log("Testing event function")
}

// $.ajax({
// 	url: `http://localhost:8080/api/posts/${postId}`,
// 	type: "PUT",
// 	contentType: "application/json",
// 	data: putObj,
// 	success: function (result) {
// 		console.log(result);
// 		createView("/posts");
// 	},
// 	error: function (result) {
// 		console.log("Caught error for ajax call");
// 		console.log(result);
// 		alert("There was an issue changing the post.  Please try again later.")
// 	}
// })