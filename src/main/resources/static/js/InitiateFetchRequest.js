import createView from "./createView.js";
import {getHeaders} from "./auth.js";

export function initiateFetchRequest(url, methodType, requestBody) {
	fetch(url, {
		method: methodType,
		headers: getHeaders(),
		body: JSON.stringify(requestBody)
	}).then( response => {
		console.log(response.status)
		createView("/")
	})
}