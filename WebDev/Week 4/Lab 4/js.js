var plusButton = document.getElementById('plusButton');
var minusButton = document.getElementById('minusButton');
 
plusButton.onclick = function incrementByOne(){
    document.getElementById('increment').stepUp(1);
}

minusButton.onclick = function decrementByOne(){
    document.getElementById('increment').stepUp(-1);
}
