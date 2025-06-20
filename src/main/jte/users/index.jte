@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.main(
    page = page,
    head = @`
        <meta charset="utf-8">
        <title>Users</title>
    `,
    body = @`
        <h1>Users</h1>
        <h3>Search by name or email</h3>
        <form>
            <input type="search" name="term" value="${page.getTerm()}">
            <input type="submit" value="Search">
        </form>
        @if(page.getUsers().isEmpty())
            <p>No users yet</p>
        @else
            @for(var user : page.getUsers())
                <div>
                    <h3><a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a></h3>
                    <p>${user.getEmail()}</p>
                </div>
            @endfor
        @endif
    `
)