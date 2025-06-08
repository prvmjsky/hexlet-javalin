@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>${page.getCourse().getName()}</title>
</head>
<body>
<div class="col-lg-8 mx-auto p-4 py-md-5">
    <main>
        <h1>${page.getCourse().getName()}</h1>
        <p>${page.getCourse().getDescription()}</p>
    </main>
</div>
</body>
</html>