this.onmessage = function (e) {
    if (e.data.addThis != undefined) {
        let a = e.data.addThis.num1;
        let b = e.data.addThis.num2;
        this.postMessage({result: a + b});
    }
}