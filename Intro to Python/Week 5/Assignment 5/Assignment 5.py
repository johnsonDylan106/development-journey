def load_dictionary():
    translations = {}
    with open("HmongWords.txt", "r") as words:
        for line in words:
            (n, k, v) = line.split(",")
            v = v.rstrip().lower()
            translations[v] = k
    return translations


def translate(sentence):
    illegal = "?!@#$%^&*,."
    all_words = []
    keep_translating = ""
    while keep_translating.lower() != "n":
        english = input("Type your English Sentence: ").lower()
        for x in illegal:
            english = english.replace(x, "")
        individual = english.split()
        print("Hmong: ", end='')
        for x in individual:
            if x in sentence.keys():
                print(sentence[x], end=' ')
            else:
                print("?", end=' ')
        for x in individual:
            all_words.append(x)
        keep_translating = input("\nAnother translation (Y/N): ")
    return all_words


def print_word_frequency(array):
    length = len(max(array, key=len))
    unique_words = []
    for x in array:
        if x not in unique_words:
            unique_words.append(x)
    print(f"\nWord", end=''.ljust(length))
    print("Frequency")
    print("----------------", end='-'*length)
    print()
    for x in unique_words:
        num_of_appear = 0
        num_of_char = len(x)
        space = " "
        num_of_spaces = (length-num_of_char+3)
        for y in array:
            if y == x:
                num_of_appear += 1
        print(f"{x} {space*num_of_spaces}", end='')
        print(num_of_appear)


def main():
    all_words = load_dictionary()
    frequency = translate(all_words)
    print_word_frequency(frequency)


if __name__ == '__main__':
    main()
# appear against govern
# I can help you help him.
