const v = "global (0)";

function printVariable() {
    console.log(this.v);
}

const call1 = {
    v: 'Local (1)',
    fun: printVariable
}
const call2 = {
    v: 'Local (2)',
    fun: printVariable
}
call1.fun();
call2.fun();