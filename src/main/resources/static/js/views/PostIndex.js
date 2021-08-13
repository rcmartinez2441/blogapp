export default function PostIndex(props) {
	return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
        	<form id="post-form">
        		<input type="text" placeholder="id">
        		<input type="text" placeholder="new Title">
        		<input type="text" placeholder="new Content">
        		<button id="post-submit" type="submit"> Submit </button>
			</form>
<!--         Make a  form here, forms from HTML amd will have a click event and listen to that id, the event fires off, now  -->
            <div>
                ${props.posts.map(post => `<h3>${post.title}</h3>`).join('')}   
            </div>
        </main>
    `;
}

function test (){
	console.log('testing if this works')
}

