
def bracket(dependent):
    percentage = 0
    if dependent <= 1:
        percentage = .25
    elif dependent <= 3:
        percentage = .2
    elif dependent >= 4:
        percentage = .15
    return percentage


def netpay(earned, percentage):
    pay = earned - (earned * percentage)
    return pay


def numformat(number):
    return "{:,.2f}".format(number)


firstName = input("Enter your first name: ")
lastName = input("Enter your last name: ")
grossPay = float(input("Enter your gross pay: "))
dependents = int(input("Enter number of dependents: "))

taxPercentage = bracket(dependents)

afterTaxes = netpay(grossPay, taxPercentage)

print(f"\n\nName:       {firstName} {lastName}")
print(f"Gross pay:  ${numformat(grossPay)}")
print(f"Dependents: {dependents}")
print(f"Tax rate:   {taxPercentage}")
print(f"Net Pay:    ${numformat(afterTaxes)}")
input("Enter to exit")
