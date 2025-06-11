@template.layout.main(
    head = @`
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hello Hexlet!</title>
    `,
    body = @`
        <div class="col-lg-8 mx-auto p-4 py-md-5">
            <main>
                <h1 class="text-body-emphasis">Hello Hexlet!</h1>
                <h3><a href="/courses">Programming courses</a></h3>
                <h3><a href="/users">Users</a></h3>
            </main>
        </div>
    `
)