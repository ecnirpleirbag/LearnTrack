# Design Notes

## ArrayList vs Array
We used `ArrayList` instead of traditional arrays because `ArrayList` provides dynamic resizing. In a management system where the number of students or courses can change frequently, `ArrayList` is much more convenient as it handles growth automatically and provides useful methods for searching and filtering.

## Static Members
Static members were used in:
- `IdGenerator`: To maintain a global counter for unique IDs across all instances.
- `AppConstants`: To store application-wide constants like the app name and divider string.
Static methods allow access to these utilities without needing to instantiate the class every time.

## Inheritance
The `Person` class serves as a base class for `Student`. This demonstrates inheritance by allowing `Student` to inherit common fields like `firstName`, `lastName`, and `email`. It reduces code duplication and allows for polymorphism (e.g., overriding `getDisplayName`).

## Clean Code
- Used descriptive method names like `addStudent`, `enrollStudent`.
- Separated concerns into entity, service, repository, and UI layers.
- Handled exceptions gracefully to prevent program crashes.
