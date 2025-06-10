@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.main(
    head = @`
        <meta charset="utf-8" />
        <title>Courses</title>
    `,
    body = @`
        <h1>Courses</h1>
        <h3>Search by name or description</h3>
        <form>
            <input type="search" name="term" value="${page.getTerm()}">
            <input type="submit" value="Search">
        </form>
        @if(page.getCourses().isEmpty())
            <p>No courses yet</p>
        @else
            @for(var course : page.getCourses())
                <div>
                    <h3><a href="/courses/${course.getId()}">${course.getName()}</a></h3>
                    <p>${course.getDescription()}</p>
                </div>
            @endfor
        @endif
    `
)