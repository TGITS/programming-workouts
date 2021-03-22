class School:
    def __init__(self):
        self.students_by_grade = {}

    def add_student(self, name, grade):
        if grade not in self.students_by_grade:
            self.students_by_grade[grade] = []
        
        self.students_by_grade[grade].append(name)

    def roster(self):
        return [student for _, students in sorted(self.students_by_grade.items()) for student in sorted(students)]

    def grade(self, grade_number):
        return sorted(self.students_by_grade[grade_number]) if grade_number in self.students_by_grade else []
