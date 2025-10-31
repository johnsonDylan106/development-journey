def read_name():
    firstname = input("Enter your first name: ")
    lastname = input("Enter your last name: ")
    return firstname, lastname


def read_gross_pay():
    return float(input("Enter your gross pay: "))


def dependents():
    return input("Enter number of dependents: ")


def compute_tax_rate(dependents):
    taxrate = 0
    if dependents <= 1:
        taxrate = .25
    elif dependents <= 3:
        taxrate = .2
    elif dependents >= 4:
        taxrate = .15
    return taxrate


def compute_net_pay(grossincome, taxrate):
    return grossincome - (grossincome * taxrate)


def numformat(uscurrency):
    return "{:,.2f}".format(uscurrency)


def output(name, grosspay, dependents, taxrate, netpay):
    print(f"\n\nName:       {name[0]} {name[1]}")
    print(f"Gross pay:  ${numformat(grosspay)}")
    print(f"Dependents: {dependents}")
    print(f"Tax rate:   {'{:.0%}'.format(taxrate)}")
    print(f"Net Pay:    ${numformat(netpay)}")
    input("Enter to exit")

def main():
    name = read_name()
    grosspay = float(read_gross_pay())
    dependent = int(dependents())
    taxrate = float(compute_tax_rate(dependent))
    netpay = compute_net_pay(grosspay, taxrate)
    output(name, grosspay, dependent, taxrate, netpay)


if __name__ == "__main__":
    main()
