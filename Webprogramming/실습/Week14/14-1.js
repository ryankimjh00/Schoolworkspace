const v = "global (0)";
const printVariable = () => {
    console.log(v); // console.log(this.v) -> x
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