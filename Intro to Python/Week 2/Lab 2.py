import random

multipleOfTen = -10

while multipleOfTen <= 110:
    print(multipleOfTen)
    multipleOfTen += 10

print("\n\n\n")
randomNumbers = []
average = 0

for x in range(10):
    randomNumbers.append(random.randrange(0, 550, 2))

for last, x in enumerate(randomNumbers):
    if last == len(randomNumbers) - 1:
        print(f"{x} = ", end="")
    else:
        print(f"{x} + ", end="")

    average += x

average = average / len(randomNumbers)
print(f"{average}")
print(f"The Average of the numbers is: {average}")

print("\n\n\n")
killSwitch = "done"
userInput = ""

while userInput.lower() != killSwitch:
    userInput = input("Say something: ")
    if userInput.lower() == killSwitch:
        print("Goodbye!")
        break
    else:
        print(f"You said: {userInput}")

print("\n\n\n")
userInput = ""

while userInput.lower() != killSwitch:
    userInput = input("Say something: ")
    if userInput.lower() != killSwitch:
        print(f"You said: {userInput}")

print("Goodbye!")


