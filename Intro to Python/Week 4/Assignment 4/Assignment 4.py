def load_scores():
    filename = input("\nEnter the name of the file: ")
    scores = []
    try:
        with open(filename, 'r') as file_data:
            for line in file_data:
                score = line.strip()
                scores.append(int(score))
        print("Data read successfully.\n")
        return scores
    except FileNotFoundError:
        print("File does not exist. Please try again.\n\n")


def print_scores(output):
    text = ['Min       = ', 'Max       = ', 'Mean      = ', 'Median    = ']
    final = []
    for x in range(0, len(text)):
        final.append(text[x])
        final.append(f"{str(round(output[x],2))}\n")
    try:
        with open('stats-file.txt', 'x') as outfile:
            for x in final:
                outfile.write(x)
    except FileExistsError:
        with open('stats-file.txt', 'w') as outfile:
            for x in final:
                outfile.write(x)


def mean(scores):
    total = 0
    print("Calculating scores.", end='')
    for x in scores:
        total = total + x
        print(".", end='')
    calc_mean = total/len(scores)
    return calc_mean


def median(scores):
    run = int((len(scores)-1)/2)
    calc_median = []
    for x in scores:
        calc_median.append(x)
    if len(scores) % 2 == 0:
        for x in range(0, run):
            calc_median.pop()
            calc_median.sort(reverse=True)
            calc_median.pop()
            calc_median.sort()
            print(".", end='')
        calc_median = (calc_median[1]+calc_median[0])/2
        return calc_median
    else:
        for x in range(0, run):
            calc_median.pop()
            calc_median.sort(reverse=True)
            calc_median.pop()
            calc_median.sort()
            print(".", end='')
        return calc_median


def score_stats(scores):
    scores.sort()
    small = scores[0]
    large = scores[len(scores)-1]
    scores_mean = mean(scores)
    scores_median = median(scores)
    print(f"\n\nMin       = {small}")
    print(f"Max       = {large}")
    print(f"Mean      = {round(scores_mean, 2)}")
    print(f"Median    = {round(scores_median,2)}\n\n")
    return small, large, scores_mean, scores_median


def main():
    to_do = ""
    scores = []
    out_file = []
    while to_do != "4":
        print("Choose an option:")
        print("1. Load Data")
        print("2. Display computed statistics")
        print("3. Save computed statistics")
        print("4. Exit")
        to_do = input("Choice: ")

        if to_do == "1":
            scores = load_scores()
        elif to_do == "2":
            out_file = score_stats(scores)
        elif to_do == "3":
            print_scores(out_file)


if __name__ == '__main__':
    main()

