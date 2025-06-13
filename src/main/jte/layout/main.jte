@import gg.jte.Content
@import org.example.hexlet.NamedRoutes

@param Content head
@param Content body

<!DOCTYPE html>
<html lang="en">
    <head>
        ${head}
    </head>
    <body>
        <header>
            <nav>
                <h1><a href="/">Hexlet</a></h1>
                <h5><a href="${NamedRoutes.buildUserPath()}">Register</a></h5>
            </nav>
        </header>
        ${body}
        <div class="footer">
            <p><a href="https://github.com/prvmjsky">My GitHub</a></p>
        </div>
    </body>
</html>