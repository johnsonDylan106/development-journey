import random
from turtle import Turtle
from math import sqrt


class DrawObject:
    turtle = Turtle()


class Circle(DrawObject):
    def __init__(self, center, radius):
        self.center = center
        self.radius = radius

    def draw(self):
        DrawObject.turtle.shape('circle')
        DrawObject.turtle.up()
        DrawObject.turtle.setpos(self.center[0], self.center[1] - abs(self.radius))
        DrawObject.turtle.down()
        DrawObject.turtle.circle(self.radius)
        DrawObject.turtle.up()

    def area(self):
        a = 3.14 * pow(self.radius, 2)
        return a


class Rectangle(DrawObject):
    def __init__(self, base, height):
        self.base = base
        self.height = height

    def draw(self):
        DrawObject.turtle.shape('square')
        DrawObject.turtle.up()
        DrawObject.turtle.setpos(self.base, self.height)
        DrawObject.turtle.down()
        for length in range(0, 2):
            DrawObject.turtle.forward(abs(self.base))
            DrawObject.turtle.left(90)
            DrawObject.turtle.forward(abs(self.height))
            DrawObject.turtle.left(90)
        DrawObject.turtle.up()

    def area(self):
        a = abs(self.base * self.height)
        return a


class RightTriangle(DrawObject):
    def __init__(self, base, height):
        self.base = base
        self.height = height

    def draw(self):
        c = sqrt(pow(self.base, 2) + pow(self.height, 2))
        DrawObject.turtle.up()
        DrawObject.turtle.shape('triangle')
        DrawObject.turtle.setpos(self.base, self.height)
        DrawObject.turtle.down()
        DrawObject.turtle.forward(abs(self.base))
        DrawObject.turtle.left(90)
        DrawObject.turtle.forward(abs(self.height))
        DrawObject.turtle.setpos(self.base, self.height)
        DrawObject.turtle.up()

    def area(self):
        a = abs(self.base * self.height) / 2
        return a


class EqualTriangle(DrawObject):
    def __init__(self, sides, ycord):
        self.sides = sides
        self.ycord = ycord

    def draw(self):
        DrawObject.turtle.up()
        DrawObject.turtle.shape('triangle')
        DrawObject.turtle.setpos(self.sides, self.ycord)
        DrawObject.turtle.down()
        for x in range(0, 3):
            DrawObject.turtle.forward(self.sides)
            DrawObject.turtle.left(120)

    def area(self):
        a = (sqrt(3)/4) * pow(self.sides, 2)
        return a


def print_shapes(number_of_shapes):
    def point(number=1):
        if number == 1:
            cord = random.randint(-200, 200)
            return cord
        elif number == 2:
            cord1 = random.randint(-200, 200)
            cord2 = random.randint(-200, 200)
            return cord1, cord2

    to_draw = []

    for x in range(1, number_of_shapes+1):
        which_shape = random.randint(1, 4)
        if which_shape == 1:
            to_draw += [Circle(point(2), point())]
        elif which_shape == 2:
            to_draw += [Rectangle(point(), point())]
        elif which_shape == 3:
            to_draw += [RightTriangle(point(), point())]
        elif which_shape == 4:
            to_draw += [EqualTriangle(point(), point())]

    return to_draw


def main():
    create_shapes = print_shapes(14)
    total_area = 0
    for x in create_shapes:
        x.draw()
        total_area += x.area()

    print(f"Total Area: {total_area}")
    input("Press <enter> to exit.")


if __name__ == "__main__":
    main()
