@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

@template.layout.main(
    page = page,
    head = @`
        <meta charset="utf-8">
        <title>Creating user</title>
    `,
    body = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action ="${NamedRoutes.usersPath()}" method="post">
            <div>
                <label>
                    Name
                    <input type="text" required name="name" value="${page.getName()}">
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email" value="${page.getEmail()}">
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
    `
)

