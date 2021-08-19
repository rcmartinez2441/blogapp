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
		//Will check the amount of properties in "state" located in route and will do a fetch request per each property - ex: { posts: '/api/posts' }
		promises.push(
			fetch(baseUri + state[pieceOfState], request) //Request is everything in {method, headers, body} for fetch request
				.then(function (res) {
						return res.json();
				}));
	}
	//THis is saying that it will NOT render or return anything until ALL fetch requests are completed and return a response
	//propsData is an array or objects
	return Promise.all(promises).then(propsData => {
		const props = {};
		//It will then create new properties into 'props' name them the name written in router.state and then assign the object from propsData[] at the current index in loop
		Object.keys(state).forEach((key, index) => {
			props[key] = propsData[index];
		});
		return props;
	});
}
