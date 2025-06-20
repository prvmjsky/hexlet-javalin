@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.courses.BuildCoursePage
@param BuildCoursePage page

@template.layout.main(
    page = page,
    head = @`
        <meta charset="utf-8">
        <title>New course</title>
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

        <form action ="${NamedRoutes.coursesPath()}" method="post">
            <div>
                <label>
                    Name
                    <input type="text" required name="name" value="${page.getName()}">
                </label>
            </div>
            <div>
                <label>
                    Description
                    <input type="text" required name="description" value="${page.getDescription()}">
                </label>
            </div>
            <input type="submit" value="Create">
        </form>
    `
)

