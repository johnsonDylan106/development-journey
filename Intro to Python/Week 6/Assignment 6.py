class Park:

    def __init__(self, name_of_park, location, type_of_park, entrance_fee, visitors_twelve_months, budget):
        self.name_of_park = name_of_park
        self.location = location
        self.type_of_park = type_of_park
        self.entrance_fee = entrance_fee
        self.visitors_twelve_months = visitors_twelve_months
        self.budget = budget

    def park_info(self):
        return f"\nPark Name: {self.name_of_park}\nPark Location: {self.location}\nType of Park: {self.type_of_park}"

    def cost_per_visitor(self):
        return self.budget/self.visitors_twelve_months

    def revenue(self):
        return f"\nTotal revenue: ${self.entrance_fee*self.visitors_twelve_months}"

    def everything(self):
        return f"{self.park_info()}\nEntrance fee: {self.entrance_fee}\nVisitors for last 12 Months: " \
               f"{self.visitors_twelve_months}\nBudget: ${self.budget}\nCost per Visitor: ${self.cost_per_visitor()}\n" \
               f"{self.revenue()}"


def main():
    park1 = Park("Mississippi Point Park", "Anoka", "Local", 5, 10000, 30000)
    print(park1.park_info())
    print(park1.cost_per_visitor())
    print(park1.revenue())
    print(park1.everything())
    input("Press enter to exit")


if __name__ == '__main__':
    main()
