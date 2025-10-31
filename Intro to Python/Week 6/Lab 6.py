import random


class Rectangle:
    def __init__(self, height, width):
        self.height = height
        self.width = width

    def compute_area(self):
        total_area = 0
        for x, y in zip(self.height, self.width):
            total_area = total_area + (x*y)
        print(f"\nTotal area = {total_area}")


def rng():
    rectangles = []
    how_many = random.randrange(1, 21)
    print(f"Number of rectangles is {how_many}\n")
    height = []
    width = []
    while how_many != 0:
        height.append(random.randrange(0, 111))
        width.append(random.randrange(0, 111))
        how_many -= 1
    for x, y in zip(height, width):
        rectangles.append([x, y])
    for x in rectangles:
        print(x)
    return height, width


def main():
    tocompute = rng()
    heightwidth = Rectangle(tocompute[0], tocompute[1])
    heightwidth.compute_area()
    input("Press enter to exit.")


if __name__ == '__main__':
    main()

