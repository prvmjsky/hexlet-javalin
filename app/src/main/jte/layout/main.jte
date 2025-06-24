@import gg.jte.Content
@import org.example.hexlet.dto.BasePage
@import org.example.hexlet.util.NamedRoutes

@param Content head
@param Content body
@param BasePage page = null

<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        ${head}
    </head>
    <body>
        @if(page != null && page.getFlash() != null)
            <div class="${page.getFlashType()}" role="alert">
                <p>${page.getFlash()}</p>
            </div>
        @endif
        <header>
            <nav>
                <h1><a href="/">Hexlet</a></h1>
                <h5><a href="${NamedRoutes.buildUserPath()}">Create user</a></h5>
                <h5><a href="${NamedRoutes.buildSessionPath()}">Sign in</a></h5>
            </nav>
        </header>
        ${body}
        <div class="footer">
            <p><a href="https://github.com/prvmjsky">My GitHub</a></p>
        </div>
    </body>
</html>