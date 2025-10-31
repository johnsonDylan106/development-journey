import random

def display_banner():
    print("""
 __                               _      _            _ 
/ _\  ___  _ __  __ _  _ __ ___  | |__  | |  ___   __| |
\ \  / __|| '__|/ _` || '_ ` _ \ | '_ \ | | / _ \ / _` |
_\ \| (__ | |  | (_| || | | | | || |_) || ||  __/| (_| |
\__/ \___||_|   \__,_||_| |_| |_||_.__/ |_| \___| \__,_|
                                                        
""")


def load_words(filename):
    # load file containing scrambled word and answer.
    # scrambled word and answer are separated by :

    scrambled_list = []
    answer_list = []
    with open(filename, 'r') as f:
        for line in f:
            (s, a) = line.strip().split(":")
            scrambled_list += [s]
            answer_list += [a]
    return scrambled_list, answer_list


def heart_of_game(scrambled_list, answer_list):
    game = ""
    guess = ""
    which_word = []
    word = random.randrange(0, len(scrambled_list))
    number_of_guesses = 0

    while game.lower() != "n":
        if len(which_word) != len(scrambled_list):
            while word in which_word:
                word = random.randrange(0, len(scrambled_list))
            if number_of_guesses != 0:
                print(f"There are {len(scrambled_list) - len(which_word)} words left.")
            which_word.append(word)
            scrambled_word = scrambled_list[word]
            print(f"Scrambled word is: {scrambled_word}")
            while guess != answer_list[word]:
                guess = input("What is the word? ")
                if guess == answer_list[word]:
                    print("You got it!")
                    number_of_guesses += 1
                    if len(which_word) != len(scrambled_list):
                        game = input("Another game? (Y/N): ")
                elif len(which_word) == len(scrambled_word):
                    break
                else:
                    print("Wrong answer. Try again!")
                    number_of_guesses += 1
        else:
            break

    if len(which_word) == len(scrambled_list):
        print(f"Game Over!")
        print(f"It took {number_of_guesses} guesses to guess all {len(scrambled_list)} words!")
        if number_of_guesses == len(scrambled_list):
            print("Congrats!! You got them all in one shot!")
    else:
        if len(which_word) == 1 and number_of_guesses == 1:
            print(f"You completed {len(which_word)} word in {number_of_guesses} guess")
        elif len(which_word) == 1 and number_of_guesses != 1:
            print(f"You completed {len(which_word)} word in {number_of_guesses} guesses")
        else:
            print(f"You completed {len(which_word)} words in {number_of_guesses} guesses")
    input("Hope to see you again soon!")


def main():
    display_banner()

    (scrambled_list, answer_list) = load_words('Lab 4.txt')

    heart_of_game(scrambled_list, answer_list)

    # --------------------------
    # Randomly pick a scrambled word from the list.
    # Asks the user to guess it.
    # Ask again if the guess is wrong.  Repeat until the guess is right.
    # If guess is right, ask if user wants another game.
    # --------------------------


main()
