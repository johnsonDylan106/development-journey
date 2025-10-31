
class Employee:
    def __init__(self, name: str, number: int):
        self.name = name
        self.number = number

    def emp_name(self):
        print(f'Name: {self.name}')

    def emp_number(self):
        print(f'Number: {self.number}')

    def update_emp_name(self):
        self.name = input('Update Name: ')

    def update_emp_number(self):
        self.number = int(input('Updated Employee Number: '))


class ShiftWorker(Employee):
    def __init__(self, name, number, emp_shift: int, pay_rate: float):
        super().__init__(name, number)
        self.emp_shift = emp_shift
        self.pay_rate = pay_rate

    def emp_shift_number(self):
        if self.emp_shift == 1:
            print('Day shift')
        elif self.emp_shift == 2:
            print('Night shift')
        else:
            print('Invalid shift number')

    def hourly_rate(self):
        print(f'Hourly pay rate: ${self.pay_rate:.2f} hour')

    def update_emp_shift(self):
        self.emp_shift = int(input('New shift number (1 or 2): '))

    def emp_raise(self):
        self.pay_rate = float(input('New Hourly pay rate: '))


def main():

    choice = ""

    options = [1, 2]

    while choice not in options:
        choice = int(input("Enter your own info (1), Default Values (2)? "))

        if choice == 1:
            name = input("Name of Employee: ")
            number = int(input("Employee Number: "))
            work_shift = int(input("Day shift (1) or Night shift (2): "))
            hourly_rate = float(input("Employees hourly rate: "))
            emp1 = ShiftWorker(name, number, work_shift, hourly_rate)
            emp1.emp_name()
            emp1.emp_number()
            emp1.emp_shift_number()
            emp1.hourly_rate()

            correction_needed = ""

            while correction_needed.lower() != "y":
                correction_needed = input("Does this information look right? (Y/N) ")
                if correction_needed.lower() == "n":
                    emp1.update_emp_name()
                    emp1.update_emp_number()
                    emp1.update_emp_shift()
                    emp1.emp_raise()
                    emp1.emp_name()
                    emp1.emp_number()
                    emp1.emp_shift_number()
                    emp1.hourly_rate()

        elif choice == 2:
            emp1 = ShiftWorker("John Doe", 123456, 2, 16)
            emp1.emp_name()
            emp1.emp_number()
            emp1.emp_shift_number()
            emp1.hourly_rate()

            correction_needed = ""

            while correction_needed.lower() != "y":
                correction_needed = input("Does this information look right? (Y/N) ")
                if correction_needed.lower() == "n":
                    emp1.update_emp_name()
                    emp1.update_emp_number()
                    emp1.update_emp_shift()
                    emp1.emp_raise()
                    emp1.emp_name()
                    emp1.emp_number()
                    emp1.emp_shift_number()
                    emp1.hourly_rate()

        else:
            print("Incorrect Please try again: ")


if __name__ == "__main__":
    main()
