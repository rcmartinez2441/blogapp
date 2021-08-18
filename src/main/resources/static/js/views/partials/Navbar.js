export default function Navbar(props) {
    return `
        <nav>
            <a href="/" data-link class="pr-3">Home</a>
            <a href="/posts" data-link class="pr-1">Posts</a>
            <a href="/about" data-link class="pr-1">About</a>
            <a href="/login" data-link class="pr-1">Login</a>
            <a href="/users" data-link class="pr-1">Register</a>
            <a href="/search" data-link class="pr-1">Search</a>
        </nav>
        <br>
    `;
}