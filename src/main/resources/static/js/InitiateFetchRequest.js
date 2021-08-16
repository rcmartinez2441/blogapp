import createView from "./createView.js";

export function initiateFetchRequest(url, methodType, requestBody) {
	fetch(url, {
		method: methodType,
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(requestBody)
	}).then( response => {
		console.log(response.status)
		createView("/")
	})
}