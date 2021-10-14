let customerAge;
let gender;
let sourceOfIncome;
let revenue;
let rating;
let amountCredit;
let period;
let purposeCredit;
let solutionCredit;
let mandatory = ["customerAgeId",  "sourceOfIncomeId", "revenueId", "ratingId", "amountCreditId", "periodId",
    "purposeCreditId", "customerSexId"];
let baseRate=10;
let modifier;
let annualPayment=0;
let value;
let newSumCredit=0;

function getValue(){
    value=true;
    customerAge = Number(document.getElementById("customerAgeId").value);
    gender = document.getElementById("customerSexId").value;
    sourceOfIncome = document.getElementById("sourceOfIncomeId").value;
    revenue = Number(document.getElementById("revenueId").value);
    rating = document.getElementById("ratingId").value;
    amountCredit = Number(document.getElementById("amountCreditId").value);
    period = Number(document.getElementById("periodId").value);
    purposeCredit = document.getElementById("purposeCreditId").value;

    for (let i = 0; i < mandatory.length; i++) {
        document.getElementById(mandatory[i].valueOf()).style.borderColor = "#ffcc00";
        if (document.getElementById(mandatory[i]).value.valueOf()== '') {
            document.getElementById(mandatory[i].valueOf()).style.borderColor = "red";
            document.getElementById('msg').style.display = 'block';
            document.getElementById('msg').innerHTML = '* Все параметры обязательны для заполнения';
            value=false;
        }
    }
}

function rateCredit() {
    modifier=0;
    if(purposeCredit=="mortgage"){
        modifier=modifier-2;
    }else if(purposeCredit=="businessUp"){
        modifier=modifier-0.5;
    }else if(purposeCredit=="consumer"){
        modifier=modifier+1.5;
    }
    if(rating=="-1"){
        modifier=modifier+1.5;
    }else if(rating=="1"){
        modifier=modifier - 0.25;
    }else if(rating=="2"){
        modifier=modifier - 0.75;
    }

    if(sourceOfIncome=="passive"){
        modifier=modifier + 0.5;
    }else if(sourceOfIncome=="employee"){
        modifier=modifier - 0.25;
    }else if(sourceOfIncome=="business"){
        modifier=modifier + 0.25;
    }

    modifier=modifier-Math.log10(amountCredit);
   }

function getPayment() {
    sumCredit();
    annualPayment=Number((newSumCredit*(1 + period * (baseRate + modifier)*0.01)) / period).toFixed(6);
}

function solution(){
    solutionCredit = true;
    let returnCreditAge = customerAge+period;
    if((gender =='male' && returnCreditAge>=65) || (gender =='female' && returnCreditAge>=60) || (customerAge<18)){
        solutionCredit=false;
    }
    if((amountCredit/period) > (revenue/3)){
        solutionCredit=false;
    }
    if(rating=='-2'){
        solutionCredit=false;
    }
    if(sourceOfIncome=='unemployed'){
        solutionCredit=false;
    }


}

function sumCredit() {
    newSumCredit=amountCredit;
    if(sourceOfIncome=="passive" && newSumCredit>1){
        newSumCredit=1;
    }else if(sourceOfIncome=="employee"&& newSumCredit>5){
        newSumCredit=5;
    }else if(sourceOfIncome=="business" && newSumCredit>10){
        newSumCredit=10;
    }
    if(rating=="-1" && newSumCredit>1){
        newSumCredit=1;
    }else if(rating=="0" && newSumCredit>5){
        newSumCredit=5;
    }else if((rating=="1" || rating=="2") && newSumCredit>10 ){
        newSumCredit=10;
    }

}

function clickReset() {
    document.getElementById('msg').style.display = 'none';
    document.getElementById('resumeId').style.display = 'none';
    document.getElementById('sumId').style.display = 'none';
    for (let i = 0; i < mandatory.length; i++) {
        document.getElementById(mandatory[i].valueOf()).style.borderColor = "#ffcc00";
        document.getElementById(mandatory[i]).value = '';
    }
}

function clickCalculate() {
    document.getElementById('msg').style.display = 'none';
    document.getElementById('resumeId').style.display = 'none';
    document.getElementById('sumId').style.display = 'none';
    getValue();
    if(value==true){
        solution();
        if (solutionCredit==false){
            document.getElementById('resumeId').style.display = 'block';
            document.getElementById('resumeId').innerHTML = "РЕШЕНИЕ ПО КРЕДИТУ: ОТКАЗАНО";
        }else {
            rateCredit();
            getPayment();
            if (annualPayment > revenue / 2) {
                document.getElementById('resumeId').style.display = 'block';
                document.getElementById('resumeId').innerHTML = "РЕШЕНИЕ ПО КРЕДИТУ: ОТКАЗАНО";
            } else {
                document.getElementById('resumeId').style.display = 'block';
                document.getElementById('resumeId').innerHTML = "РЕШЕНИЕ ПО КРЕДИТУ: КРЕДИТ ВЫДАЕТСЯ. ЗАПРОШЕННАЯ СУММА "+ amountCredit + " МЛН.РУБ.,  " +
                    "ОДОБРЕННАЯ СУММА "+ newSumCredit + " МЛН.РУБ.";
                document.getElementById('sumId').style.display = 'block';
                document.getElementById('sumId').innerHTML = "ГОДОВОЙ ПЛАТЕЖ ПО КРЕДИТУ: " + annualPayment + " млн.рублей";
            }
        }
    }
}
