@param String name
@param String description
@param String problem = null

@template.layout.main(
    head = @`
        <meta charset="utf-8">
        <title>New course</title>
    `,
    body = @`
        <form action ="/courses" method="post">
            <div>
                <label>
                    Name
                    <input type="text" required name="name" value="${name}">
                </label>
            </div>
            <div>
                <label>
                    Description
                    <input type="text" required name="description" value="${description}">
                </label>
            </div>
            <input type="submit" value="Create">
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

