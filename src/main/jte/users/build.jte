@param String name
@param String email
@param String problem = null

@template.layout.main(
    head = @`
        <meta charset="utf-8">
        <title>Registration</title>
    `,
    body = @`
        <form action ="/users" method="post">
            <div>
                <label>
                    Name
                    <input type="text" required name="name" value="${name}">
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email" value="${email}">
                </label>
            </div>
            <div>
                <label>
                    Password
                    <input type="password" required name="password">
                </label>
            </div>
            <div>
                <label>
                    Password confirmation
                    <input type="password" required name="passwordConfirmation">
                </label>
            </div>
            <input type="submit" value="Register">
        </form>

        @if (problem != null)
            <dialog open>
                <p>${problem}</p>
                <form method="dialog">
                    <button formnovalidate>OK</button>
                </form>
            </dialog>
        @endif
    `
)

