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
				<input id="search" name="search" type="text" placeholder="Search Here">
				<select name="findBy" id="findBy">
					<option value="Id">ID</option>
					<option value="Email">EMAIL</option>
					<option value="Username">USERNAME</option>
				</select>
				<button id="searchBtn" type="button">Search</button>
			</div>
			<div id="userResult">
			</div>
		</body>	
	`
}

export function SearchEvent(){
	$('#searchBtn').click(function (){
		let input =  $('#search').val();
		let findBy = $('#findBy').val();

	})
}

function ajaxRequestSearch(searchInput, findBy){
	$.ajax({
		url: `http://localhost:8080/api/users/findBy${findBy}?`,
		type: "GET",
		contentType: "application/json",
		data: putObj,
		success: function (result) {
			console.log(result);
			createView("/posts");
		},
		error: function (result) {
			console.log("Caught error for ajax call");
			console.log(result);
			alert("There was an issue changing the post.  Please try again later.")
		}
	})
}

