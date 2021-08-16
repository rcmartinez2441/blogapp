import fetchData from "../fetchData.js";
import createView from "../createView.js";

export default function PostIndex(props) {
	return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
        	<form id="post-form" class="row">
        		<input class="col-3" name="newPost-id" id="newPost-id" data-id="newPost-id" type="text" placeholder="ID"><br>
        		<input class="col-3" name="newPost-title" id="newPost-title" type="text" placeholder="Title"><br>
        		<textarea class="col-9" name="newPost-content" id="newPost-content" cols="50" rows="5" placeholder="[Enter Post Content Here]"></textarea><br>
        		<button class="col-3" id="newPost-submit" type="button"> Submit </button>
			</form>
<!--         Make a  form here, forms from HTML amd will have a click event and listen to that id, the event fires off, now  -->
            <div>
                ${props.posts.map(post => `
					<div class="post">
						<h3 class="edit-title" contenteditable="false">${post.title}</h3>
						<h6 class="edit-body" contenteditable="false">${post.content}</h6>
						<button type="button" data-id=${post.id} class="editPost-btn" >Edit</button>
						<button type="button" class="deletePost-btn" data-id=${post.id}>Delete</button>
					</div>
				<!-- Add Edit/Delete Button -->
				`).join('')}   
            </div>
        </main>
    `;
}

export function PostEvents() {
	newPostSubmitClickEvent();
	postEditClickEvent();
	postDeleteClickEvent();
}

function newPostSubmitClickEvent() {
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

function postEditClickEvent() {
	$('.editPost-btn').click(function () {
		let editable = $(this).siblings('.edit-title').attr("contenteditable");
		if ( editable === "false" ) {
			$(this).siblings(".edit-title, .edit-body").attr("contenteditable", true).css({
				'border': "solid 1px black"
			})
			$(this).text('Save')

		} else {

			$(this).siblings(".edit-title, .edit-body").attr("contenteditable", false)
			let request = {
				'id':$(this).attr('data-id'),
				'title':$(this).siblings('.edit-title').text(),
				'content': $(this).siblings('.edit-body').text()
			}
			submitEditEvent(request)
			$(this).text('Edit')
		}

	})
}

function submitEditEvent(requestBody) {
	fetch(`http://localhost:8080/api/posts/${requestBody.id}`, {
		method: "PUT",
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(requestBody)
	}).then(response => {
		console.log(response)
		createView("/posts")
	}).catch(error => {
		console.log(error)
	})
}

function postDeleteClickEvent() {
	$('.deletePost-btn').click(function () {
		if( confirm("are you sure?") ){
			let id = $(this).attr('data-id')
			submitDeleteEvent(id)
		}
	});
}

function submitDeleteEvent(id){
	fetch(`http://localhost:8080/api/posts/${id}`, {
		method: "DELETE",
		headers: {
			'Content-Type': 'application/json',
		},
	}).then(response => {
		console.log(response)
		createView("/posts")
	}).catch(error => {
		console.log(error)
	})
}

function fetchRequest(body='', id){

}



