# Dice Game #
import random


# Random value generator ###############################################################################################
def dice():
    value = random.randrange(1, 7, 1)
    return value


# This function ensures values entered are integers ####################################################################
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
                secondchance = input("Not valid input. Last chance to enter your bet.\n")
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


# This function checks to make sure the player has enough funds for the bet attempted to be placed #####################
def overbet(bet, funds):
    if bet > funds:
        while bet > funds:
            bet = intchecker(input(f"You do not have the funds,\nEnter a new bet that is no more than {funds}: "))

    return bet
########################################################################################################################


# Initialized key variables ############################################################################################
amountOfMoney = 350
playerBet = 0
keepGoing = ""
########################################################################################################################

# Heart of the game ####################################################################################################
while amountOfMoney > 0:
    playersGuess = 0
    print(f"You have ${amountOfMoney}.")

    playerBet = intchecker(input("Place your bet: "))
    playerBet = overbet(playerBet, amountOfMoney)
    print(f"You have placed a ${playerBet} bet!\n\n")

    # Ensures the user enters a valid guess of the sum of the dice #####################################################
    while playersGuess < 2 or playersGuess > 12:
        playersGuess = intchecker(input("Dice sums can be from 2-12\nEnter what you think the total will be: "))
        if playersGuess < 2 or playersGuess > 12:
            print("Please enter a valid guess")
        else:
            print("Rolling the dice...")
            diceOne = dice()
            diceTwo = dice()
            correctAnswer = diceOne + diceTwo
    ####################################################################################################################
    # If bet placed is not an integer it kills the program #############################################################
    if playerBet == "Rerun program to try again.":
        break
    ####################################################################################################################
    else:
        print(f"\n\nThe sum of the 2 dice is equal to.....\n{correctAnswer}")
        print(f"\nDice number one is {diceOne}\nDice number two is {diceTwo}")
        print(f"\nYou guessed the sum would be {playersGuess}\n")
        if playersGuess == correctAnswer:
            if diceOne == diceTwo:
                amountOfMoney = amountOfMoney + (playerBet * 2)
                print(f"Wow!! You have one {playerBet * 2}\n")
            elif diceOne != diceTwo:
                amountOfMoney = amountOfMoney + playerBet
                print(f"Congrats! You have one {playerBet}\n")
        else:
            print("Sorry, your guess was incorrect\n")
            amountOfMoney -= playerBet
        if amountOfMoney > 0:
            keepGoing = input("Would you like to play again? y/n: ")
            if keepGoing.lower() != "y":
                print(f"\nYour game is over, you ended with ${amountOfMoney}\n")
                break
        else:
            print("You are out of money, better luck next time!\n")

########################################################################################################################

# Closing Statements ###################################################################################################
if amountOfMoney == 0:
    print("See you later!")
elif playerBet == "Rerun program to try again.":
    print("Please enter valid input to continue to play the game.")
elif keepGoing.lower() == "n":
    print("See you later!")
    print("\nThanks for playing!")

input("Press enter to exit.")
########################################################################################################################
