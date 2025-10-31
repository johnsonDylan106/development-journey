# Dylan Johnson
# CSC 101 - Assignment 4
# 6/2/22

# Functions to challenge myself and take out possible error messages ###################################################
def firstname(first):
    print(f"{first}, welcome to the world of programming!")


def averageofnumbers(average):
    numbers = 0
    for c in average:
        numbers += c
    average = numbers / len(average)
    return average

def intchecker(averagenumber):
    number = None
    loopcount = 0
    my_variable = averagenumber
    while loopcount < 2:
        try:
            if loopcount == 0:
                int(my_variable)
            else:
                int(secondchance)
        except ValueError:
            if loopcount < 1:
                secondchance = input("Not valid input, Last chance for valid input.\n")
                loopcount += 1
            else:
                loopcount += 1
        else:
            if loopcount == 1:
                number = int(secondchance)
            else:
                number = int(my_variable)
            loopcount = 4

    if loopcount == 2:
        return "Rerun program to try again."
    elif loopcount == 4:
        return number
#5######################################################################################################################


# Asks for users first name ################
fName = input("What is your first name?\n")
#47##########################################

# prints firstName function 10 times #
for i in range(10):
    firstname(fName)
#51####################################

# Variables for inside the while loop ####
breakVariable = 0
newNumber = 0
assignmentChoice = 0
#56########################################

# This was done for fun#################################################################################################
# Created 2 different ways to prompt the user for the numbers they'd like to average ###################################
while breakVariable < 3: # This is here to provide a way out of the loop and make easier breaks

    # Gives the user a choice on which way they'd like to see the output################################################
    print("\nNow to calculate the average of some numbers.\n")
    print("There are 2 different programmed ways which would you like to try?\n")
    assignmentChoice = intchecker(input("The assignment describes, (1)\nOr\nAnother way, (2)?\n"))
    # Checks if input is valid, if not it kills the program#############################################################
    if assignmentChoice == "Rerun program to try again.":
        break
    else:
        if assignmentChoice != 1 and assignmentChoice != 2:
            if breakVariable == 3:
                break
            else:
                print("\nInvalid input, please try again.\n")
                breakVariable = breakVariable + 1
        elif assignmentChoice == 1 or assignmentChoice == 2:
            # Asks how many numbers you would like to average and defines an array variable #
            numOfNumbers = intchecker(input("\nHow many numbers would you like to average?\n"))
            if numOfNumbers == "Rerun program to try again.":
                break
    #66#################################################################################################################
            # This part takes the users input and gets it ready to to calculate the average#############################
            else:
                # Array to store numbers to average and a counter variable for output to user later#####################
                numsToAverage = []
                breakVariable = 4
                #86#####################################################################################################

                # Loop asking for user input on #'s they'd like to average##############################################
                for i in range(numOfNumbers):

                    # Shows the way the assignment asked for############################################################
                    if assignmentChoice == 1:
                        if numOfNumbers > 1:
                            if i == 0:
                                testVariable = intchecker(input("Please enter your first number:\n"))
                                if testVariable == "Rerun program to try again.":
                                    print(testVariable)
                                    break
                                else:
                                    numsToAverage.append(testVariable)
                            elif i == numOfNumbers - 1:
                                testVariable = intchecker(input("Please enter your last number::\n"))
                                if testVariable == "Rerun program to try again.":
                                    print(testVariable)
                                    break
                                else:
                                    numsToAverage.append(testVariable)
                            elif i < numOfNumbers - 1:
                                testVariable = intchecker(input("Please enter your next number:\n"))
                                if testVariable == "Rerun program to try again.":
                                    print(testVariable)
                                    break
                                else:
                                    numsToAverage.append(testVariable)
                            else:  # Will only run if not enough numbers are available
                                print(f"You need more than {numOfNumbers} to calculate an average.")
                    #96#################################################################################################

                    # Provides same output as section above, in different format and less code##########################
                    elif assignmentChoice == 2:
                        if numOfNumbers > 1:
                            testVariable = intchecker(input(f"Number {i + 1} of {numOfNumbers} to average:\n"))
                            if testVariable == "Rerun program to try again.":
                                print(testVariable)
                                break
                            else:
                                numsToAverage.append(testVariable)
                        else: # Will only run if not enough numbers are available
                            print(f"You need more than {numOfNumbers} number to calculate an average.")
                    #124################################################################################################
                #93#####################################################################################################
#62#####################################################################################################################

# Provides the average of the numbers provided #########################################################################
# Should any errors happen, the if statements take away output errors and replace with reasons why######################
if breakVariable == 3:
    print("\nTo many invalid attempts, please rerun the program to try again.")
elif assignmentChoice == "Rerun program to try again.":
    print(f"\nInvalid choice, {assignmentChoice}")
elif numOfNumbers == "Rerun program to try again.":
    print(f"\nInvalid choice, {numOfNumbers}")
elif testVariable == "Rerun program to try again.":
    print("\nNumbers need to be added.")
elif len(numsToAverage) == 0:  # Unless not enough numbers are provided
    print("\n\nMore numbers are required")  # Because the array variable equaled nothing
else:
    print(f"\n\nThe numbers you are averaging are: {numsToAverage}")
    print(f"\nThe average of your {numOfNumbers} numbers is: {averageofnumbers(numsToAverage)}")
    if fName == "Susan":
        print(f"\nI'm glad you found this part, {fName}!")
        print("I hope you get to try the few different areas I created in here!")
        print("If you have time, I challenge you to find a way to get an error message in output. :)")
        print("I had way too much fun making this! haha")
    else:
        print(f"\nThanks for using my program, {fName}!")

input("\n\nPress enter to end.")
#139####################################################################################################################
