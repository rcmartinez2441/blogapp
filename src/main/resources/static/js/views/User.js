import createView from "../createView.js";
import {postDeleteClickEvent, postEditClickEvent} from "./PostIndex.js";

export default function User(props){
	return `
			<div class="container border shadow">
				<h1>Search User</h1>
				<div class="searchBar my-5">
					<input id="search" name="search" type="text" placeholder="Search Here">
					<select name="findBy" id="findBy">
						<option value="Email">EMAIL</option>
						<option value="Username">USERNAME</option>
						<option value="Category">CATEGORY</option>
					</select>
					<button id="searchBtn" type="button">Search</button>
				</div>
				<div id="userResult" class="mx-auto">
				</div>
			</div>
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
			return '/users/findByEmail?email=';
		case 'Username':
			return '/users/findByUsername?userName=';
		case 'Category':
			return '/categories?category='
	}
}

function ajaxRequestSearch(searchInput, urlEndPoint){
	console.log("Made it to Ajax Request")
	$.ajax({
		url: `http://localhost:8080/api/${urlEndPoint}${searchInput}`,
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
	userResult.slideUp( 300 ).delay( 800 ).fadeIn( 400 )
	userResult.html('');
	userResult.append(`
		<div class="main-body">
			<h2 class="my-4">Welcome Back, <span style="color:#7FC8A9">${data.username}</span></h2>
			<div class="text-end"><a href="#" class="mb-5" id="updatePassword">Update Password</a></div>
			<h2 class="my-3">Your Posts</h2>
		</div>
	`);

	data.posts.forEach(post => {
		userResult.append(`
			<div class="post row row-cols-1 border rounded mb-2 shadow p-2 mx-2">
				<h3 class="edit-title col" contenteditable="false">${post.title}</h3>
				<h6 class="edit-body col" contenteditable="false">${post.content}</h6>
				<div><em>Created By: ${data.username}</em></div>
				<button type="button" data-id=${post.id} class="editPost-btn btn btn-info col-2 m-1" >Edit</button>
				<button type="button" class="deletePost-btn btn btn-info col-2 m-1" data-id=${post.id}>Delete</button>
			</div>
		`)
	})

	postEditClickEvent();
	postDeleteClickEvent();
	updatePasswordEvent();
}

function updatePasswordEvent(){
	$('#updatePassword').one("click", function (){
		$(this).parent().append(`
			<div class="password-section mt-1">
				<label for="old-password">Old Password</label><br>
				<input class="mb-4" name="old-password" placeholder="Enter Old Password">
				<br>
				<label for="new-password">New Password</label><br>
				<input class="mb-4" name="new-password" placeholder="Enter New Password">
				<br>
				<label for="new-password2">Re-Enter New Password</label><br>
				<input class="mb-4" name="new-password2" placeholder="Enter New Password">
				<br>
				<button class="" id="submitPassword">Confirm</button>
			</div>
		`)
	})
}

