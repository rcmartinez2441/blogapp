import fetchData from "../fetchData.js";
import createView from "../createView.js";

export default function PostIndex(props) {
	return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
        	<form id="post-form">
        		<input name="newPost-id" id="newPost-id" type="text" placeholder="ID"><br>
        		<input name="newPost-title" id="newPost-title" type="text" placeholder="Title"><br>
        		<textarea name="newPost-content" id="newPost-content" cols="30" rows="10" placeholder="[Enter Post Content Here]"></textarea><br>
        		<button id="newPost-submit" type="button"> Submit </button>
			</form>
<!--         Make a  form here, forms from HTML amd will have a click event and listen to that id, the event fires off, now  -->
            <div>
                ${props.posts.map(post => `
					<h3>${post.title}</h3>
					<h5>${post.content}</h5>
					<div><br></div>
				<!-- Add Edit/Delete Button -->
				`).join('')}   
            </div>
        </main>
    `;
}

export function PostEvents() {
	newPostSubmitClickEvent();
}

function newPostSubmitClickEvent(){

	$("#newPost-submit").click(function () {
		let url = "http://localhost:8080/api/posts"
		let request = {
			method: "POST",
			headers: {
				'Content-Type': 'application/json',
			},
			data: JSON.stringify({
				id: `${$('#newPost-id').val()}`,
				title: `${$('#newPost-title').val()}`,
				content: `${$('#newPost-content').val()}`
			})
		};

		$.ajax(url, request).done(
			createView("/posts")
		)

	});

}

function postEditClickEvent (){
}

function postDeleteClickEvent(){

}

