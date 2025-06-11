@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

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
                    <input type="text" required name="email" value="${email}">
                </label>
            </div>
            <div>
                <label>
                    Password
                    <input type="text" required name="password">
                </label>
            </div>
            <div>
                <label>
                    Password confirmation
                    <input type="text" required name="passwordConfirmation">
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

