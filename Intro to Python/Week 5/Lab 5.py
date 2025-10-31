def en_input():
    words = input("Enter English Words: ").lower()
    return words


def long_word(text):
    text = text.split()
    long = max(text, key=len)
    return len(long)


def unique(text):
    char = ".?/!@#"
    for x in char:
        text = text.replace(x, "")
    text = text.split()
    unique_text = []
    for x in text:
        if x not in unique_text:
            unique_text.append(x)
    return text, unique_text


def connect(list1, list2):
    list0 = {}
    for x in list1:
        list0[x] = list2.count(x)
    return sorted(list0.items(), key=lambda x: x[1], reverse=True)


def out(values, length):
    print("Word", end=''.ljust(length))
    print("Count\n")
    num = list(values.values())
    check = 0
    for key, value in sorted(values.items(), key=lambda x: x[1], reverse=True):
        print(f"{key.ljust(length + 3)} {value}")
        for y in range(check, len(num)-1):
            if num[y] != num[y+1]:
                print()
                break
            else:
                break
        check += 1


def main():
    words = en_input()
    longest = long_word(words)
    unique_words = unique(words)
    final = connect(unique_words[1], unique_words[0])
    final = dict(final)
    out(final, longest)


if __name__ == '__main__':
    main()

