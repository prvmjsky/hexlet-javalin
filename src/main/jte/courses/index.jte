@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.main(
head = @`
    <meta charset="utf-8" />
    <title>Courses</title>
`,
body = @`
    <h1>${page.getHeader()}</h1>
    @if(page.getCourses().isEmpty())
        <p>No courses yet</p>
    @else
        @for(var course : page.getCourses())
            <div>
                <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
                <p>${course.getDescription()}</p>
            </div>
        @endfor
    @endif
`
)