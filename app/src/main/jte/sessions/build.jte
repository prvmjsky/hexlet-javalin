@import org.example.hexlet.util.NamedRoutes

@template.layout.main(
    head = @`
        <meta charset="utf-8">
        <title>Authorization</title>
    `,
    body = @`
        <form action="${NamedRoutes.sessionsPath()}" method="post">
            <div>
                <input type="text" placeholder="Nickname" name="nickname" required>
            </div>
            <div>
                <input type="password" placeholder="Password" name="password" required>
            </div>
            <div>
                <input type="submit">
            </div>
        </form>
    `
)
