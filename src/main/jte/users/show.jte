@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.main(
    page = page,
    head = @`
        <meta charset="utf-8">
        <title>${page.getUser().getName()}</title>
    `,
    body = @`
        <div class="col-lg-8 mx-auto p-4 py-md-5">
            <main>
                <h1>${page.getUser().getName()}</h1>
                <p>The man.</p>
            </main>
        </div>
    `
)