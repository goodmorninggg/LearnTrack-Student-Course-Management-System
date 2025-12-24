# Design Notes

## Why ArrayList instead of Array?
We used `ArrayList` for storing Students, Courses, and Enrollments because:
1.  **Dynamic Sizing**: Arrays have a fixed size. We don't know how many students or courses will be added. `ArrayList` grows automatically.
2.  **Ease of Use**: `ArrayList` provides built-in methods like `add()`, `remove()`, `contains()`, and `size()`, which makes managing data much easier than manual array manipulation.

## Static Members
We used `static` members in the `IdGenerator` class.
- **Why?**: We need a single, shared counter for IDs across the entire application. If we used instance variables, every time we created a new `IdGenerator` object, the counter would reset.
- `private static int studentIdCounter`: Keeps track of the last used ID regardless of where it is accessed.
- `public static int getNextStudentId()`: Allows us to get a new ID without needing to instantiate an `IdGenerator` object.

## Inheritance
We introduced a `Person` base class.
- **Why?**: To adhere to the **DRY (Don't Repeat Yourself)** principle.
- Both `Student` and potentially `Trainer` (future scope) share common fields like `firstName`, `lastName`, and `email`.
- By creating `Person`, we define these common attributes once. `Student` then extends `Person`, inheriting these fields automatically. This makes the code cleaner and easier to maintain.
