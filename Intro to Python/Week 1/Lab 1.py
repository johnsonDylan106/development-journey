def gradeCheck(grade, grad):
    lettergrade = []
    if "y" in grad:
        grade = grade - 10
    elif "Y" in grad:
        grade = grade - 10

    if grade >= 90:
        lettergrade = ["A", "Excellent!"]
    elif grade >= 80:
        lettergrade = ["B", "Excellent!"]
    elif grade >= 70:
        lettergrade = ["C", "Could Improve."]
    elif grade >= 60:
        lettergrade = ["D", "Could Improve."]
    else:
        lettergrade = ["F", "Needs work."]
    return lettergrade


checkStudents = 1
student = 1
grades = []
comments = []

while checkStudents == 1:
    print(f"Checking Grades for student #{student}")
    numberGrade = int(input("Enter Grade (0-100): "))
    stuLevel = input("Graduate Student? (Y/N): ")
    numberGrade = gradeCheck(numberGrade, stuLevel)
    print(f"Your letter grade is: {numberGrade[0]}")
    print(numberGrade[1])
    grades.append(numberGrade[0])
    comments.append(numberGrade[1])
    keepGoing = input("\n\nWould you like to check another student (Y/N): ")

    if "n" in keepGoing:
        checkStudents = 0
    elif "N" in keepGoing:
        checkStudents = 0
    else:
        print("\n\n")
        student += 1

print(f"\n{student} student(s) grades were checked!")
for x in grades:
    print(f"Student {checkStudents+1} earned a letter grade of: {x}, {comments[checkStudents]}")
    checkStudents += 1

