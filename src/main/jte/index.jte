@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.main(
    page = page,
    head = @`
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hello Hexlet!</title>
    `,
    body = @`
        <div class="col-lg-8 mx-auto p-4 py-md-5">
            <main>
                <h1 class="text-body-emphasis">Hello Hexlet!</h1>

                @if(page.getCurrentUser() != null)
                    <div>
                        Hello, ${page.getCurrentUser()}!
                        <br><br>
                    </div>
                @endif

                @if(!page.isVisited())
                    <div>
                        You're visiting us for the first time!
                        <br>
                        Or you've simply cleaned your cookies...
                    </div>
                @endif

                <h3><a href="${NamedRoutes.coursesPath()}">Programming courses</a></h3>
                <h3><a href="${NamedRoutes.usersPath()}">Users</a></h3>
            </main>
        </div>
    `
)