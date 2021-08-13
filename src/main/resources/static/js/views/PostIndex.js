import fetchData from "../fetchData.js";
import createView from "../createView.js";
import addLoginEvent from "../auth";

export default function PostIndex(props) {
	return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
        	<form id="post-form">
        		<input name="newPost-id" id="newPost-id" type="text" placeholder="ID"><br>
        		<input name="newPost-title" id="newPost-title" type="text" placeholder="new Title"><br>
        		<textarea name="newPost-content" id="newPost-content" cols="30" rows="10"></textarea><br>
        		<button id="newPost-submit" type="button"> Submit </button>
			</form>
<!--         Make a  form here, forms from HTML amd will have a click event and listen to that id, the event fires off, now  -->
            <div>
                ${props.posts.map(post => `
					<h3>${post.title}</h3>
					<h4>${post.content}</h4>
					<div></div>
				<!-- Add Edit/Delete Button -->
				`).join('')}   
            </div>
        </main>
    `;
}

export function PostEvent(){
	$("#newPost-submit").click(function (){
		let request = {
			method: "POST",
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				'id': `${$('#newPost-ID').val()}`,
				'title': `${$('#newPost-title').val()}`,
				'content': `${$('#newPost-content').val()}`
			})
		};

		fetch('http://localhost:8080/posts', request)
	})
}

