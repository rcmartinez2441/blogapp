export default function Navbar(props) {
    return `
<!--        <nav>-->
<!--            <a href="/" data-link class="pr-3">Home</a>-->
<!--            <a href="/posts" data-link class="pr-1">Posts</a>-->
<!--            <a href="/about" data-link class="pr-1">About</a>-->
<!--            <a href="/login" data-link class="pr-1">Login</a>-->
<!--            <a href="/users" data-link class="pr-1">Register</a>-->
<!--            <a href="/search" data-link class="pr-1">Search</a>-->
<!--        </nav>-->
        <nav class="navbar navbar-expand sticky-top navbar-dark bg-dark">
        <a class="navbar-brand p-1" href="#">BlogApp</a>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a href="/" data-link class="p-2" style="text-decoration:none; color:white">Home</a>
            </li>
            <li class="nav-item active">
                <a href="/posts" data-link class="p-2" style="text-decoration:none; color:white">Posts</a>
            </li>
            <li class="nav-item">
                <a href="/about" data-link class="p-2" style="text-decoration:none; color:white">About</a>
            </li>
            <li class="nav-item">
                <a href="/login" data-link class="p-2" style="text-decoration:none; color:white">Login</a>
            </li>
            <li class="nav-item">
                <a href="/users" data-link class="p-2" style="text-decoration:none; color:white">Register</a>
            </li>
            <li class="nav-item">
                <a href="/search" data-link class="p-2" style="text-decoration:none; color:white">Search</a>
            </li>
        </ul>
</nav>
        <div></div>
    `;
}

