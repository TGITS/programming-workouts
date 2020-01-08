class Student {
    fullName: string;
    constructor(public firstName: string, public middleInitial: string, public lastName: string) {
        this.fullName = firstName + " " + middleInitial + " " + lastName;
    }

    public toString = () : string => {
        return `${this.fullName}`;
    }
}

interface Person {
    firstName: string;
    lastName: string;
}

function greeter(person: Person) {
    return `Hello, ${person}`;
}

let user = new Student("Jane", "M.", "User");

document.body.textContent = greeter(user);