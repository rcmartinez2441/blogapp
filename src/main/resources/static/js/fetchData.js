/**
 * Given an object containing all the required data for a given page, fetch all the needed data and return it as properties to pass to a view.
 * @param state
 * @param request
 * @returns {Promise<{}>}
 */
export default function fetchData(state, request) { //current state (status) of a record/object
	const promises = [];
	//TODO: this needs to be moved to a prop file or env variable
	const baseUri = "http://localhost:8080";

	//For each key (key:value) inside the 'state' property
	//Ex. Object.keys('/posts'.state) => Will give you an array will list of property names
	for (let pieceOfState of Object.keys(state)) {
		console.log(baseUri + state[pieceOfState]); //Ex state[posts] = 'api/posts/'
		promises.push(
			fetch(baseUri + state[pieceOfState], request) //Request is everything in {method, headers, body} for fetch request
				.then(function (res) {
						return res.json();
				}));
	}
	return Promise.all(promises).then(propsData => {
		const props = {};
		Object.keys(state).forEach((key, index) => {
			props[key] = propsData[index];
		});
		return props;
	});
}
