import Home from "./views/Home.js";
import PostIndex from "./views/PostIndex.js";
import About from "./views/About.js";
import Error404 from "./views/Error404.js";
import Loading from "./views/Loading.js";
import Login from "./views/Login.js";
import LoginEvent from "./auth.js";
import {PostEvents} from "./views/PostIndex.js";
import Register, {RegisterEvents} from "./views/Register.js";
import User, {SearchEvent} from "./views/User.js";

/**
 * Returns the route object for a specific route based on the given URI
 * @param URI
 * @returns {*}
 */
export default function router(URI) {
    const routes = {
        '/': {
            returnView: Home,
            state: {},
            uri: '/',
            title: 'Home',
        },
        '/login': {
            returnView: Login,
            state: {},
            uri: '/login',
            title: "Login",
            viewEvent: LoginEvent
        },
        '/posts': {
            returnView: PostIndex,
            state: {
                posts: '/api/posts',
                categories: '/api/categories'
            },
            uri: '/posts',
            title: 'All Posts',
            viewEvent: PostEvents
        },
        '/about': {
            returnView: About,
            state: {},
            uri: '/about',
            title: 'About',
        },
        '/error': {
            returnView: Error404,
            state: {},
            uri: location.pathname,
            title: ' ERROR',
        },
        '/loading': {
            returnView: Loading,
            state: {},
            uri: location.pathname,
            title: 'Loading...',
        },
        '/users': {
            returnView: Register,
            state: {
                users: '/api/users' /* "/getCurrentUser" after security implemented  */
            },
            uri: '/users',
            title: 'Users',
            viewEvent: RegisterEvents
        },
        '/search': {
            returnView: User,
            state: {
                users: '/api/users' /* "/getCurrentUser" after security implemented  */
            },
            uri: '/users',
            title: 'Search',
            viewEvent: SearchEvent
        }
    };

    return routes[URI];
}

