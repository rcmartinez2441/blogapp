import createView from "../createView.js";
import {postDeleteClickEvent, postEditClickEvent} from "./PostIndex.js";

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
						<option value="Email">EMAIL</option>
						<option value="Username">USERNAME</option>
					</select>
					<button id="searchBtn" type="button">Search</button>
				</div>
				<div id="userResult">
				</div>
			</body>
		</html>	
	`
}

export function SearchEvent(){
	$('#searchBtn').click(function (){
		let searchInput =  $('#search').val();
		let searchType = $('#findBy').val();

		ajaxRequestSearch(searchInput, urlEndPoint(searchType))

	})
}

function urlEndPoint( chosenSearchType ){
	switch (chosenSearchType){
		case 'Email':
			return 'findByEmail?email='
		case 'Username':
			return 'findByUsername?userName='
	}
}

function ajaxRequestSearch(searchInput, urlEndPoint){
	console.log("Made it to Ajax Request")
	$.ajax({
		url: `http://localhost:8080/api/users/${urlEndPoint}${searchInput}`,
		type: "GET",
		contentType: "application/json",
		success: function (result) {
			console.log(result);
			displayResultsInDOM(result)
			// createView("/search");
		},
		error: function (result) {
			console.log("Caught error for ajax call");
			console.log(result);
			alert("There was an issue changing the post.  Please try again later.")
		}
	})
}

function displayResultsInDOM(data){
	let userResult = $('#userResult')
	userResult.html('');
	userResult.append(`
		<h2>Welcome Back, ${data.username}</h2>
		<div><a href="#" id="updatePassword">Update Password</a></div>
	`);

	data.posts.forEach(post => {
		userResult.append(`
			<div class="post">
				<h3 class="edit-title" contenteditable="false">${post.title}</h3>
				<h6 class="edit-body" contenteditable="false">${post.content}</h6>
				<div><em>Created By: ${data.username}</em></div>
				<button type="button" data-id=${post.id} class="editPost-btn" >Edit</button>
				<button type="button" class="deletePost-btn" data-id=${post.id}>Delete</button>
			</div>
		`)
	})

	postEditClickEvent();
	postDeleteClickEvent();
	updatePasswordEvent();
}

function updatePasswordEvent(){
	$('#updatePassword').click(function (){
		console.log('Click Event Works')
	})
}

