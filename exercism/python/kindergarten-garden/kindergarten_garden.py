class Garden:
    def __init__(self, diagram, students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']):
        self.rows = diagram.split("\n")
        self.students = sorted(students)
        self.plant_name_by_initial = { 'V': 'Violets', 'R' : 'Radishes', 'C':'Clover', 'G' : 'Grass'}

    def plants(self, child_name):
        child_plant_index = self.students.index(child_name) * 2
        child_plants = []
        for row in self.rows:
            child_plants.append(self.plant_name_by_initial[row[child_plant_index]])
            child_plants.append(self.plant_name_by_initial[row[child_plant_index + 1]])
        return child_plants
