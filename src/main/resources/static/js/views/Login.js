export default function Login(props) {
    return `
<head>
    <meta charset="UTF-8"/>
    <title>Log In</title>
</head>
<main class="container border shadow">
<h1>Log In</h1>

<form id="login-form">
    <label for="username">Email</label>
    <input id="username" name="username" type="text"/>
    <label for="password">Password</label>
    <input id="password" name="password" type="password"/>
    <input id="login-btn" type="submit" value="Log In"/>
</form>
</main>`;

}


