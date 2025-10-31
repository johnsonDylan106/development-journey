addCounter, subCounter, mulCounter, divCounter, absCounter, calculator = 0, 0, 0, 0, 0, ["add", "sub", "mul"]
tester = ["add", "sub", "mul", "div", "abs", "quit"]


def add(num):
    global addCounter
    addCounter += 1
    amount = len(num)
    for x in range(0, amount-1):
        print(f"{num[x]} +", end=' ')
    print(f"{num[amount-1]} = {sum(num)}")


def sub(num):
    global subCounter
    subCounter += 1
    total = num[0]
    amount = len(num)
    for x in range(0, amount-1):
        print(f"{num[x]} -", end=' ')
    print(num[amount - 1], end=' ')
    for x in range(1, amount):
        total = total - num[x]
    print(f"= {total}")


def mul(num):
    global mulCounter
    mulCounter += 1
    total = num[0]
    amount = len(num)
    for x in range(0, amount - 1):
        print(f"{num[x]} *", end=' ')
    print(num[amount - 1], end=' ')
    for x in range(1, amount):
        total = total * num[x]
    print(f"= {total}")


def div(num):
    global divCounter
    divCounter += 1
    total = num[0]
    amount = len(num)
    error = 0
    for x in range(1, amount - 1):
        if num[x] == 0:
            error = 1
            break
    if error == 1:
        print("ERROR: Division by zero")
    else:
        for x in range(0, amount - 1):
            print(f"{num[x]} /", end=' ')
        print(num[amount - 1], end=' ')
        for x in range(1, amount):
            total = total / num[x]
        print(f"= {total}")


def finished():
    global addCounter, subCounter, mulCounter, divCounter, absCounter
    print(f"Add function was used {addCounter} time(s)")
    print(f"Sub function was used {subCounter} time(s)")
    print(f"Mul function was used {mulCounter} time(s)")
    print(f"Div function was used {divCounter} time(s)")
    print(f"Abs function was used {absCounter} time(s)")
    print("\nThank you for using iCalculator\n")
    input("Press enter to exit")


def function():
    userinput = input()
    if userinput.lower() != "quit":
        values = userinput.split()
        numbers = []
        checkpoint = len(values)
        if checkpoint >= 3:
            operator = values[0]
            for x in range(1, checkpoint):
                numbers.append(float(values[x]))
            return operator, numbers
        elif checkpoint == 2:
            operator = values[0]
            if operator != "abs":
                return numbers
            else:
                num1 = values[1]
            return operator, num1
    else:
        return userinput


def calculations():
    global absCounter, calculator, tester
    print("Welcome to iCalculator")
    while calculator[0].lower() != "quit":
        calculator = function()
        try:
            if calculator == tester[5] or calculator[0] in tester:
                if calculator[0].lower() == "add" and len(calculator[1]) >= 2:
                    add(calculator[1])
                elif calculator[0].lower() == "sub" and len(calculator[1]) >= 2:
                    sub(calculator[1])
                elif calculator[0].lower() == "mul" and len(calculator[1]) >= 2:
                    mul(calculator[1])
                elif calculator[0].lower() == "div" and len(calculator[1]) >= 2:
                    div(calculator[1])
                elif calculator[0].lower() == "abs":
                    print(f"The absolute value of {calculator[1]} is {abs(float(calculator[1]))}")
                    absCounter += 1
                elif calculator == "quit":
                    finished()
                    break
            else:
                print("Invalid input, please try again.")
        except IndexError:
            print("Invalid input, please try again.")
        except TypeError:
            print("Not enough information, please try again")


def main():
    calculations()


if __name__ == "__main__":
    main()
